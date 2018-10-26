package com.bwie.xiaqin.yuekao20181025lx.mvp.view;

import android.content.Context;

import com.bwie.xiaqin.yuekao20181025lx.bean.FenLeftBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/25.
 */

public interface IView<T> {
    void success(T t);

    void failed(Exception e);

    String getUsername();

    String getPassword();

    void check(boolean isChecked);
    void Goto();
    Context getContext();


}
