package com.demo.store.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.demo.store.holder.BaseHolder;
import com.demo.store.holder.MoreHolder;

import java.util.List;

/**
 * Created by GSJ
 * Date: 2016/10/23
 * Time: 11:01
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private List<T> data;
    private ListView mListView;
    private final int ITEM_VIEW_TYPE=1;
    private final int MORE_VIEW_TYPE=0;
    private BaseHolder holder;

    public MyBaseAdapter(List<T> data, ListView mListView) {
        this.data=data;
        this.mListView=mListView;
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
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            // 更多的条目
            return MORE_VIEW_TYPE;
        } else {
            // 普通的条目
            return getInnerItemViewType(position);
        }
    }

    private int getInnerItemViewType(int position) {
        return ITEM_VIEW_TYPE;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount()+1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
             if(getItemViewType(position)==MORE_VIEW_TYPE){
                 holder = getMoreHolder();
             }else{
                  holder=getHolder();
             }
        }else{
            holder = (BaseHolder) convertView.getTag();

        }
        if(getItemViewType(position)!=MORE_VIEW_TYPE){
               holder.setData(data.get(position));
        }


        return holder.getRootView();
    }

    protected abstract BaseHolder getHolder();

    private   MoreHolder moreHolder = null;
    private BaseHolder getMoreHolder() {
        if(moreHolder==null){
            moreHolder = new MoreHolder(hasMore(), this);
        }

        return moreHolder;
    }

    private boolean hasMore() {

        return  true;
    }
}
