
package com.example.plaso.customview.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.plaso.customview.R;

/**
 * Created by zhhang on 2018/8/22.
 */

public class CoordinatorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context mContext;
    public static final int FOOTOR = 10;
    public static final int NORMAL = 11;
    public CoordinatorAdapter(Context mContetx){
        this.mContext = mContetx;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == FOOTOR){
            View view = LayoutInflater.from(mContext).inflate(R.layout.recycleview_foot_layout, parent, false);
            return new FootViewHolder(view);
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
        if(position == 10){
            return FOOTOR;
        }else {
            return NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return 10+1;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public myViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
        }
    }

    public class FootViewHolder extends RecyclerView.ViewHolder{
        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }
}
