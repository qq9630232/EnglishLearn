package com.example.englishlearn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.englishlearn.List1Activity;
import com.example.englishlearn.MainActivity;
import com.example.englishlearn.R;

public class WordLearnActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 背单词
     */
    private Button mBtBei;
    /**
     * 练听力
     */
    private Button mBtListen;
    /**
     * 看阅读
     */
    private Button mBtLook;
    private String adminId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_learn);
        initView();
        adminId = getIntent().getStringExtra("admin_id");

    }

    private void initView() {
        mBtBei = (Button) findViewById(R.id.bt_bei);
        mBtBei.setOnClickListener(this);
        mBtListen = (Button) findViewById(R.id.bt_listen);
        mBtListen.setOnClickListener(this);
        mBtLook = (Button) findViewById(R.id.bt_look);
        mBtLook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_bei:
                Intent intent = new Intent(this, ReciteActivity.class);
                intent.putExtra("admin_id", adminId);
                startActivity(intent);
                break;
            case R.id.bt_listen:

                break;
            case R.id.bt_look:
                Intent bIntent = new Intent(this, BookListActivity.class);
                startActivity(bIntent);
                break;
        }
    }
}
