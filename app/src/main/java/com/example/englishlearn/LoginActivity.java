package com.example.englishlearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import static com.example.englishlearn.constant.ApiConstant.BASE_URL;


public class LoginActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
    private Button btn_register;
    private static final String TAG = "yfg";
    String data;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.button_login);
        btn_register = (Button) findViewById(R.id.button_register);
        btn_login.setOnClickListener(new MyButton());
        btn_register.setOnClickListener(new MyButton());
    }

    public class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            final String username = et_username.getText().toString().trim();
            final String password = et_password.getText().toString().trim();
            switch (view.getId()) {
                //当点击登录按钮时
                case R.id.button_login:
                    if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                        Toast.makeText(LoginActivity.this, "密码或账号不能为空", Toast.LENGTH_SHORT).show();
                    } else {

                            final String url = BASE_URL+"/fm/login/study/login";
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


                                    okhttp3.Request request = new okhttp3.Request.Builder()
                                            .url(url)
                                            .post(requestBody)
                                            .build();

                                    Call call = okHttpClient.newCall(request);
                                    call.enqueue(new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {
                                            Log.d(TAG, "response: " + e.getMessage());
                                        }

                                        @Override
                                        public void onResponse(Call call, okhttp3.Response response) throws IOException {

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
//                                                            Toast.makeText(LoginActivity.this, data, Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                            intent.putExtra("admin_id",data);
                                                            startActivity(intent);
                                                        }
                                                    });
                                                }else {
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            if (TextUtils.isEmpty(data) || "null".equals(data)){
                                                                if (!TextUtils.isEmpty(message)){
                                                                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                                                }
                                                            }else {
                                                                Toast.makeText(LoginActivity.this, data, Toast.LENGTH_SHORT).show();
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

                    }
                    break;
                //当点击注册按钮事件时
                case R.id.button_register:
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    break;

            }
        }
    }
}

