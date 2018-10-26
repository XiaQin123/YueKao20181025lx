package com.bwie.xiaqin.yuekao20181025lx.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.xiaqin.yuekao20181025lx.R;
import com.bwie.xiaqin.yuekao20181025lx.ShoppMyAddSubView;
import com.bwie.xiaqin.yuekao20181025lx.bean.ShoppBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo on 2018/10/25.
 */

public class ShoppAdapter extends BaseExpandableListAdapter{
    private List<ShoppBean.DataBean> sellerData;
    private Context context;

    public ShoppAdapter(List<ShoppBean.DataBean> sellerData, Context context) {
        this.sellerData = sellerData;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return sellerData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return sellerData.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null){
           groupViewHolder = new GroupViewHolder();
            convertView = View.inflate(context, R.layout.gw_group_view_layout_shopcar, null);
            groupViewHolder.cb_group_item = convertView.findViewById(R.id.cb_group_item);
            groupViewHolder.tv_title_group = convertView.findViewById(R.id.tv_title_group);
            convertView.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        ShoppBean.DataBean dataBean = sellerData.get(groupPosition);
        groupViewHolder.tv_title_group.setText(dataBean.getSellerName());
        boolean b = isCurrentSellerAllProductSelected(groupPosition);
        groupViewHolder.cb_group_item.setChecked(b);

        groupViewHolder.cb_group_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCartListChangeListener != null){
                    onCartListChangeListener.SellerSelectedChange(groupPosition);

                }
            }
        });
        return convertView;
    }
    public void changeCurrentSellerAllProductSelected(int Position,boolean b){
        List<ShoppBean.DataBean.ListBean> list = sellerData.get(Position).getList();
        for (int i=0;i<list.size();i++){
            list.get(i).setSelected(b ? 1 : 0);
        }
    }


    public boolean isCurrentSellerAllProductSelected(int position) {
        List<ShoppBean.DataBean.ListBean> list = sellerData.get(position).getList();
        for (int i=0;i<list.size();i++){
            if (list.get(i).getSelected() == 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public View getChildView(final int groupPosition,final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
       ChildViewHolder childViewHolder = null;
       if (convertView == null){
           childViewHolder = new ChildViewHolder();
           convertView = View.inflate(context, R.layout.gw_child_view_layout_shopcar, null);
           childViewHolder.cb_child_item = convertView.findViewById(R.id.cd_child_item);
           childViewHolder.tv_title_child = convertView.findViewById(R.id.tv_title_child);
           childViewHolder.tv_price_child = convertView.findViewById(R.id.tv_price_child);
           childViewHolder.iv_icon_child = convertView.findViewById(R.id.iv_icon_child);
           childViewHolder.add_sub_view_child = convertView.findViewById(R.id.add_sub_view_child);
           convertView.setTag(childViewHolder);
       }else {
           childViewHolder = (ChildViewHolder) convertView.getTag();

       }
        ShoppBean.DataBean.ListBean listBean = sellerData.get(groupPosition).getList().get(childPosition);
       childViewHolder.tv_title_child.setText(listBean.getTitle());
        String images = listBean.getImages();
        String[] split = images.split("\\|");
//        Picasso.with(context).load(split[0]).into(childViewHolder.iv_icon_child);
        Glide.with(context).load(split[0]).into(childViewHolder.iv_icon_child);
        //商品价格
        childViewHolder.tv_price_child.setText("￥" + listBean.getPrice());
        //商品checkb0x
        childViewHolder.cb_child_item.setChecked(listBean.getSelected() == 1);
        childViewHolder.cb_child_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCartListChangeListener.changeCurrentProductSelected(groupPosition, childPosition);
            }
        });
        childViewHolder.add_sub_view_child.setOnNumberChangeListener(new ShoppMyAddSubView.OnNumberChangeListener(){
            @Override
            public void OnNumberChange(int number) {
                onCartListChangeListener.ProductNumberChange(groupPosition, childPosition, number);
            }
        });
        return convertView;
    }
    public void changeCurrentProductNumber(int groupPosition, int childPosition, int number) {
        ShoppBean.DataBean.ListBean listBean = sellerData.get(groupPosition).getList().get(childPosition);
        listBean.setNum(number);
    }

    public void changeCurrentProductSelected(int groupPosition, int childPosition) {
        ShoppBean.DataBean.ListBean listBean = sellerData.get(groupPosition).getList().get(childPosition);
        listBean.setSelected(listBean.getSelected() == 0 ? 1 : 0);


    }
    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < sellerData.size(); i++) {
            List<ShoppBean.DataBean.ListBean> list = sellerData.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                //只有选中采取计算
                if (list.get(j).getSelected() == 1) {
                    double price = list.get(j).getPrice();
                    int num = list.get(j).getNum();
                    totalPrice += price * num;
                }

            }
        }
        return totalPrice;
    }

    public int calculateTotalNumber() {
        int totalNumber = 0;
        for (int i = 0; i < sellerData.size(); i++) {
            List<ShoppBean.DataBean.ListBean> list = sellerData.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                //只有选中采取计算
                if (list.get(j).getSelected() == 1) {
                    int num = list.get(j).getNum();
                    totalNumber += num;
                }

            }
        }
        return totalNumber;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    public boolean isAllProductsSelected() {
        for (int i = 0; i < sellerData.size(); i++) {
            List<ShoppBean.DataBean.ListBean> list = sellerData.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getSelected() == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    public void changeAllProductsSelected(boolean b) {
        for (int i = 0; i < sellerData.size(); i++) {
            List<ShoppBean.DataBean.ListBean> list = sellerData.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                list.get(j).setSelected(b ? 1 : 0);
            }
        }

    }
    public static class GroupViewHolder{
        public CheckBox cb_group_item;
        public TextView tv_title_group;

    }
    public static class ChildViewHolder{
        public CheckBox cb_child_item;
        public ImageView iv_icon_child;
        public TextView tv_title_child;
        public TextView tv_price_child;
        public ShoppMyAddSubView add_sub_view_child;
    }
    OnCartListChangeListener onCartListChangeListener;
    public void setOnCartListChangeListener(OnCartListChangeListener onCartListChangeListener) {
        this.onCartListChangeListener = onCartListChangeListener;
    }

    public interface OnCartListChangeListener {
        void SellerSelectedChange(int groupPosition);

        void changeCurrentProductSelected(int groupPosition, int childPosition);

        void ProductNumberChange(int groupPosition, int childPosition, int number);
    }
}
