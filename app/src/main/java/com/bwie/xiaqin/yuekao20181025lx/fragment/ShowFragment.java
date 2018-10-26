package com.bwie.xiaqin.yuekao20181025lx.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwie.xiaqin.yuekao20181025lx.LushibujuActivity;
import com.bwie.xiaqin.yuekao20181025lx.R;
import com.bwie.xiaqin.yuekao20181025lx.adapter.PagetuAdapter;
import com.bwie.xiaqin.yuekao20181025lx.bean.PagetuBean;
import com.bwie.xiaqin.yuekao20181025lx.mvp.presenter.Presenter;
import com.bwie.xiaqin.yuekao20181025lx.mvp.view.IView;

import java.util.List;

public class ShowFragment extends Fragment implements IView<List<PagetuBean.DataBean>>{
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                int current = vp_banner.getCurrentItem();
                current++;
                vp_banner.setCurrentItem(current);
                sendEmptyMessageDelayed(0,2000);
            }
        }
    };
    private ViewPager vp_banner;
    private PagetuAdapter adapter;
    private View view;
    private Presenter presenter;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.show_fragment, container, false);
        view.findViewById(R.id.shousuo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LushibujuActivity.class);
                startActivity(intent);

            }
        });
        vp_banner = view.findViewById(R.id.vp_banner);
         presenter= new Presenter();
        presenter.attach(this);
        presenter.Banner("http://www.zhaoapi.cn/ad/getAd");
        return view;
    }

    @Override
    public void success(List<PagetuBean.DataBean> dataBeans) {
        Toast.makeText(getActivity(), dataBeans.size()+"ssssssssssss", Toast.LENGTH_SHORT).show();
        adapter = new PagetuAdapter(getActivity(),dataBeans);
        vp_banner.setAdapter(adapter);
        handler.sendEmptyMessageDelayed(0,2000);
    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void check(boolean isChecked) {

    }

    @Override
    public void Goto() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter !=null ){
            presenter.detach();//防止内存溢出
        }
    }
}
