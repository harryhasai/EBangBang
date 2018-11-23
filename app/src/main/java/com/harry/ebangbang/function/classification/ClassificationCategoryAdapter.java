package com.harry.ebangbang.function.classification;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;
import com.harry.ebangbang.network.entity.CommonEntity;

import java.util.List;

/**
 * Created by Harry on 2018/11/22.
 * 一级分类的适配器
 */
public class ClassificationCategoryAdapter extends BaseQuickAdapter<CommonEntity, BaseViewHolder> {

    private Activity mActivity;
    private int lastPosition = 0;

    public ClassificationCategoryAdapter(int layoutResId, @Nullable List<CommonEntity> data, Activity mActivity) {
        super(layoutResId, data);
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(final BaseViewHolder helper, CommonEntity item) {
        final TextView tvText = helper.getView(R.id.tv_text);
        if (helper.getAdapterPosition() == lastPosition) {
            tvText.setTextColor(mActivity.getResources().getColor(R.color.app_status_bar_color));
        } else {
            tvText.setTextColor(mActivity.getResources().getColor(R.color.black));
        }
        tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPosition = helper.getAdapterPosition();
                tvText.setTextColor(mActivity.getResources().getColor(R.color.app_status_bar_color));
                notifyDataSetChanged();
            }
        });
    }
}
