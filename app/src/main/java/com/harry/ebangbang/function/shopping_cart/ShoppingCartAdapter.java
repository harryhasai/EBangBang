package com.harry.ebangbang.function.shopping_cart;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;
import com.harry.ebangbang.network.entity.CommonEntity;

import java.util.List;

/**
 * Created by Harry on 2018/11/23.
 */
public class ShoppingCartAdapter extends BaseQuickAdapter<CommonEntity, BaseViewHolder> {

    public ShoppingCartAdapter(int layoutResId, @Nullable List<CommonEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonEntity item) {
        LinearLayout llContainer = helper.getView(R.id.ll_container);
        for (int i = 0; i < 3; i++) {
            View view = View.inflate(mContext, R.layout.item_shopping_cart_child, null);

            llContainer.addView(view);
        }
    }
}
