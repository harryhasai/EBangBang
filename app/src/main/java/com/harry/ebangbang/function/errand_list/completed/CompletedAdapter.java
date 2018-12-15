package com.harry.ebangbang.function.errand_list.completed;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;
import com.harry.ebangbang.network.entity.ErrandListEntity;

/**
 * Created by Harry on 2018/12/15.
 */
public class CompletedAdapter extends BaseQuickAdapter<ErrandListEntity.DataBean, BaseViewHolder> {

    public CompletedAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ErrandListEntity.DataBean item) {
        helper.setText(R.id.tv_order_number, "订单号:" + item.orderNumber)
                .setText(R.id.tv_send_address, "发件地址: " + item.collect)
                .setText(R.id.tv_remark, "备注信息: " + item.remark)
                .setText(R.id.tv_price, "¥" + item.practicalMoney)
                .setText(R.id.tv_receive_address, "收件地址: " + item.addressee);
        helper.setGone(R.id.tv_rider_location, false)
                .setGone(R.id.tv_call_rider, false);
    }
}
