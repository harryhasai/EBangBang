package com.harry.ebangbang.function.merchant_entry.select_content;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;
import com.harry.ebangbang.network.entity.SelectContentAddressEntity;

/**
 * Created by Harry on 2018/12/25.
 */
public class AddressAdapter extends BaseQuickAdapter<SelectContentAddressEntity.DataBean, BaseViewHolder> {

    private int lastPosition = -1;

    public AddressAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final SelectContentAddressEntity.DataBean item) {
        final TextView tvText = helper.getView(R.id.tv_text);
        tvText.setText(item.name);
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
                    mListener.onClick(item.id, item.name);
                }
                notifyDataSetChanged();
            }
        });
    }

    public interface OnTextViewClickListener {
        void onClick(int id, String name);
    }

    private OnTextViewClickListener mListener;

    public void setOnTextViewClickListener(OnTextViewClickListener onTextViewClickListener) {
        this.mListener = onTextViewClickListener;
    }
}
