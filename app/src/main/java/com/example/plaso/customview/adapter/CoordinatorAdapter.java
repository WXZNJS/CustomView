
package com.example.plaso.customview.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.plaso.customview.R;

import java.util.List;

/**
 * Created by zhhang on 2018/8/22.
 */

public class CoordinatorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context mContext;
    public static final int LOAD_MORE = 10;
    public static final int NORMAL = 11;
    public static final int FOOTER = 12;
    List<Integer> dataList;
    public CoordinatorAdapter(Context mContetx,List<Integer> dataList){
        this.mContext = mContetx;
        this.dataList = dataList;
    }

    public void setData(List<Integer> dataList){
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void refreshData(){
        this.dataList.clear();
        notifyDataSetChanged();
    }


    public List<Integer> getData(){
        return dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == FOOTER){
            View view = LayoutInflater.from(mContext).inflate(R.layout.recycleview_footer_layout, parent, false);
            return new FooterViewHolder(view);
        }else if(viewType == LOAD_MORE){
            View view = LayoutInflater.from(mContext).inflate(R.layout.recycleview_load_more_layout, parent, false);
            return new LoadMoreViewHolder(view);
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.coordinator_item_layout, parent, false);
            return new myViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof myViewHolder){
            myViewHolder myHolder = (myViewHolder)holder;
            myHolder.textView.setText("第"+position+"个item");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(isNoMoreData && position == dataList.size()){
            return FOOTER;
        }else if(position == dataList.size()){
            return LOAD_MORE;
        }else {
            return NORMAL;
        }
    }


    @Override
    public int getItemCount() {
        return dataList.size()<10 ? dataList.size() : dataList.size()+1;
    }

    boolean isNoMoreData = false;
    public void setNoNoreData(boolean isNoMore){
        isNoMoreData = isNoMore;
        notifyDataSetChanged();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public myViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder{
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class LoadMoreViewHolder extends RecyclerView.ViewHolder{
        public LoadMoreViewHolder(View itemView) {
            super(itemView);
        }
    }
}
