package com.example.englishlearn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.englishlearn.R;
import com.example.englishlearn.adapter.BookListAdapter;
import com.example.englishlearn.bean.BookBean;
import com.example.englishlearn.utils.Network.OnRequestResult;
import com.example.englishlearn.utils.Network.RestApi;
import com.google.gson.Gson;

import java.util.List;

public class BookListActivity extends AppCompatActivity implements OnRequestResult {
    private static final String TAG = "BookListActivity";

    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        initView();
        RestApi.getInstance().post("/fm/data/book/list", "",this);
    }

    private void initView() {
        mList = (ListView) findViewById(R.id.list);
    }

    @Override
    public void onSuccess(String json) {
        BookBean bookBean = new Gson().fromJson(json, BookBean.class);
        final List<BookBean.DataBean> data = bookBean.getData();
        BookListAdapter bookListAdapter = new BookListAdapter(data, this);
        mList.setAdapter(bookListAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0){
                    BookBean.DataBean dataBean = data.get(position);
                    Intent intent = new Intent(BookListActivity.this, BookDetailActivity.class);
                    String json1 = new Gson().toJson(dataBean);
                    intent.putExtra("data", dataBean);
                    startActivity(intent);
                }
            }
        });
        Log.e(TAG, "onSuccess: "+json );
    }

    @Override
    public void onFail() {

    }

    @Override
    public void netUnlink() {

    }
}
