package chenhong.com.oschina.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import chenhong.com.oschina.ui.holder.BaseHolder;

/**
 * Created by Administrator on 2016/6/20.
 */
public abstract class MyBaseListAdapter<T> extends BaseAdapter {

    private List<T> data;


    //定义传入数据列的类型
    public MyBaseListAdapter(List<T> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
   }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder;
        if (convertView == null) {
            holder = getHolder(position);
        }else {
            holder= (BaseHolder) convertView.getTag();
        }
        holder.setData(getItem(position));
       return  holder.getmRootView();
    }

    //返回当前页面的holder对象
    public abstract BaseHolder<T> getHolder(int position);



}