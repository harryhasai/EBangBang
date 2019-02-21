package com.harry.ebangbang.function.merchant_entry.select_content;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.ConstantFinal;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.network.entity.SelectContentAddressChildEntity;
import com.harry.ebangbang.network.entity.SelectContentAddressEntity;
import com.harry.ebangbang.network.entity.SelectContentCategoryChildEntity;
import com.harry.ebangbang.network.entity.SelectContentCategoryEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/12/25.
 * 选择经营品类和所在城市的页面
 */
public class SelectContentActivity extends BaseActivity<SelectContentPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view1)
    RecyclerView recyclerView1;
    @BindView(R.id.recycler_view2)
    RecyclerView recyclerView2;
    @BindView(R.id.recycler_view3)
    RecyclerView recyclerView3;
    private CategoryAdapter categoryAdapter;
    private AddressAdapter addressAdapter;
    private ChildCategoryAdapter childCategoryAdapter;
    private ChildAddressAdapter childAddressAdapter;

    private boolean isCategory = false;
    private boolean isAddress = false;
    private ChildCategoryAdapter childCategoryAdapter3;
    private ChildAddressAdapter childAddressAdapter3;
    private int categoryId1;
    private String categoryName1;
    private int categoryId2;
    private String categoryName2;
    private int categoryId3;
    private String categoryName3;
    private int addressId1;
    private String addressName1;
    private int addressId2;
    private String addressName2;
    private int addressId3;
    private String addressName3;
    private String name;

    @Override
    protected int setupView() {
        return R.layout.activity_select_content;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        initRecyclerView();

        name = getIntent().getStringExtra("name");
        tvTitle.setText(name);
        if (name.equals("经营品类")) {
            mPresenter.getCategory();
        } else if (name.equals("所在城市")) {
            mPresenter.getAddress();
        }

    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.SELECT_CONTENT_ACTIVITY);
        return tags;
    }

    @Override
    protected SelectContentPresenter bindPresenter() {
        return new SelectContentPresenter();
    }

    private void initRecyclerView() {
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter = new CategoryAdapter(R.layout.item_classification_category);
        addressAdapter = new AddressAdapter(R.layout.item_classification_category);
        childCategoryAdapter = new ChildCategoryAdapter(R.layout.item_classification_category);
        childCategoryAdapter3 = new ChildCategoryAdapter(R.layout.item_classification_category);
        childAddressAdapter = new ChildAddressAdapter(R.layout.item_classification_category);
        childAddressAdapter3 = new ChildAddressAdapter(R.layout.item_classification_category);

        //一级菜单
        categoryAdapter.setOnTextViewClickListener(new CategoryAdapter.OnTextViewClickListener() {
            @Override
            public void onClick(int id, String name) {
                childCategoryAdapter3.setNewData(null);
                mPresenter.getChildCategory(String.valueOf(id));
                isCategory = false;
                categoryId1 = id;
                categoryName1 = name;
            }
        });
        //二级菜单
        childCategoryAdapter.setOnTextViewClickListener(new ChildCategoryAdapter.OnTextViewClickListener() {
            @Override
            public void onClick(int id, String name) {
                childCategoryAdapter3.setNewData(null);
                mPresenter.getChildCategory(String.valueOf(id));
                isCategory = true;
                categoryId2 = id;
                categoryName2 = name;
            }
        });
        //一级菜单
        addressAdapter.setOnTextViewClickListener(new AddressAdapter.OnTextViewClickListener() {
            @Override
            public void onClick(int id, String name) {
                childAddressAdapter3.setNewData(null);
                mPresenter.getChildAddress(String.valueOf(id));
                isAddress = false;
                addressId1 = id;
                addressName1 = name;
            }
        });

        //二级菜单
        childAddressAdapter.setOnTextViewClickListener(new ChildAddressAdapter.OnTextViewClickListener() {
            @Override
            public void onClick(int id, String name) {
                childAddressAdapter3.setNewData(null);
                mPresenter.getChildAddress(String.valueOf(id));
                isAddress = true;
                addressId2 = id;
                addressName2 = name;
            }
        });

        //三级菜单
        childCategoryAdapter3.setOnTextViewClickListener(new ChildCategoryAdapter.OnTextViewClickListener() {
            @Override
            public void onClick(int id, String name) {
                categoryId3 = id;
                categoryName3 = name;
            }
        });

        //三级菜单
        childAddressAdapter3.setOnTextViewClickListener(new ChildAddressAdapter.OnTextViewClickListener() {
            @Override
            public void onClick(int id, String name) {
                addressId3 = id;
                addressName3 = name;
            }
        });
    }

    public void getCategory(List<SelectContentCategoryEntity.DataBean> data) {
        recyclerView1.setAdapter(categoryAdapter);
        categoryAdapter.setNewData(data);
    }

    public void getChildCategory(List<SelectContentCategoryChildEntity.DataBean> data) {
        if (isCategory) {
            recyclerView3.setAdapter(childCategoryAdapter3);
            childCategoryAdapter3.setNewData(data);
        } else {
            recyclerView2.setAdapter(childCategoryAdapter);
            childCategoryAdapter.setNewData(data);
        }
    }

    public void getAddress(List<SelectContentAddressEntity.DataBean> data) {
        recyclerView1.setAdapter(addressAdapter);
        addressAdapter.setNewData(data);
    }

    public void getChildAddress(List<SelectContentAddressChildEntity.DataBean> data) {
        if (isAddress) {
            recyclerView3.setAdapter(childAddressAdapter3);
            childAddressAdapter3.setNewData(data);
        } else {
            recyclerView2.setAdapter(childAddressAdapter);
            childAddressAdapter.setNewData(data);
        }
    }

    @OnClick({R.id.ll_back, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_confirm:
                Intent intent = new Intent();
                if (name.equals("经营品类")) {
                    intent.putExtra("categoryId1", categoryId1);
                    intent.putExtra("categoryId2", categoryId2);
                    intent.putExtra("categoryId3", categoryId3);
                    intent.putExtra("categoryName1", categoryName1);
                    intent.putExtra("categoryName2", categoryName2);
                    intent.putExtra("categoryName3", categoryName3);
                } else if (name.equals("所在城市")) {
                    intent.putExtra("addressId1", addressId1);
                    intent.putExtra("addressId2", addressId2);
                    intent.putExtra("addressId3", addressId3);
                    intent.putExtra("addressName1", addressName1);
                    intent.putExtra("addressName2", addressName2);
                    intent.putExtra("addressName3", addressName3);
                }
                setResult(ConstantFinal.COMMON_RESULT_CODE, intent);
                finish();
                break;
        }
    }
}
