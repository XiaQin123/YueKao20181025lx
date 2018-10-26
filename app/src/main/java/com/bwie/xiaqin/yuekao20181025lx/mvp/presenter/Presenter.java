package com.bwie.xiaqin.yuekao20181025lx.mvp.presenter;

import android.util.Log;

import com.bwie.xiaqin.yuekao20181025lx.bean.PagetuBean;
import com.bwie.xiaqin.yuekao20181025lx.inter.ICallBack;
import com.bwie.xiaqin.yuekao20181025lx.mvp.model.Model;
import com.bwie.xiaqin.yuekao20181025lx.mvp.view.IView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by lenovo on 2018/10/25.
 */

public class Presenter {
    private IView iv;
    private Model model;


    public void attach(IView iv) {
        this.iv = iv;
        model = new Model();
    }

    public void detach() {//内存溢出
        if (iv != null) {
            iv = null;
        }
    }
    public void Banner(String url) {

        final Type type = new TypeToken<PagetuBean>() {
        }.getType();
        model.login(url, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                PagetuBean banner = (PagetuBean) obj;
                if (banner != null) {
                    List<PagetuBean.DataBean> data = banner.getData();
                    Log.i("12432545", "success: "+data.size());
                    iv.success(data);
                }
            }

            @Override
            public void onFailed(Exception e) {
                iv.failed(e);
            }
        }, type);
    }

}
