package com.bwie.xiaqin.yuekao20181025lx.fenlei.leftpresenter;

import com.bwie.xiaqin.yuekao20181025lx.bean.FenLeftBean;
import com.bwie.xiaqin.yuekao20181025lx.fenlei.inter.View;
import com.bwie.xiaqin.yuekao20181025lx.fenlei.leftmodel.LeftModel;
import com.bwie.xiaqin.yuekao20181025lx.inter.ICallBack;
import com.bwie.xiaqin.yuekao20181025lx.mvp.view.IView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by lenovo on 2018/10/25.
 */

public class LeftPresenter {
    private View iv;
    private LeftModel leftModel;
    public void attch(final View iv){
        this.iv = iv;
        leftModel = new LeftModel();
    }
    public void getleft(){
        Type type = new TypeToken<FenLeftBean>(){}.getType();
        leftModel.getlefts("http://www.zhaoapi.cn/product/getCatagory", new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                FenLeftBean leftBean = (FenLeftBean) obj;
                if (leftBean!=null){
                    iv.getleft(leftBean.getData());
                }
            }

            @Override
            public void onFailed(Exception e) {
                iv.failed(e);
            }
        },type);
    }
    public void detach(){
        if (iv !=null){
            iv = null;
        }
    }
}
