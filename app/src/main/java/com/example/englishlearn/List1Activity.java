package com.example.englishlearn;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishlearn.activity.VideoPlayActivity;
import com.example.englishlearn.bean.VideoBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.englishlearn.constant.ApiConstant.BASE_URL;

/**
 * Created by bianbian on 2021/5/9.
 */

public class List1Activity extends AppCompatActivity implements View.OnClickListener {

    String type;
    private TextView mTextView;
    private ListView mListView;
    private String data;
    MediaPlayer mediaPlayer;
    String url;
    private static final String TAG = "List1Activity";
    List<VideoBean.DataBean> mWordBeans = new ArrayList<>();
    WAdapter mWAdapter;
    private ImageView mImgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_1);
        initView();
        type = getIntent().getStringExtra("type");
        mTextView = findViewById(R.id.tv);
        mListView = findViewById(R.id.list);
        if ("1".equals(type)) {
            mTextView.setHint("单词源");
            url = BASE_URL + "/fm/data/word/list";
        } else if ("2".equals(type)) {
            mTextView.setHint("视频源");
            url = BASE_URL + "/fm/data/video/list";
        }


        new Thread(new Runnable() {
            @Override
            public void run() {

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                OkHttpClient okHttpClient = new OkHttpClient();
                JSONObject json = new JSONObject();
                RequestBody requestBody = RequestBody.create(JSON, String.valueOf(json));


                Request request = new Request.Builder()
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
                    public void onResponse(Call call, Response response) throws IOException {

                        Log.d(TAG, "response: " + response.toString());
                        String string = response.body().string();
                        Log.i("info", string + "");
                        try {
                            JSONObject json = new JSONObject(string);
                            String code = json.optString("code");
                            data = json.optString("data");
                            if (type.equals("2")) {
                                if (code.equals("200")) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
//                                        Toast.makeText(UpdateActivity.this, data, Toast.LENGTH_SHORT).show();
                                            mWordBeans = new Gson().fromJson(data, new TypeToken<List<VideoBean.DataBean>>() {
                                            }.getType());
                                            mWAdapter = new WAdapter(mWordBeans, List1Activity.this);
                                            mListView.setAdapter(mWAdapter);
                                            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                    if ("1".equals(type)) {
                                                        mediaPlayer = new MediaPlayer();
                                                        try {
                                                            mediaPlayer.setDataSource(mWordBeans.get(position).getUrl());
                                                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                                            mediaPlayer.prepare();
                                                            mediaPlayer.start();
                                                        } catch (IOException e) {
                                                            e.printStackTrace();
                                                        }
                                                    } else {
                                                        Intent intent = new Intent(List1Activity.this, VideoPlayActivity.class);
                                                        intent.putExtra("url", mWordBeans.get(position).getUrl() + "");

//                                                    String s = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
//                                                    intent.putExtra("url", s);
                                                        startActivity(intent);
                                                    }

                                                }
                                            });
                                        }
                                    });
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(List1Activity.this, data, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
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
    protected void onDestroy() {
        if (null != mediaPlayer) {
            mediaPlayer.reset();     //重置MediaPlayer
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    private void initView() {
        mImgBack = (ImageView) findViewById(R.id.img_back);
        mImgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }
}
