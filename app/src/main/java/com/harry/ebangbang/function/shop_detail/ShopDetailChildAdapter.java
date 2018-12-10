package com.harry.ebangbang.function.shop_detail;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.function.goods_detail.GoodsDetailActivity;
import com.harry.ebangbang.network.entity.ShopDetailChildEntity;
import com.harry.ebangbang.utils.SPUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Harry on 2018/12/4.
 */
public class ShopDetailChildAdapter extends RecyclerView.Adapter<ShopDetailChildAdapter.ViewHolder> {

    private List<ShopDetailChildEntity.DataBean.GoodsBean> mList;
    private Context mContext;


    public ShopDetailChildAdapter(List<ShopDetailChildEntity.DataBean.GoodsBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shop_detail_child_category, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ShopDetailChildEntity.DataBean.GoodsBean bean = mList.get(position);
        Picasso.with(mContext)
                .load(SPUtils.getString(UserInfo.HEADER_BASE.name(), "") + bean.imgLink)
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_error)
//                .transform(new PicassoCircleTransform())
                .resize(ConvertUtils.dp2px(85), ConvertUtils.dp2px(85))
                .centerCrop()
                .into(holder.ivHeader);

        holder.tvName.setText(bean.name);
        holder.tvPrice.setText("¥" + bean.price);
        if (bean.goodsCount == 0) {
            holder.tvCount.setVisibility(View.GONE);
            holder.ivReduce.setVisibility(View.GONE);
        } else {
            holder.tvCount.setVisibility(View.VISIBLE);
            holder.ivReduce.setVisibility(View.VISIBLE);
            holder.tvCount.setText(String.valueOf(bean.goodsCount));
        }

        holder.ivReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.reduce(holder.getAdapterPosition(), bean.id);
                }
            }
        });

        holder.ivPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.plus(holder.getAdapterPosition(), bean.id);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(bean.id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_header)
        ImageView ivHeader;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.iv_reduce)
        ImageView ivReduce;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.iv_plus)
        ImageView ivPlus;
        RelativeLayout itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = (RelativeLayout) itemView;
        }
    }

    public interface OnChangeNumberListener {
        void plus(int adapterPosition, int id);

        void reduce(int adapterPosition, int id);

        void onItemClick(int goodsId);
    }

    private OnChangeNumberListener mListener;

    public void setOnChangeNumberListener(OnChangeNumberListener listener) {
        this.mListener = listener;
    }

}
