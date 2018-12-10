package com.harry.ebangbang.function.goods_detail;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.network.entity.GoodsDetailEntity;
import com.harry.ebangbang.utils.DateFormatUtils;
import com.harry.ebangbang.utils.PicassoCircleTransform;
import com.harry.ebangbang.utils.SPUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by Harry on 2018/12/10.
 */
public class GoodsDetailCommentAdapter extends BaseQuickAdapter<GoodsDetailEntity.DataBean.CommentsBean, BaseViewHolder> {

    public GoodsDetailCommentAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsDetailEntity.DataBean.CommentsBean item) {
        ImageView ivUserHeader = helper.getView(R.id.iv_user_header);
        ImageView ivCommentImg = helper.getView(R.id.iv_comment_img);
        LinearLayout llStar = helper.getView(R.id.ll_star);
        Picasso.with(mContext)
                .load(SPUtils.getString(UserInfo.HEADER_BASE.name(), "") + item.userimg)
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_place_holder)
                .transform(new PicassoCircleTransform())
                .into(ivUserHeader);

        if (TextUtils.isEmpty(item.img)) {
            ivCommentImg.setVisibility(View.GONE);
        } else {
            ivCommentImg.setVisibility(View.VISIBLE);
            Picasso.with(mContext)
                    .load(SPUtils.getString(UserInfo.HEADER_BASE.name(), "") + item.img)
                    .error(R.drawable.ic_error)
                    .placeholder(R.drawable.ic_place_holder)
                    .into(ivCommentImg);
        }

        setStarCount(llStar, item.score);

        helper.setText(R.id.tv_time, DateFormatUtils.getFormatedNewsTime(item.time))
                .setText(R.id.tv_user_name, item.userNickName)
                .setText(R.id.tv_comment_content, item.content);

    }

    private void setStarCount(LinearLayout llStar, int count) {
        switch (count) {
            case 1:
                llStar.removeViews(0, 4);
                break;
            case 2:
                llStar.removeViews(0, 3);
                break;
            case 3:
                llStar.removeViews(0, 2);
                break;
            case 4:
                llStar.removeViews(0, 1);
                break;
            case 5:
                break;
        }
    }
}
