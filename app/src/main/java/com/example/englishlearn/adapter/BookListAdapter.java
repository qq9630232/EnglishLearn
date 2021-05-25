package com.example.englishlearn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.englishlearn.R;
import com.example.englishlearn.activity.BookListActivity;
import com.example.englishlearn.bean.BookBean;
import com.example.englishlearn.bean.VideoBean;

import java.util.List;

/**
 * Created by songdechuan on 2021/5/25.
 */

public class BookListAdapter  extends BaseAdapter {

    private List<BookBean.DataBean> dataList;
    private Context mContext;
    public BookListAdapter(List<BookBean.DataBean> dataList, Context context) {
        this.dataList = dataList;
        dataList.add(0,null);
        mContext = context;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载布局为一个视图
        View view =  LayoutInflater.from(mContext).inflate(R.layout.item_book,null);
        //在view 视图中查找 组件
        TextView mTvNum = view.findViewById(R.id.tv_num);
        TextView mTvName = view.findViewById(R.id.tv_name);
        TextView mTvAuthor = view.findViewById(R.id.tv_author);
        TextView mTvDescription = view.findViewById(R.id.tv_description);
        if(position>0){
            BookBean.DataBean dataBean = dataList.get(position);

            //为Item 里面的组件设置相应的数据
            mTvNum.setText(position+"");
            mTvName.setText(dataBean.getBookName());
            mTvAuthor.setText(dataBean.getBookUser());
            mTvDescription.setText(dataBean.getDescription());
        }else {
            mTvNum.setTextSize(25);
            mTvName.setTextSize(25);
            mTvAuthor.setTextSize(25);
            mTvDescription.setTextSize(25);

        }
        //返回含有数据的view
        return view;
    }
}
