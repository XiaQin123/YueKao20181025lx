package com.bwie.xiaqin.yuekao20181025lx.fenlei.inter;

import com.bwie.xiaqin.yuekao20181025lx.bean.FenRightBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/25.
 */

public interface RightView {
    void getrights(List<FenRightBean.DataBean> list);
    void failed(Exception e);
}
