package com.harry.ebangbang.function.shopping_cart;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.ConstantFinal;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.BaseFragment;
import com.harry.ebangbang.function.submit_order.SubmitOrderActivity;
import com.harry.ebangbang.network.entity.ShoppingCartEntity;
import com.harry.ebangbang.utils.SwipeRefreshLayoutRefreshingUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Harry on 2018/11/6.
 * 购物车页面
 */
public class ShoppingCartFragment extends BaseFragment<ShoppingCartPresenter> {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    private List<ShoppingCartEntity.DataBean> mList;
    private ShoppingCartAdapter adapter;
    private int parentPosition;

    @Override
    protected int setupView() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        mList = new ArrayList<>();

        llBack.setVisibility(View.GONE);
        tvTitle.setText("购物车");

        initRecyclerView();
        mPresenter.getShoppingList();
    }

    private void initRecyclerView() {
        // 设置下拉进度的背景颜色，默认就是白色的
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new ShoppingCartAdapter(R.layout.item_shopping_cart, mList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getShoppingList();
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_cancel:
                        showIsDelete(position);
                        break;
                    case R.id.btn_settle_accounts://去结算:
                        ShoppingCartEntity.DataBean bean = mList.get(position);
                        List<JsonFormatBean> jsonList = new ArrayList<>();
                        for (int i = 0; i < bean.goods.size(); i++) {
                            ShoppingCartEntity.DataBean.GoodsBean goodsBean = bean.goods.get(i);
                            if (goodsBean.standby1 == 0) {//0 选中 1 不选中
                                //[{"id":"1","num":"4"},{"id":"4","num":"1"}]
//                                jsonList.add(new JsonFormatBean(String.valueOf(goodsBean.goodsId), String.valueOf(goodsBean.amount)));
                                jsonList.add(new JsonFormatBean(goodsBean.goodsId, goodsBean.amount));
                            }
                        }
                        Gson gson = new Gson();
                        String s = gson.toJson(jsonList);
                        String shopId = String.valueOf(bean.shopId);

                        Intent intent = new Intent(mActivity, SubmitOrderActivity.class);
                        intent.putExtra("shopId", shopId);
                        intent.putExtra("ids", s);
                        startActivityForResult(intent, ConstantFinal.COMMON_REQUEST_CODE);
                        break;
                }
            }
        });

        adapter.setOnStateChangeListener(new ShoppingCartAdapter.OnStateChangeListener() {
            @Override
            public void add(int parentPosition, int childPosition) {
                ShoppingCartFragment.this.parentPosition = parentPosition;
                String shopId = String.valueOf(mList.get(parentPosition).shopId);
                String goodsId = String.valueOf(mList.get(parentPosition).goods.get(childPosition).goodsId);
                mPresenter.add(shopId, goodsId);
            }

            @Override
            public void reduce(int parentPosition, int childPosition) {
                ShoppingCartFragment.this.parentPosition = parentPosition;
                String shopId = String.valueOf(mList.get(parentPosition).shopId);
                String goodsId = String.valueOf(mList.get(parentPosition).goods.get(childPosition).goodsId);
                mPresenter.reduce(shopId, goodsId);
            }

            @Override
            public void check(int isChecked, int parentPosition, int childPosition) {
                ShoppingCartFragment.this.parentPosition = parentPosition;
                String shopId = String.valueOf(mList.get(parentPosition).shopId);
                String goodsId = String.valueOf(mList.get(parentPosition).goods.get(childPosition).goodsId);
                mPresenter.check(shopId, goodsId, isChecked);
            }
        });
    }

    /**
     * 是否删除当前订单
     *
     * @param position 当前订单的位置
     */
    private void showIsDelete(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setMessage("是否删除当前订单?");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mPresenter.delete(String.valueOf(mList.get(position).shopId));
            }
        }).setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.SHOPPING_CART_FRAGMENT_GET_SHOPPING_LIST);
        tags.add(DisposableFinal.SHOPPING_CART_FRAGMENT_DELETE);
        tags.add(DisposableFinal.SHOPPING_CART_FRAGMENT_ADD);
        tags.add(DisposableFinal.SHOPPING_CART_FRAGMENT_REDUCE);
        tags.add(DisposableFinal.SHOPPING_CART_FRAGMENT_CHECK);
        return tags;
    }

    @Override
    protected ShoppingCartPresenter bindPresenter() {
        return new ShoppingCartPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setRefreshing(boolean refreshing) {
        if (swipeRefreshLayout != null) {
            if (refreshing) {
                SwipeRefreshLayoutRefreshingUtil.setRefreshing(swipeRefreshLayout);
            } else {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    public void getShoppingList(List<ShoppingCartEntity.DataBean> data) {
        mList.clear();
        mList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (mList.size() == 0) {
                mPresenter.getShoppingList();
            }
        }
    }

    /**
     * 删除成功后回调的方法
     */
    public void delete() {
        mPresenter.getShoppingList();
    }

    /**
     * @param data 更新局部列表
     */
    public void updateShoppingList(List<ShoppingCartEntity.DataBean> data) {
        mList.clear();
        mList.addAll(data);
        adapter.notifyItemChanged(parentPosition);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ConstantFinal.COMMON_REQUEST_CODE && resultCode == ConstantFinal.COMMON_RESULT_CODE) {
            mPresenter.getShoppingList();
        }
    }
}
