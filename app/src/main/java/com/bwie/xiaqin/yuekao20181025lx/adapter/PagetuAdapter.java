package com.bwie.xiaqin.yuekao20181025lx.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bwie.xiaqin.yuekao20181025lx.bean.PagetuBean;
import com.bwie.xiaqin.yuekao20181025lx.utils.StringUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo on 2018/10/25.
 */

public class PagetuAdapter extends PagerAdapter {
    private Context context;
    private List<PagetuBean.DataBean> data;

    public PagetuAdapter(Context context, List<PagetuBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView imageView = new ImageView(context);
        imageView.setScaleType(imageView.getScaleType().FIT_XY);

        PagetuBean.DataBean dataBean1 = data.get(position % data.size());
//如果这个地方报错,就强转http在utils里面找StringUtils
        String icon = dataBean1.getIcon();
        Log.e("TAG", "instantiateItem: "+icon);
        String s = StringUtils.http2Http(icon);
        Glide.with(context).load(s).into(imageView);
//        Picasso.with(context).load(icon).into(imageView);
        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //一定要删除 super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
