package com.harry.ebangbang.function.home;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.application.EBangBangApplication;
import com.harry.ebangbang.base.BaseFragment;
import com.harry.ebangbang.function.errand_service.ErrandServiceActivity;
import com.harry.ebangbang.function.main.MainActivity;
import com.harry.ebangbang.function.search.SearchActivity;
import com.harry.ebangbang.function.shop_detail.ShopDetailActivity;
import com.harry.ebangbang.network.entity.HomeBannerEntity;
import com.harry.ebangbang.network.entity.HomeEntity;
import com.harry.ebangbang.utils.LocationUtil;
import com.harry.ebangbang.utils.SPUtils;
import com.harry.ebangbang.utils.SwipeRefreshLayoutRefreshingUtil;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Harry on 2018/11/6.
 * 主页
 */
public class HomeFragment extends BaseFragment<HomePresenter> {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;

    private HomeAdapter adapter;
    private boolean isGetList;
    private boolean isGetBanner;

    @Override
    protected int setupView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        initRecyclerView();

        mPresenter.getBanner();
        mPresenter.getList();

        uploadCurrentPosition();
    }

    /**
     * 提交当前经纬度到服务器上
     */
    private void uploadCurrentPosition() {
        LocationUtil.getInstance().initLocation(mActivity.getApplicationContext());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LocationUtil.getInstance().startLocation(new LocationListener());
            }
        }, 500);

    }

    private class LocationListener implements AMapLocationListener {

        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功, 发送经纬度到服务器
                mPresenter.currentPosition(aMapLocation.getLongitude(), aMapLocation.getLatitude());
                TextView tvCity = (TextView) adapter.getViewByPosition(recyclerView, 0, R.id.tv_city);
                tvCity.setText(aMapLocation.getCity());
            } else {
                LocationUtil.getInstance().stopLocation();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LocationUtil.getInstance().startLocation();
                    }
                }, 1000);
                ToastUtils.showShort("请开启手机GPS");
            }
        }
    }


    private void initRecyclerView() {
        // 设置下拉进度的背景颜色，默认就是白色的
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getList();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        List<HomeMultiItem> mList = new ArrayList<>();
        mList.add(new HomeMultiItem(HomeMultiItem.TOP));
        mList.add(new HomeMultiItem(HomeMultiItem.CENTER));
        mList.add(new HomeMultiItem(HomeMultiItem.BOTTOM));
        adapter = new HomeAdapter(mList, mActivity);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.et_search:
                        startActivity(new Intent(mActivity, SearchActivity.class));
                        break;
                    case R.id.tv_center_service:
                        startActivity(new Intent(mActivity, ErrandServiceActivity.class));
                        break;
                    case R.id.tv_center_food:
                        MainActivity mainActivity = (MainActivity) mActivity;
                        mainActivity.swichTab(1);
                        break;
                    case R.id.tv_center_order:
                        Intent intent = new Intent(mActivity, ShopDetailActivity.class);
                        intent.putExtra("shopId", "1");
                        startActivity(intent);
                        break;
                    case R.id.tv_center_share:
                        wxShare();
                        break;
                }
            }
        });
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.HOME_FRAGMENT_GET_LIST);
        tags.add(DisposableFinal.HOME_FRAGMENT_GET_BANNER);
        tags.add(DisposableFinal.HOME_FRAGMENT_CURRENT_POSITION);
        return tags;
    }

    @Override
    protected HomePresenter bindPresenter() {
        return new HomePresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setRefreshing(boolean refreshing) {
        if (swipeRefreshLayout != null) {
            if (refreshing) {
                SwipeRefreshLayoutRefreshingUtil.setRefreshing(swipeRefreshLayout);
            } else {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    public void getList(List<HomeEntity.DataBean> list) {
        adapter.setupRecyclerView(list);
        if (list.size() == 0) {
            isGetList = false;
        } else {
            isGetList = true;
        }
    }

    /**
     * @param homeBannerEntity Banner的实体类
     */
    public void getBanner(HomeBannerEntity homeBannerEntity) {
        adapter.setupBanner(homeBannerEntity);
        if (homeBannerEntity.data.size() == 0) {
            isGetBanner = false;
        } else {
            isGetBanner = true;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (!isGetList) {
                mPresenter.getList();
            }
            if (!isGetBanner) {
                mPresenter.getBanner();
            }
        }
    }

    /**
     * 微信分享
     */
    private void wxShare() {
        //初始化一个 WXTextObject 对象，填写分享的文本内容
        WXTextObject textObj = new WXTextObject();
        textObj.text = SPUtils.getString(UserInfo.INVITE_CODE.name(), "");
        //用 WXTextObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = SPUtils.getString(UserInfo.INVITE_CODE.name(), "");

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;

        EBangBangApplication.getAppContext().mWXAPI.sendReq(req);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
