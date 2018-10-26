package com.bwie.xiaqin.yuekao20181025lx.fenlei.inter;

import com.bwie.xiaqin.yuekao20181025lx.bean.FenLeftBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/25.
 */

public interface View {
    void getleft(List<FenLeftBean.DataBean> list);
    void failed(Exception e);
}
