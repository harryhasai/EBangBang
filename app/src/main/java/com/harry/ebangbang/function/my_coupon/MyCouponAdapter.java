package com.harry.ebangbang.function.my_coupon;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;
import com.harry.ebangbang.network.entity.MyCouponEntity;
import com.harry.ebangbang.utils.DateFormatUtils;

/**
 * Created by Harry on 2018/12/15.
 */
public class MyCouponAdapter extends BaseQuickAdapter<MyCouponEntity.DataBean, BaseViewHolder> {

    public MyCouponAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCouponEntity.DataBean item) {
        String time = DateFormatUtils.getFormatedDateTime(DateFormatUtils.PATTERN_3, item.couponEndPeriod);
        helper.setText(R.id.tv_name, item.couponName)
                .setText(R.id.tv_money, "¥" + item.couponMoney + "满" + item.couponMeetMoney + "元可用")
                .setText(R.id.tv_time, "有效期至" + time)
                .setText(R.id.tv_remark, item.couponRemarks)
                .addOnClickListener(R.id.tv_submit);
    }
}
