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
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.englishlearn.constant.ApiConstant.BASE_URL;

/**
 * Created by bianbian on 2021/5/8.
 */

public class UpdateActivity1 extends AppCompatActivity {

    private EditText mEditText;
    private Button mButton;
    String type;
    String data;
    String message;
    String admin_id;
    String height;
    String weight;
    String age;
    private static final String TAG = "UpdateActivity1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_1);
        type = getIntent().getStringExtra("type");

        admin_id = getIntent().getStringExtra("admin_id");
        height = getIntent().getStringExtra("height");
        weight = getIntent().getStringExtra("weight");
        age = getIntent().getStringExtra("age");
        mEditText = findViewById(R.id.et);
        mButton = findViewById(R.id.btn);

        if ("1".equals(type)){
            mEditText.setHint("请输入身高");
        }else if ("2".equals(type)){
            mEditText.setHint("请输入体重");
        }else if ("3".equals(type)){
            mEditText.setHint("请输入年龄");
        }
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        final String url = BASE_URL+"/fm/user/edit";
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                                OkHttpClient okHttpClient = new OkHttpClient();
                                JSONObject json = new JSONObject();
                                try {
                                    json.put("admin_id", admin_id);
                                    if ("1".equals(type)) {
                                        json.put("height", mEditText.getText().toString());
                                    } else if ("2".equals(type)) {
                                        json.put("weight", mEditText.getText().toString());
                                    } else if ("3".equals(type)) {
                                        json.put("age", mEditText.getText().toString());
                                    }

                                } catch (JSONException e) {

                                }
                                RequestBody requestBody = RequestBody.create(JSON, String.valueOf(json));
                                Request request = new Request.Builder()
                                        .url(url)
                                        .addHeader("Content-Type", "application/json")
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
                                        Log.i("info", string + "");
                                        try {
                                            JSONObject json = new JSONObject(string);
                                            String code = json.optString("code");
                                            data = json.optString("data");
                                            message = json.optString("message");
                                            if (code.equals("200")) {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
//                                                        Toast.makeText(UpdateActivity1.this, data, Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(UpdateActivity1.this, UpdateActivity.class);
                                                        intent.putExtra("admin_id", admin_id);
                                                        startActivity(intent);
                                                    }
                                                });
                                            } else {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        if (TextUtils.isEmpty(data) || "null".equals(data)) {
                                                            if (!TextUtils.isEmpty(message)) {
                                                                Toast.makeText(UpdateActivity1.this, message, Toast.LENGTH_SHORT).show();
                                                            }
                                                        } else {
                                                            Toast.makeText(UpdateActivity1.this, data, Toast.LENGTH_SHORT).show();
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
                });
    }
}
