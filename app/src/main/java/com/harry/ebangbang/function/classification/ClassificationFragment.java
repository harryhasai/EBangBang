package com.harry.ebangbang.function.classification;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.ebangbang.R;
import com.harry.ebangbang.base.BaseFragment;
import com.harry.ebangbang.network.entity.CommonEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Harry on 2018/11/6.
 * 分类页面
 */
public class ClassificationFragment extends BaseFragment<ClassificationPresenter> {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    @BindView(R.id.rv_child_category)
    RecyclerView rvChildCategory;
    Unbinder unbinder;
    private ClassificationChildCategoryAdapter childCategoryAdapter;
    private ClassificationCategoryAdapter categoryAdapter;

    @Override
    protected int setupView() {
        return R.layout.fragment_classification;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        initRecyclerView();
    }

    private void initRecyclerView() {
        rvCategory.setLayoutManager(new LinearLayoutManager(mActivity));
        rvChildCategory.setLayoutManager(new LinearLayoutManager(mActivity));

        List<CommonEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new CommonEntity());
        }
        categoryAdapter = new ClassificationCategoryAdapter(R.layout.item_classification_category, list, mActivity);
        rvCategory.setAdapter(categoryAdapter);
        childCategoryAdapter = new ClassificationChildCategoryAdapter(R.layout.item_classification_child_category, list);
        rvChildCategory.setAdapter(childCategoryAdapter);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected ClassificationPresenter bindPresenter() {
        return new ClassificationPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
