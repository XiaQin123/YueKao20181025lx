package com.bwie.xiaqin.yuekao20181025lx.fenlei.leftadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.xiaqin.yuekao20181025lx.R;
import com.bwie.xiaqin.yuekao20181025lx.bean.FenRightBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/25.
 */

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {
    private Context context;
    private List<FenRightBean.DataBean.ListBean> list;

    public RightAdapter(Context context, List<FenRightBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.fenitem_right,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RightAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getIcon()).into(holder.imageright);
        holder.textright.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageright;
        private TextView textright;
        public ViewHolder(View itemview){
            super(itemview);
            imageright = itemview.findViewById(R.id.img_right);
            textright = itemview.findViewById(R.id.txt_right);
        }
    }
}
