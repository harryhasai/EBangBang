package com.harry.ebangbang.function.address_management;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.harry.ebangbang.function.modify_address.ModifyAddressActivity;
import com.harry.ebangbang.network.entity.AddressManagementEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/12/14.
 * 地址管理页面
 */
public class AddressManagementActivity extends BaseActivity<AddressManagementPresenter> {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private AddressManagementAdapter adapter;

    @Override
    protected int setupView() {
        return R.layout.activity_address_management;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        initRecyclerView();

        mPresenter.getAddressList();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddressManagementAdapter(R.layout.item_address_management);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                final AddressManagementEntity.DataBean bean = (AddressManagementEntity.DataBean) adapter.getData().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddressManagementActivity.this);
                builder.setMessage("请选择您要做的操作").setCancelable(true);
                builder.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.delete(String.valueOf(bean.id));
                        dialog.dismiss();
                    }
                }).setPositiveButton("设为默认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.setDefault(String.valueOf(bean.id));
                        dialog.dismiss();
                    }
                }).show();
                return true;
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_modify:
                        AddressManagementEntity.DataBean bean = (AddressManagementEntity.DataBean) adapter.getData().get(position);
                        Intent intent = new Intent(AddressManagementActivity.this, ModifyAddressActivity.class);
                        intent.putExtra("addressId", String.valueOf(bean.id));
                        intent.putExtra("isDefault", String.valueOf(bean.isDefault));
                        intent.putExtra("name", bean.name);
                        intent.putExtra("address", bean.site);
                        intent.putExtra("longitude", bean.longitude);
                        intent.putExtra("latitude", bean.latitude);
                        intent.putExtra("phone", bean.phone);
                        startActivityForResult(intent, ConstantFinal.COMMON_REQUEST_CODE);
                        break;
                }
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
    protected AddressManagementPresenter bindPresenter() {
        return new AddressManagementPresenter();
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
