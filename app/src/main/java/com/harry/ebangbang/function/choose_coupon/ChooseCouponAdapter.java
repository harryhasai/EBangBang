package com.harry.ebangbang.function.choose_coupon;

import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;
import com.harry.ebangbang.network.entity.ChooseCouponEntity;
import com.harry.ebangbang.utils.DateFormatUtils;

import java.util.List;

/**
 * Created by Harry on 2018/11/30.
 */
public class ChooseCouponAdapter extends BaseQuickAdapter<ChooseCouponEntity.DataBean, BaseViewHolder> {

    public ChooseCouponAdapter(int layoutResId, @Nullable List<ChooseCouponEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChooseCouponEntity.DataBean item) {
        String text = "¥" + item.money;
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new RelativeSizeSpan(0.5f), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        String time = DateFormatUtils.getFormatedDateTime(DateFormatUtils.PATTERN_3, item.endPeriod);
        helper.setText(R.id.tv_over_time, "有效期至" + time)
                .setText(R.id.tv_full_money, "满" + item.meetMoney + "元可用")
                .setText(R.id.tv_reduce_money, spannableString);

    }
}
