package com.harry.ebangbang.function.search;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.donkingliang.labels.LabelsView;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.function.shop_detail.ShopDetailActivity;
import com.harry.ebangbang.network.entity.SearchEntity;
import com.harry.ebangbang.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/12/3.
 * 全局搜索页面
 */
public class SearchActivity extends BaseActivity<SearchPresenter> {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.labels)
    LabelsView labels;
    @BindView(R.id.ll_history)
    LinearLayout llHistory;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<String> labelList;

    private List<SearchEntity.DataBean> mList;
    private SearchAdapter adapter;

    @Override
    protected int setupView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        mList = new ArrayList<>();
        initLabels();

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchAdapter(R.layout.item_search, mList);
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(SearchActivity.this, ShopDetailActivity.class);
                intent.putExtra("shopId", String.valueOf(mList.get(position).shopId));
                startActivity(intent);
            }
        });
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.SEARCH_ACTIVITY_SEARCH);
        return tags;
    }

    @Override
    protected SearchPresenter bindPresenter() {
        return new SearchPresenter();
    }

    @OnClick({R.id.ll_back, R.id.tv_search, R.id.iv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_search:
                String searchText = etSearch.getText().toString().trim();
                if (TextUtils.isEmpty(searchText)) {
                    ToastUtils.showShort("请输入您要搜索的内容");
                    return;
                }
                addSearchHistory(searchText);

                llHistory.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                mPresenter.search(searchText);
                break;
            case R.id.iv_delete:
                labelList.clear();
                SPUtils.putStrListValue(UserInfo.SEARCH_HISTORY.name(), labelList);
                labels.setLabels(labelList);
                break;
        }
    }

    private void initLabels() {
        labelList = SPUtils.getStrListValue(UserInfo.SEARCH_HISTORY.name());
        if (labelList == null) {
            labelList = new ArrayList<>();
        }
        labels.setLabels(labelList);

        labels.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                llHistory.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                mPresenter.search((String) data);
            }
        });
    }

    private void addSearchHistory(String history) {
        if (labelList.size() >= 10) {
            labelList.remove(9);
        }
        labelList.add(0, history);
        SPUtils.putStrListValue(UserInfo.SEARCH_HISTORY.name(), labelList);
        labels.setLabels(labelList);
    }

    public void searchResult(List<SearchEntity.DataBean> data) {
        mList.clear();
        mList.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
