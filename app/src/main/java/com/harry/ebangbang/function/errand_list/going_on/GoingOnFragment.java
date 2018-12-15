package com.harry.ebangbang.function.errand_list.going_on;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.BaseFragment;
import com.harry.ebangbang.network.entity.ErrandListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 2018/12/15.
 * 进行中的跑腿订单
 */
public class GoingOnFragment extends BaseFragment<GoingOnPresenter> {

    private GoingOnAdapter adapter;

    @Override
    protected int setupView() {
        return R.layout.fragment_going_on;
    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new GoingOnAdapter(R.layout.item_going_on);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ErrandListEntity.DataBean bean = (ErrandListEntity.DataBean) adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.tv_call_rider:
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + bean.ridePhone);
                        intent.setData(data);
                        startActivity(intent);
                        break;
                    case R.id.tv_rider_location:
                        Intent intent1 = new Intent(mActivity, RiderLocationActivity.class);
                        intent1.putExtra("latitude", bean.rideLatitude);
                        intent1.putExtra("longitude", bean.rideLongitude);
                        startActivity(intent1);
                        break;

                }
            }
        });

        mPresenter.getGoingOnList();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.GOING_ON_FRAGMENT_GET_GOING_ON_LIST);
        return tags;
    }

    @Override
    protected GoingOnPresenter bindPresenter() {
        return new GoingOnPresenter();
    }

    public void getGoingOnList(List<ErrandListEntity.DataBean> data) {
        adapter.setNewData(data);
    }
}
