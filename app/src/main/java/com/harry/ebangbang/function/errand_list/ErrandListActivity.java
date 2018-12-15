package com.harry.ebangbang.function.errand_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.harry.ebangbang.R;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.function.errand_list.completed.CompletedFragment;
import com.harry.ebangbang.function.errand_list.going_on.GoingOnFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/12/15.
 * 跑腿服务 - 列表查看
 */
public class ErrandListActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private List<Fragment> fragmentList;

    @Override
    protected int setupView() {
        return R.layout.activity_errand_list;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("跑腿服务");

        initFragmentList();
        initConfig();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @OnClick(R.id.ll_back)
    public void onViewClicked() {
        finish();
    }

    private void initFragmentList() {
        fragmentList = new ArrayList<>();
        CompletedFragment completedFragment = new CompletedFragment();
        GoingOnFragment goingOnFragment = new GoingOnFragment();
        fragmentList.add(goingOnFragment);
        fragmentList.add(completedFragment);
    }

    private void initConfig() {
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(new ErrandPagerAdapter(getSupportFragmentManager()));

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabTextColors(getResources().getColorStateList(R.color.select_tab_text));
        tabLayout.setSelectedTabIndicatorHeight(0);    // 下方滚动的下划线高度
//        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_text_color_selected));  // 下方滚动的下划线颜色
        tabLayout.setupWithViewPager(viewPager);

    }

    private class ErrandPagerAdapter extends FragmentPagerAdapter {

        private String[] tabNames;

        public ErrandPagerAdapter(FragmentManager fm) {
            super(fm);
            tabNames = getResources().getStringArray(R.array.errand_name);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }
    }
}
