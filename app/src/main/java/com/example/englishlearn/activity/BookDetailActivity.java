package com.example.englishlearn.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.englishlearn.R;
import com.example.englishlearn.bean.BookBean;
import com.google.gson.Gson;

public class BookDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImgBack;
    private TextView mTvName;
    private TextView mTvAuthor;
    private TextView mTvDescription;
    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        initView();
        BookBean.DataBean dataBean  = (BookBean.DataBean) getIntent().getSerializableExtra("data");
        if(dataBean != null){

            mTvName.setText(dataBean.getBookName());
            mTvAuthor.setText("——"+dataBean.getBookUser());
            mTvDescription.setText("\u3000\u3000"+dataBean.getDescription());
            mTvContent.setText(dataBean.getContent());
        }

    }

    private void initView() {
        mImgBack = (ImageView) findViewById(R.id.img_back);
        mImgBack.setOnClickListener(this);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvAuthor = (TextView) findViewById(R.id.tv_author);
        mTvDescription = (TextView) findViewById(R.id.tv_description);
        mTvContent = (TextView) findViewById(R.id.tv_content);
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
