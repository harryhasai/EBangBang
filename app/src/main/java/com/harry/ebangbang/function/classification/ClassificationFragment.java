package com.harry.ebangbang.function.classification;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.BaseFragment;
import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.network.entity.SecondCategoryEntity;
import com.harry.ebangbang.network.entity.TopCategoryEntity;

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
    private List<TopCategoryEntity.DataBean> mTopCategoryList;
    private List<SecondCategoryEntity.DataBean> mSecondCategoryList;

    @Override
    protected int setupView() {
        return R.layout.fragment_classification;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        mTopCategoryList = new ArrayList<>();
        mSecondCategoryList = new ArrayList<>();
        initRecyclerView();

        mPresenter.getFirstLevel();
    }

    private void initRecyclerView() {
        rvCategory.setLayoutManager(new LinearLayoutManager(mActivity));
        rvChildCategory.setLayoutManager(new LinearLayoutManager(mActivity));

        categoryAdapter = new ClassificationCategoryAdapter(R.layout.item_classification_category, mTopCategoryList, mActivity);
        rvCategory.setAdapter(categoryAdapter);
        childCategoryAdapter = new ClassificationChildCategoryAdapter(R.layout.item_classification_child_category, mSecondCategoryList);
        rvChildCategory.setAdapter(childCategoryAdapter);

        categoryAdapter.setOnTextViewClickListener(new ClassificationCategoryAdapter.OnTextViewClickListener() {
            @Override
            public void onClick(int id) {
                mPresenter.getSecondLevel(String.valueOf(id));
            }
        });
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.CLASSIFICATION_FRAGMENT_GET_FIRST_LEVEL);
        tags.add(DisposableFinal.CLASSIFICATION_FRAGMENT_GET_SECOND_LEVEL);
        return tags;
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

    public void getFirstLevel(List<TopCategoryEntity.DataBean> data) {
        mTopCategoryList.clear();
        mTopCategoryList.addAll(data);
        categoryAdapter.notifyDataSetChanged();
        mPresenter.getSecondLevel(String.valueOf(data.get(0).id));
    }

    public void getSecondLevel(List<SecondCategoryEntity.DataBean> data) {
        mSecondCategoryList.clear();
        mSecondCategoryList.addAll(data);
        childCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (mTopCategoryList.size() == 0) {
                mPresenter.getFirstLevel();
            }
            if (mTopCategoryList.size() != 0 && mSecondCategoryList.size() == 0) {
                mPresenter.getSecondLevel(String.valueOf(mTopCategoryList.get(0).id));
            }
        }
    }
}
