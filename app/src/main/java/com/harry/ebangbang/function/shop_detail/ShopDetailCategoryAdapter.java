package com.harry.ebangbang.function.shop_detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;
import com.harry.ebangbang.network.entity.ShopDetailCategoryEntity;

/**
 * Created by Harry on 2018/12/4.
 */
public class ShopDetailCategoryAdapter extends BaseQuickAdapter<ShopDetailCategoryEntity.DataBean, BaseViewHolder> {

    private int lastPosition = 0;

    public ShopDetailCategoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ShopDetailCategoryEntity.DataBean item) {
        final TextView tvText = helper.getView(R.id.tv_text);
        tvText.setText(item.catName);
        if (helper.getAdapterPosition() == lastPosition) {
            tvText.setTextColor(mContext.getResources().getColor(R.color.app_status_bar_color));
        } else {
            tvText.setTextColor(mContext.getResources().getColor(R.color.black));
        }
        tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPosition = helper.getAdapterPosition();
                tvText.setTextColor(mContext.getResources().getColor(R.color.app_status_bar_color));
                if (mListener != null) {
                    mListener.onClick(item.shopClassifyId);
                }
                notifyDataSetChanged();
            }
        });
    }

    public void setSelection(RecyclerView recyclerView, int lastPosition) {
        this.lastPosition = lastPosition;
        TextView tvText = (TextView) getViewByPosition(recyclerView, lastPosition, R.id.tv_text);
        tvText.setTextColor(mContext.getResources().getColor(R.color.app_status_bar_color));
        notifyDataSetChanged();
    }

    public interface OnTextViewClickListener {
        void onClick(int shopClassifyId);
    }

    private OnTextViewClickListener mListener;

    public void setOnTextViewClickListener(OnTextViewClickListener onTextViewClickListener) {
        this.mListener = onTextViewClickListener;
    }
}
