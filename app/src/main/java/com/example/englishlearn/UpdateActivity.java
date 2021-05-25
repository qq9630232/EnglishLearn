package com.example.englishlearn;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import static com.example.englishlearn.constant.ApiConstant.BASE_URL;

/**
 * Created by 杨方刚 on 2021/5/5.
 */

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mLLHeight;
    private LinearLayout mLLWeight;
    private LinearLayout mLLAge;
    private TextView mTvHeight;;
    private TextView mTvWeight;;
    private TextView mTvAge;
    private String admin_id;
    private String data;
    private static final String TAG = "UpdateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        mLLHeight = findViewById(R.id.ll_height);
        mLLWeight = findViewById(R.id.ll_weight);
        mLLAge = findViewById(R.id.ll_age);
        mTvHeight = findViewById(R.id.tv_height);
        mTvWeight = findViewById(R.id.tv_weight);
        mTvAge = findViewById(R.id.tv_age);
        mLLHeight.setOnClickListener(this);
        mLLWeight.setOnClickListener(this);
        mLLAge.setOnClickListener(this);
        admin_id = getIntent().getStringExtra("admin_id");
        final String url = BASE_URL+"/fm/user/get";
        new Thread(new Runnable() {
            @Override
            public void run() {

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                OkHttpClient okHttpClient = new OkHttpClient();
                JSONObject json = new JSONObject();
                try {
                    json.put("admin_id", admin_id);
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
                        Log.i("info", string + "");
                        try {
                            JSONObject json = new JSONObject(string);
                            String code = json.optString("code");
                            data = json.optString("data");
                            if (code.equals("200")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
//                                        Toast.makeText(UpdateActivity.this, data, Toast.LENGTH_SHORT).show();
                                        UserBean userBean = new Gson().fromJson(data,UserBean.class);
                                        if (!TextUtils.isEmpty(userBean.getHeight()) && !"null".equals(userBean.getHeight())){
                                            mTvHeight.setText(userBean.getHeight());
                                        }
                                        if (!TextUtils.isEmpty(userBean.getAge()) && !"null".equals(userBean.getAge())){
                                            mTvAge.setText(userBean.getAge());
                                        }
                                        if (!TextUtils.isEmpty(userBean.getWeight()) && !"null".equals(userBean.getWeight())){
                                            mTvWeight.setText(userBean.getWeight());
                                        }
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(UpdateActivity.this, data, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.ll_age:
        Intent intent = new Intent(UpdateActivity.this, UpdateActivity1.class);
        intent.putExtra("admin_id", admin_id);
        intent.putExtra("age",mTvAge.getText().toString().trim());
        intent.putExtra("type", "3");
        startActivity(intent);
        break;
    case R.id.ll_weight:
        Intent intent1 = new Intent(UpdateActivity.this, UpdateActivity1.class);
        intent1.putExtra("admin_id", admin_id);
        intent1.putExtra("weight",mTvAge.getText().toString().trim());
        intent1.putExtra("type", "2");
        startActivity(intent1);
        break;
    case R.id.ll_height:
        Intent intent2 = new Intent(UpdateActivity.this, UpdateActivity1.class);
        intent2.putExtra("admin_id", admin_id);
        intent2.putExtra("height",mTvAge.getText().toString().trim());
        intent2.putExtra("type", "1");
        startActivity(intent2);
        break;
}
    }
}
