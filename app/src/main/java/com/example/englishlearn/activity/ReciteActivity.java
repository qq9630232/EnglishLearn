package com.example.englishlearn.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishlearn.R;
import com.example.englishlearn.bean.WordBean;
import com.example.englishlearn.bean.WordProgressBean;
import com.example.englishlearn.utils.Network.OnRequestResult;
import com.example.englishlearn.utils.Network.RestApi;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class ReciteActivity extends AppCompatActivity implements View.OnClickListener, OnRequestResult {
    private static final String TAG = "ReciteActivity";
    private TextView mTvWord;
    /**
     * 认识
     */
    private Button mBtRenshi;
    /**
     * 不认识
     */
    private Button mBtBurenshi;
    /**
     * 上一个
     */
    private Button mBtShang;
    /**
     * 下一个
     */
    private Button mBtNext;
    /**
     * 总数:
     */
    private TextView mTvJilu;
    /**
     * 已记住:
     */
    private TextView mTvYi;
    /**
     * 未记住:
     */
    private TextView mTvWei;
    private String adminId;
    private TextView mTvFan;
    private TextView mTvExample;
    private int index = 0;
    List<WordBean.DataBean> dataList = new ArrayList<>();
    private int knowCount;
    private int unKnowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite);
        initView();
        initData();
    }

    private void initData() {
        adminId = getIntent().getStringExtra("admin_id");
        RestApi.getInstance().post("/fm/data/word/list", "", this);

        loadWordProgress();

    }

    private void loadWordProgress() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("adminId",adminId);
        String json = new Gson().toJson(jsonObject);
        RestApi.getInstance().post("/fm/data/progress/get", json, new OnRequestResult() {
            @Override
            public void onSuccess(String json) {
                WordProgressBean wordProgressBean = new Gson().fromJson(json, WordProgressBean.class);
                WordProgressBean.DataBean data = wordProgressBean.getData();
                knowCount = data.getKnowCount();
                unKnowCount = data.getUnKnowCount();
                mTvYi.setText("已记住:"+ knowCount);
                mTvWei.setText("未记住:"+ unKnowCount);
                Log.e(TAG, "onSuccess: "+json);
            }

            @Override
            public void onFail() {

            }

            @Override
            public void netUnlink() {

            }
        });
    }

    private void initView() {
        mTvWord = (TextView) findViewById(R.id.tv_word);
        mBtRenshi = (Button) findViewById(R.id.bt_renshi);
        mBtRenshi.setOnClickListener(this);
        mBtBurenshi = (Button) findViewById(R.id.bt_burenshi);
        mBtBurenshi.setOnClickListener(this);
        mBtShang = (Button) findViewById(R.id.bt_shang);
        mBtShang.setOnClickListener(this);
        mBtNext = (Button) findViewById(R.id.bt_next);
        mBtNext.setOnClickListener(this);
        mTvJilu = (TextView) findViewById(R.id.tv_jilu);
        mTvYi = (TextView) findViewById(R.id.tv_yi);
        mTvWei = (TextView) findViewById(R.id.tv_wei);
        mTvFan = (TextView) findViewById(R.id.tv_fan);
        mTvExample = (TextView) findViewById(R.id.tv_example);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_renshi:
                WordBean.DataBean rsBean = dataList.get(index);
                setWordContent(rsBean.getWordName(),rsBean.getExplanation(),"例子:"+rsBean.getExample());
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("adminId",adminId);
                jsonObject.addProperty("wordId",rsBean.getWordId());
                jsonObject.addProperty("type",1);
                String json = new Gson().toJson(jsonObject);
                RestApi.getInstance().post("/fm/data/word/know", json, new OnRequestResult() {
                    @Override
                    public void onSuccess(String json) {
                        loadWordProgress();

                    }

                    @Override
                    public void onFail() {

                    }

                    @Override
                    public void netUnlink() {

                    }
                });
                break;
            case R.id.bt_burenshi:
                WordBean.DataBean brsBean = dataList.get(index);
                setWordContent(brsBean.getWordName(),brsBean.getExplanation(),"例子:"+brsBean.getExample());
                JsonObject unknowJsonObj = new JsonObject();
                unknowJsonObj.addProperty("adminId",adminId);
                unknowJsonObj.addProperty("wordId",brsBean.getWordId());
                unknowJsonObj.addProperty("type",0);
                String unKnowJson = new Gson().toJson(unknowJsonObj);
                RestApi.getInstance().post("/fm/data/word/unknow", unKnowJson, new OnRequestResult() {
                    @Override
                    public void onSuccess(String json) {
                        loadWordProgress();

                    }

                    @Override
                    public void onFail() {

                    }

                    @Override
                    public void netUnlink() {

                    }
                });
                break;
            case R.id.bt_shang:
                if(index == 0){
                    Toast.makeText(this,"已经是第一个单词了!",Toast.LENGTH_SHORT).show();
                    break;
                }
                index = index - 1;
                setWordContent(dataList.get(index).getWordName(),"","");
                break;
            case R.id.bt_next:
                if(index == dataList.size() - 1){
                    Toast.makeText(this,"已经是最后一个单词了!",Toast.LENGTH_SHORT).show();
                    break;
                }
                index = index + 1;
                setWordContent(dataList.get(index).getWordName(),"","");
                break;
        }
    }

    @Override
    public void onSuccess(String json) {
        WordBean wordBean = new Gson().fromJson(json, WordBean.class);
        List<WordBean.DataBean> data = wordBean.getData();
        dataList.addAll(data);
        WordBean.DataBean dataBean = data.get(0);
        index = 0;
        setWordContent(dataBean.getWordName(),"","");
        mTvWord.setText(dataBean.getWordName());
        mTvJilu.setText("总数:"+data.size());

    }

    private void setWordContent(String wordName, String explanation, String example) {
        mTvWord.setText(wordName);
        mTvFan.setText(explanation);
        mTvExample.setText(example);
    }

    @Override
    public void onFail() {

    }

    @Override
    public void netUnlink() {

    }
}
