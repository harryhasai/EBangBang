package com.harry.ebangbang.function.shopping_cart;

import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.network.entity.ShoppingCartEntity;
import com.harry.ebangbang.utils.SPUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Harry on 2018/11/26.
 */
public class ShoppingCartChildAdapter extends BaseQuickAdapter<ShoppingCartEntity.DataBean.GoodsBean, BaseViewHolder> {

    public ShoppingCartChildAdapter(int layoutResId, @Nullable List<ShoppingCartEntity.DataBean.GoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoppingCartEntity.DataBean.GoodsBean item) {
        ImageView ivFood = helper.getView(R.id.iv_food);
        Picasso.with(mContext)
                .load(SPUtils.getString(UserInfo.HEADER_BASE.name(), "") + item.goodsImgLink)
                .error(R.drawable.ic_error)
//                .transform(new PicassoCircleTransform())
                .resize(ConvertUtils.dp2px(79), ConvertUtils.dp2px(79))
                .centerCrop()
                .into(ivFood);

        helper.setText(R.id.tv_food_name, item.goodsName)
                .setText(R.id.tv_count, String.valueOf(item.amount))
                .setText(R.id.tv_food_price, "¥" + item.amount * item.goodsPrice)
                .addOnClickListener(R.id.tv_reduce)
                .addOnClickListener(R.id.tv_plus)
                .addOnClickListener(R.id.cb_child);

        CheckBox cbChild = helper.getView(R.id.cb_child);
        TextView tvReduce = helper.getView(R.id.tv_reduce);
        TextView tvPlus = helper.getView(R.id.tv_plus);
        if (item.standby1 == 0) {//0 选中 1 不选中
            cbChild.setChecked(true);
        } else {
            cbChild.setChecked(false);
        }
        if (cbChild.isChecked()) {
            tvReduce.setClickable(true);
            tvPlus.setClickable(true);
        } else {
            tvReduce.setClickable(false);
            tvPlus.setClickable(false);
        }
        if (item.amount == 1) {
            tvReduce.setClickable(false);
        }

    }
}
