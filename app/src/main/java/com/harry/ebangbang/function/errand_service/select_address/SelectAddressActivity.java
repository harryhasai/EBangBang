package com.harry.ebangbang.function.errand_service.select_address;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.ConstantFinal;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.function.add_address.AddAddressActivity;
import com.harry.ebangbang.function.address_management.AddressManagementAdapter;
import com.harry.ebangbang.network.entity.AddressManagementEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/12/14.
 * 选择地址页面
 */
public class SelectAddressActivity extends BaseActivity<SelectAddressPresenter> {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private SelectAddressAdapter adapter;

    @Override
    protected int setupView() {
        return R.layout.activity_select_address;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        initRecyclerView();

        mPresenter.getAddressList();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SelectAddressAdapter(R.layout.item_select_address);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AddressManagementEntity.DataBean bean = (AddressManagementEntity.DataBean) adapter.getData().get(position);
                Intent intent = new Intent();
                intent.putExtra("ID", String.valueOf(bean.id));
                intent.putExtra("address", bean.site);
                setResult(ConstantFinal.COMMON_RESULT_CODE, intent);
                finish();
            }
        });
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.ADDRESS_MANAGEMENT_ACTIVITY_GET_ADDRESS_LIST);
        tags.add(DisposableFinal.ADDRESS_MANAGEMENT_ACTIVITY_SET_DEFAULT);
        return tags;
    }

    @Override
    protected SelectAddressPresenter bindPresenter() {
        return new SelectAddressPresenter();
    }

    public void getAddressList(List<AddressManagementEntity.DataBean> data) {
        adapter.setNewData(data);
    }

    @OnClick({R.id.ll_back, R.id.tv_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_add_address:
                startActivityForResult(new Intent(this, AddAddressActivity.class), ConstantFinal.COMMON_REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ConstantFinal.COMMON_REQUEST_CODE && resultCode == ConstantFinal.COMMON_RESULT_CODE) {
            mPresenter.getAddressList();
        }
    }
}
