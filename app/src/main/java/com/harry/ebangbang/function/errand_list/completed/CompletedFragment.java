package com.harry.ebangbang.function.errand_list.completed;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.BaseFragment;
import com.harry.ebangbang.network.entity.ErrandListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 2018/12/15.
 * 已完成的跑腿订单
 */
public class CompletedFragment extends BaseFragment<CompletedPresenter> {

    private CompletedAdapter adapter;

    @Override
    protected int setupView() {
        return R.layout.fragment_completed;
    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new CompletedAdapter(R.layout.item_going_on);
        recyclerView.setAdapter(adapter);
        mPresenter.getErrandList();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.GOING_ON_FRAGMENT_GET_GOING_ON_LIST);
        return tags;
    }

    @Override
    protected CompletedPresenter bindPresenter() {
        return new CompletedPresenter();
    }

    public void getErrandList(List<ErrandListEntity.DataBean> data) {
        adapter.setNewData(data);
    }
}
