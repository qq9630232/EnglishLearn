package com.example.englishlearn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.englishlearn.constant.ApiConstant.BASE_URL;

/**
 * Created by bianbian on 2021/4/18.
 */

public class RegisterActivity extends AppCompatActivity {
    private EditText reg_username;
    private EditText reg_password;
    private EditText reg_password2;
    private EditText reg_mail;
    private Button reg_btn_sure;
    private Button reg_btn_login;
    String data;
    String message;
    private static final String TAG = "yfg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reg_username = (EditText) findViewById(R.id.reg_username);
        reg_password = (EditText) findViewById(R.id.reg_password);
        reg_password2 = (EditText) findViewById(R.id.reg_password2);
        reg_btn_sure = (Button) findViewById(R.id.reg_btn_sure);
        reg_btn_login = (Button) findViewById(R.id.reg_btn_login);
        reg_btn_sure.setOnClickListener(new RegisterButton());
        reg_btn_login.setOnClickListener(new RegisterButton());
    }

    public class RegisterButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final String username = reg_username.getText().toString().trim();
            final String password = reg_password.getText().toString().trim();
            String password2 = reg_password2.getText().toString().trim();
            switch (v.getId()) {
                //注册开始，判断注册条件
                case R.id.reg_btn_sure:
                    if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password2) ) {
                        Toast.makeText(RegisterActivity.this, "各项均不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        if (TextUtils.equals(password, password2)) {
                            //执行注册操作
                            final String url = BASE_URL+"fm/login/study/register";
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                                    OkHttpClient okHttpClient = new OkHttpClient();
                                    JSONObject json = new JSONObject();
                                    try {
                                        json.put("username", username);
                                        json.put("password", password);
                                    } catch (JSONException e) {

                                    }
                                        RequestBody requestBody = RequestBody.create(JSON, String.valueOf(json));
                                    Request request = new Request.Builder()
                                            .url(url)
                                            .addHeader("Content-Type","application/json")
                                            .post(requestBody)
                                            .build();

                                    Call call = okHttpClient.newCall(request);
                                    call.enqueue(new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {
                                            Log.d(TAG, "response: " + e.getMessage());
                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {

                                            Log.d(TAG, "response: " + response.toString());
                                            String string = response.body().string();
                                            Log.i("info",string+"");
                                            try {
                                                JSONObject json = new JSONObject(string);
                                                String code = json.optString("code");
                                                data = json.optString("data");
                                                message = json.optString("message");
                                                if (code.equals("200")){
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toast.makeText(RegisterActivity.this, data, Toast.LENGTH_SHORT).show();
                                                            reg_username.setText("");
                                                            reg_password.setText("");
                                                            reg_password2.setText("");
                                                        }
                                                    });
                                                }else {
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            if (TextUtils.isEmpty(data) || "null".equals(data)){
                                                                if (!TextUtils.isEmpty(message)){
                                                                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                                                                }
                                                            }else {
                                                                Toast.makeText(RegisterActivity.this, data, Toast.LENGTH_SHORT).show();
                                                            }

                                                        }
                                                    });

                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });


                                }
                            }).start();
                        } else {
                            Toast.makeText(RegisterActivity.this, "两次输入的密码不一样", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case R.id.reg_btn_login:
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    break;

            }
        }
    }
}

