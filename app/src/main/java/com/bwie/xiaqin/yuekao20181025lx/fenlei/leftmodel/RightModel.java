package com.bwie.xiaqin.yuekao20181025lx.fenlei.leftmodel;

import com.bwie.xiaqin.yuekao20181025lx.inter.ICallBack;
import com.bwie.xiaqin.yuekao20181025lx.utils.HttpUtils;

import java.lang.reflect.Type;

/**
 * Created by lenovo on 2018/10/25.
 */

public class RightModel {
    public void getrights(String url, ICallBack callBack, Type type){
        HttpUtils.getInstance().get(url,callBack,type);
    }
}
