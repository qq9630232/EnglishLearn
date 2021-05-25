package com.example.englishlearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.englishlearn.bean.VideoBean;

import java.util.List;

/**
 * Created by bianbian on 2021/5/9.
 */

public class WAdapter extends BaseAdapter {


    public WAdapter(List<VideoBean.DataBean> wordBeanList, Context context) {
        mWordBeanList = wordBeanList;
        mContext = context;
    }

    private List<VideoBean.DataBean> mWordBeanList;
    private Context mContext;
    @Override
    public int getCount() {
        return mWordBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mWordBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载布局为一个视图
        View view =  LayoutInflater.from(mContext).inflate(R.layout.listview_item,null);
        VideoBean.DataBean mStudentData = mWordBeanList.get(position);

        //在view 视图中查找 组件
        TextView tv_name = (TextView) view.findViewById(R.id.tv);

        //为Item 里面的组件设置相应的数据
        tv_name.setText(mStudentData.getTitle());

        //返回含有数据的view
        return view;
    }
}
