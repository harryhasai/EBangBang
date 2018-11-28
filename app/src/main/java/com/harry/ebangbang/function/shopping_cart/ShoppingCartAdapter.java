package com.harry.ebangbang.function.shopping_cart;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;
import com.harry.ebangbang.network.entity.ShoppingCartEntity;

import java.util.List;

/**
 * Created by Harry on 2018/11/23.
 */
public class ShoppingCartAdapter extends BaseQuickAdapter<ShoppingCartEntity.DataBean, BaseViewHolder> {

    public ShoppingCartAdapter(int layoutResId, @Nullable List<ShoppingCartEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, ShoppingCartEntity.DataBean item) {
        helper.setText(R.id.tv_name, item.shopName)
                .setText(R.id.tv_total_price, "合计:¥" + item.total)
                .setText(R.id.tv_box_price, "¥" + item.totalBox);
        helper.addOnClickListener(R.id.iv_cancel)
                .addOnClickListener(R.id.btn_settle_accounts);

        if (item.meet != 0) {
            helper.setText(R.id.tv_discount, "已享受满" + item.meet + "减" + item.minus + "元的优惠");
        } else {
            helper.setText(R.id.tv_discount, "");
        }

        RecyclerView rvContainer = helper.getView(R.id.rv_container);
        rvContainer.setLayoutManager(new LinearLayoutManager(mContext));
        ShoppingCartChildAdapter childAdapter = new ShoppingCartChildAdapter(R.layout.item_shopping_cart_child, item.goods);
        rvContainer.setAdapter(childAdapter);

        childAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_reduce:
                        if (mListener != null) {
                            mListener.reduce(helper.getAdapterPosition(), position);
                        }
                        break;
                    case R.id.tv_plus:
                        if (mListener != null) {
                            mListener.add(helper.getAdapterPosition(), position);
                        }
                        break;
                    case R.id.cb_child:
                        if (mListener != null) {
                            CheckBox cbChild = (CheckBox) view;
                            if (cbChild.isChecked()) {
                                mListener.check(0, helper.getAdapterPosition(), position);
                            } else {
                                mListener.check(1, helper.getAdapterPosition(), position);
                            }
                        }
                        break;
                }
            }
        });
    }

    public interface OnStateChangeListener {
        /**
         * @param parentPosition 父列表的position
         * @param childPosition  子列表的position
         */
        void add(int parentPosition, int childPosition);

        /**
         * @param parentPosition 父列表的position
         * @param childPosition  子列表的position
         */
        void reduce(int parentPosition, int childPosition);

        /**
         * @param isChecked      0选中 1 不选中
         * @param parentPosition 父列表的position
         * @param childPosition  子列表的position
         */
        void check(int isChecked, int parentPosition, int childPosition);
    }

    private OnStateChangeListener mListener;

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.mListener = onStateChangeListener;
    }

}
