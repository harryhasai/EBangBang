package com.harry.ebangbang.function.order_manage;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.network.entity.OrderManageEntity;
import com.harry.ebangbang.utils.SPUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Harry on 2018/12/11.
 */
public class OrderManageAdapter extends BaseQuickAdapter<OrderManageEntity.DataBean, BaseViewHolder> {

    public OrderManageAdapter(int layoutResId) {
        super(layoutResId);
    }

    public OrderManageAdapter(int layoutResId, @Nullable List<OrderManageEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderManageEntity.DataBean item) {
        helper.setText(R.id.tv_shop_name, item.shopName)
                .setText(R.id.tv_total_price, "¥" + item.actualMoney);

        //订单状态  -1未付款 1 待配送  4待收货  5 订单已完成未评价   6退返货   7完成并已评价
        switch (item.orderFormStatus) {
            case -1://1未付款
                helper.setText(R.id.tv_function, "待付款");
                break;
            case 1://商家已接单, 骑手未接单
                helper.setText(R.id.tv_function, "商家已接单");
                break;
            case 0://商家接单中
                helper.setText(R.id.tv_function, "商家接单中");
                break;
            case 2://待配送
                helper.setText(R.id.tv_function, "待配送");
                break;
            case 4://待收货
                helper.setText(R.id.tv_function, "待收货");
                break;
            case 5://订单已完成未评价
                helper.setText(R.id.tv_function, "待评价");
                break;
            case 6://6退返货
                helper.setText(R.id.tv_function, "退换货");
                break;
            case 7:
                helper.setText(R.id.tv_function, "已评价");
                break;
        }

        LinearLayout llContainer = helper.getView(R.id.ll_container);
        llContainer.removeAllViews();
        for (int i = 0; i < item.goods.size(); i++) {
            OrderManageEntity.DataBean.GoodsBean goodsBean = item.goods.get(i);
            View view = View.inflate(mContext, R.layout.item_order_manage_child, null);
            ImageView ivFoodImg = view.findViewById(R.id.iv_food_img);
            TextView tvFoodName = view.findViewById(R.id.tv_food_name);
            TextView tvFoodPrice = view.findViewById(R.id.tv_food_price);
            TextView tvFoodCount = view.findViewById(R.id.tv_food_count);

            Picasso.with(mContext)
                    .load(SPUtils.getString(UserInfo.HEADER_BASE.name(), "") + goodsBean.imgLink)
                    .error(R.drawable.ic_error)
                    .placeholder(R.drawable.ic_place_holder)
//                .transform(new PicassoCircleTransform())
                    .resize(ConvertUtils.dp2px(34), ConvertUtils.dp2px(34))
                    .centerCrop()
                    .into(ivFoodImg);
            tvFoodName.setText(goodsBean.name);
            tvFoodPrice.setText("¥" + goodsBean.price);
            tvFoodCount.setText("x " + goodsBean.num);

            llContainer.addView(view);
        }
    }
}
