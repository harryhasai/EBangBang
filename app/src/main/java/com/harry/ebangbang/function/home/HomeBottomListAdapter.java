package com.harry.ebangbang.function.home;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.network.entity.HomeEntity;
import com.harry.ebangbang.utils.SPUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Harry on 2018/11/20.
 */
public class HomeBottomListAdapter extends BaseQuickAdapter<HomeEntity.DataBean, BaseViewHolder> {

    public HomeBottomListAdapter(int layoutResId, @Nullable List<HomeEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeEntity.DataBean item) {
        if (helper.getAdapterPosition() == 0) {
            helper.setGone(R.id.tv_text, true);
        }
        helper.setText(R.id.tv_business, item.name)
                .setText(R.id.tv_start_price, "起送价 ¥ " + item.upToPar)
//                .setText(R.id.tv_freight, "配送费 ¥ " +item.upToPar)
                .setText(R.id.tv_sales_volume, "月售" + item.monthlySales);
        ImageView ivFoodImg = helper.getView(R.id.iv_food_img);
        Picasso.with(mContext)
                .load(SPUtils.getString(UserInfo.HEADER_BASE.name(), "") + item.logo)
                .error(R.drawable.ic_error)
//                .transform(new PicassoCircleTransform())
                .resize(ConvertUtils.dp2px(88), ConvertUtils.dp2px(88))
                .centerCrop()
                .into(ivFoodImg);

        if (item.meetMinus.size() != 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < item.meetMinus.size(); i++) {
                HomeEntity.DataBean.MeetMinusBean bean = item.meetMinus.get(i);
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
