package com.bwie.xiaqin.yuekao20181025lx.fenlei.leftpresenter;

import com.bwie.xiaqin.yuekao20181025lx.bean.FenRightBean;
import com.bwie.xiaqin.yuekao20181025lx.fenlei.inter.RightView;
import com.bwie.xiaqin.yuekao20181025lx.fenlei.leftmodel.RightModel;
import com.bwie.xiaqin.yuekao20181025lx.fragment.FenFragment;
import com.bwie.xiaqin.yuekao20181025lx.inter.ICallBack;
import com.bwie.xiaqin.yuekao20181025lx.mvp.view.IView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by lenovo on 2018/10/25.
 */

public class RightPresenter {
    private RightView iv;
    private RightModel rightModel;
    public void attch(FenFragment iv){
        this.iv = iv;
        rightModel = new RightModel();
    }
    public void getright(String url){
        Type type = new TypeToken<FenRightBean>(){}.getType();
        rightModel.getrights(url, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                FenRightBean rightBean = (FenRightBean) obj;
                if (rightBean!=null){
                    iv.getrights(rightBean.getData());
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
