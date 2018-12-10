package com.harry.ebangbang.function.goods_detail;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.ConstantFinal;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.network.entity.GoodsDetailEntity;
import com.harry.ebangbang.utils.SPUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/12/10.
 * 商品详情
 */
public class GoodsDetailActivity extends BaseActivity<GoodsDetailPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_goods_img)
    ImageView ivGoodsImg;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_goods_sales_volume)
    TextView tvGoodsSalesVolume;
    @BindView(R.id.iv_reduce)
    ImageView ivReduce;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.iv_plus)
    ImageView ivPlus;
    @BindView(R.id.tv_goods_price)
    TextView tvGoodsPrice;
    @BindView(R.id.tv_goods_desc)
    TextView tvGoodsDesc;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_complete)
    Button btnComplete;
    @BindView(R.id.ll_comment)
    LinearLayout llComment;
    private String goodsId;
    private String goodsCount;
    private int count = 0;
    private GoodsDetailCommentAdapter adapter;

    @Override
    protected int setupView() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        goodsId = getIntent().getStringExtra("goodsId");
        goodsCount = getIntent().getStringExtra("goodsCount");

        tvTitle.setText("商品详情");
        if (!TextUtils.isEmpty(goodsCount)) {
            count = Integer.parseInt(goodsCount);
            tvCount.setText(goodsCount);
        }

        initRecyclerView();

        mPresenter.getGoodsDetail(goodsId);
    }

    private void initRecyclerView() {
        LinearLayoutManager layout = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layout);
        adapter = new GoodsDetailCommentAdapter(R.layout.item_goods_detail_comment);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.GOODS_DETAIL_ACTIVITY_GET_GOODS_DETAIL);
        return tags;
    }

    @Override
    protected GoodsDetailPresenter bindPresenter() {
        return new GoodsDetailPresenter();
    }

    @OnClick({R.id.ll_back, R.id.iv_reduce, R.id.iv_plus, R.id.btn_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.iv_reduce:
                count--;
                if (count <= 0) {
                    count = 0;
                }
                tvCount.setText(String.valueOf(count));
                break;
            case R.id.iv_plus:
                count++;
                tvCount.setText(String.valueOf(count));
                break;
            case R.id.btn_complete:
                if (count != 0) {
                    Intent data = new Intent();
                    data.putExtra("goodsId", goodsId);
                    data.putExtra("goodsCount", count);
                    setResult(ConstantFinal.COMMON_RESULT_CODE, data);
                }
                finish();
                break;
        }
    }

    public void getGoodsDetail(GoodsDetailEntity.DataBean bean) {
        Picasso.with(this)
                .load(SPUtils.getString(UserInfo.HEADER_BASE.name(), "") + bean.goodsImg)
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_place_holder)
                .into(ivGoodsImg);
        tvGoodsName.setText(bean.goodsName);
        tvGoodsSalesVolume.setText("月售" + bean.salesVolume);
        tvGoodsPrice.setText("¥" + bean.price);
        tvGoodsDesc.setText(bean.details);

        List<GoodsDetailEntity.DataBean.CommentsBean> comments = bean.comments;
        if (comments.size() == 0) {
            llComment.setVisibility(View.GONE);
        } else {
            adapter.setNewData(comments);
        }
    }
}
