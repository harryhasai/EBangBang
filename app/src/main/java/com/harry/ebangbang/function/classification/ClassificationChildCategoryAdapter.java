package com.harry.ebangbang.function.classification;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.network.entity.HomeEntity;
import com.harry.ebangbang.network.entity.SecondCategoryEntity;
import com.harry.ebangbang.utils.PicassoCircleTransform;
import com.harry.ebangbang.utils.SPUtils;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Harry on 2018/11/22.
 */
public class ClassificationChildCategoryAdapter extends BaseQuickAdapter<SecondCategoryEntity.DataBean, BaseViewHolder> {

    public ClassificationChildCategoryAdapter(int layoutResId, @Nullable List<SecondCategoryEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SecondCategoryEntity.DataBean item) {
        ImageView ivFoodImg = helper.getView(R.id.iv_food_img);
        Picasso.with(mContext)
                .load(SPUtils.getString(UserInfo.HEADER_BASE.name(), "") + item.Logo)
                .error(R.drawable.ic_error)
//                .transform(new PicassoCircleTransform())
                .resize(ConvertUtils.dp2px(103), ConvertUtils.dp2px(103))
                .centerCrop()
                .into(ivFoodImg);

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
        helper.setText(R.id.tv_name, item.name)
                .setText(R.id.tv_category, item.className)
                .setText(R.id.tv_distance, distance)
                .setText(R.id.tv_address, item.siteDetail);

        if (item.meetMinus.size() != 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < item.meetMinus.size(); i++) {
                SecondCategoryEntity.DataBean.MeetMinusBean bean = item.meetMinus.get(i);
                String s = "满" + bean.meet + "立减" + bean.minus;
                if (i == (item.meetMinus.size() - 1)) {
                    builder.append(s);
                } else {
                    builder.append(s).append(",");
                }
            }
            helper.setText(R.id.tv_event, builder);
        } else {
            helper.setVisible(R.id.tv_event, false);
        }
    }
}
