package com.example.englishlearn;

import android.content.Intent;
import android.os.Bundle;
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
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import static com.example.englishlearn.constant.ApiConstant.BASE_URL;

public class PasswordEditActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mOldPassword;
    private EditText mEtPassword;
    private String data;
    private String message;
    /**
     * 登录
     */
    private Button mButtonEdit;
    private String adminId;
    private static final String TAG = "PasswordEditActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_edit);
        initView();
    }

    private void initView() {
        adminId = getIntent().getStringExtra("admin_id");
        mOldPassword = (EditText) findViewById(R.id.old_password);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mButtonEdit = (Button) findViewById(R.id.button_edit);
        mButtonEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button_edit:
                String oldPassword = mOldPassword.getText().toString();
                final String newPassword = mEtPassword.getText().toString();
                if(oldPassword.isEmpty()){
                    Toast.makeText(this,"旧密码不得为空",Toast.LENGTH_SHORT);
                }
                if(newPassword.isEmpty()){
                    Toast.makeText(this,"新密码不得为空",Toast.LENGTH_SHORT);
                }
                if(oldPassword.equals(newPassword)){
                    Toast.makeText(this,"新旧密码不意志",Toast.LENGTH_SHORT);
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String url = BASE_URL+"/fm/login/study/edit";

                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        OkHttpClient okHttpClient = new OkHttpClient();
                        JSONObject json = new JSONObject();
                        try {
                            json.put("adminId", adminId);
                            json.put("password", newPassword);
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
                                        Toast.makeText(PasswordEditActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
//                                                            Toast.makeText(LoginActivity.this, data, Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(PasswordEditActivity.this, MainActivity.class);
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
                                                        Toast.makeText(PasswordEditActivity.this, message, Toast.LENGTH_SHORT).show();
                                                    }
                                                }else {
                                                    Toast.makeText(PasswordEditActivity.this, data, Toast.LENGTH_SHORT).show();
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
                break;
        }
    }
}
