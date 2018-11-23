package com.harry.ebangbang.function.shopping_cart;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.harry.ebangbang.R;
import com.harry.ebangbang.base.BaseFragment;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.CommonEntity;

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
    Unbinder unbinder;

    @Override
    protected int setupView() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        llBack.setVisibility(View.GONE);
        tvTitle.setText("购物车");

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        List<CommonEntity> list = new ArrayList<>();
        list.add(new CommonEntity());
        list.add(new CommonEntity());
        list.add(new CommonEntity());
        recyclerView.setAdapter(new ShoppingCartAdapter(R.layout.item_shopping_cart, list));
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
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
}
