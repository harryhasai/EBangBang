package com.harry.ebangbang.function.search;

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
import com.harry.ebangbang.network.entity.HomeEntity;
import com.harry.ebangbang.network.entity.SearchEntity;
import com.harry.ebangbang.utils.SPUtils;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Harry on 2018/12/3.
 */
public class SearchAdapter extends BaseQuickAdapter<SearchEntity.DataBean, BaseViewHolder> {

    public SearchAdapter(int layoutResId, @Nullable List<SearchEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchEntity.DataBean item) {
        ImageView ivShopImg = helper.getView(R.id.iv_shop_img);
        Picasso.with(mContext)
                .load(SPUtils.getString(UserInfo.HEADER_BASE.name(), "") + item.logo)
                .error(R.drawable.ic_error)
//                .transform(new PicassoCircleTransform())
                .resize(ConvertUtils.dp2px(60), ConvertUtils.dp2px(60))
                .centerCrop()
                .into(ivShopImg);
        if (item.meetMinus.size() != 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < item.meetMinus.size(); i++) {
                SearchEntity.DataBean.MeetMinusBean bean = item.meetMinus.get(i);
                String s = "满" + bean.meet + "立减" + bean.minus;
                if (i == (item.meetMinus.size() - 1)) {
                    builder.append(s);
                } else {
                    builder.append(s).append(",");
                }
            }
            helper.setText(R.id.tv_shop_event, builder);
        } else {
            helper.setVisible(R.id.tv_shop_event, false);
        }
        String distance;
        if (item.distance < 1) {
            if (item.distance < 0.5) {
                distance = "<500m";
            } else {
                distance = ">500m";
            }
        } else {
            BigDecimal b = new BigDecimal(item.distance);
            distance = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue() + "km";
        }
        helper.setText(R.id.tv_shop_name, item.shopName)
                .setText(R.id.tv_shop_distance, distance);


        LinearLayout llContainer = helper.getView(R.id.ll_container);
        llContainer.removeAllViews();
        for (int i = 0; i < item.goods.size(); i++) {
            SearchEntity.DataBean.GoodsBean goodsBean = item.goods.get(i);

            View view = View.inflate(mContext, R.layout.item_search_child, null);
            ImageView ivFoodImg = view.findViewById(R.id.iv_food_img);
            TextView tvFoodName = view.findViewById(R.id.tv_food_name);
            TextView tvFoodPrice = view.findViewById(R.id.tv_food_price);
            TextView tvFoodSalesVolume = view.findViewById(R.id.tv_food_sales_volume);

            Picasso.with(mContext)
                    .load(SPUtils.getString(UserInfo.HEADER_BASE.name(), "") + goodsBean.imgLink)
                    .error(R.drawable.ic_error)
//                .transform(new PicassoCircleTransform())
                    .resize(ConvertUtils.dp2px(40), ConvertUtils.dp2px(40))
                    .centerCrop()
                    .into(ivFoodImg);
            tvFoodName.setText(goodsBean.goodsName);
            tvFoodPrice.setText("¥" + goodsBean.price);
            tvFoodSalesVolume.setText("月售" + goodsBean.goodsSalesVolume);

            llContainer.addView(view);
        }
    }
}
