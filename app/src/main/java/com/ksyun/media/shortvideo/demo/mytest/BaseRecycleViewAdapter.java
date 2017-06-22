package com.ksyun.media.shortvideo.demo.mytest;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GMARUnity on 2017/2/3.
 */
public abstract class BaseRecycleViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected RecycleViewItemListener itemListener;
    protected List<T> datas = new ArrayList<T>();

    public List<T> getDatas() {
        if (datas == null)
            datas = new ArrayList<T>();
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public void setItemListener(RecycleViewItemListener listener) {
        this.itemListener = listener;
    }


    @Override
    public void onBindViewHolder(VH holder, int position) {
        onBindItemHolder(holder, position);
    }

    //局部刷新关键：带payload的这个onBindViewHolder方法必须实现
    @Override
    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindItemHolder(holder, position);
        } else {
            onBindItemHolder(holder, position, payloads);
        }

    }

    public abstract void onBindItemHolder(VH holder, int position);

    public void onBindItemHolder(VH holder, int position, List<Object> payloads) {

    }

    public void remove(int position) {
        this.datas.remove(position);
        notifyItemRemoved(position);

        if (position != (datas.size())) { // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, this.datas.size() - position);
        }
    }
}
