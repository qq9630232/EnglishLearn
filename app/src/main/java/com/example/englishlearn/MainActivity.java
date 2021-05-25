package com.example.englishlearn;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishlearn.activity.WordLearnActivity;
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

public class MainActivity extends AppCompatActivity {
    private TextView tv_name;
    private Button btn_update;
    private Button btn_word;
    private Button btn_vedio;
    private String admin_id;
    private String data;
    private static final String TAG = "yfg";
    UserBean userBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv_name = findViewById(R.id.tv_name);
        btn_update = findViewById(R.id.btn_update);
        btn_word = findViewById(R.id.btn_word);
        btn_vedio = findViewById(R.id.btn_video);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, PasswordEditActivity.class);
                intent.putExtra("admin_id", admin_id);
                startActivity(intent);

            }
        });
        btn_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WordLearnActivity.class);
                intent.putExtra("admin_id", admin_id);
                startActivity(intent);
            }
        });
        btn_vedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List1Activity.class);
                intent.putExtra("type", "2");
                startActivity(intent);
            }
        });
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
//                                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                                         userBean = new Gson().fromJson(data,UserBean.class);
                                        tv_name.setText(userBean.getName());
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
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
}
