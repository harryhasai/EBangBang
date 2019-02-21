package com.harry.ebangbang.function.add_address.receiving_address;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.ConstantFinal;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.utils.LocationUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/11/29.
 * 定位附近地址
 */
public class ReceivingAddressActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.map_view)
    MapView mapView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private AMap aMap;
    private MarkerOptions markerOption;
    private PoiSearch poiSearch;
    private String city;
    private String keyword;

    private List<PoiItem> mList;
    private ReceivingAddressAdapter adapter;
    private double latitude;
    private double longitude;

    @Override
    protected int setupView() {
        return R.layout.activity_receiving_address;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mapView.onCreate(savedInstanceState);

        mList = new ArrayList<>();

        initRecyclerView();
        tvTitle.setText("选择地址");

        LocationUtil.getInstance().initLocation(getApplicationContext());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initMap();
            }
        }, 500);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReceivingAddressAdapter(R.layout.item_receiving_address, mList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("title", mList.get(position).getTitle());
                intent.putExtra("latitude", String.valueOf(latitude));
                intent.putExtra("longitude", String.valueOf(longitude));
                setResult(ConstantFinal.COMMON_RESULT_CODE, intent);
                finish();
            }
        });
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    private void startPoi(double latitude, double longitude) {
        PoiSearch.Query query = new PoiSearch.Query(keyword, "", city);
        query.setPageSize(20);
        query.setPageNum(0);
        LatLonPoint latLonPoint = new LatLonPoint(latitude, longitude);
        poiSearch = new PoiSearch(ReceivingAddressActivity.this, query);
        poiSearch.setOnPoiSearchListener(new MyPoiSearchListener());
        poiSearch.setBound(new PoiSearch.SearchBound(latLonPoint, 500));//
        // 设置搜索区域为以latLonPoint点为圆心，其周围500米范围
        poiSearch.searchPOIAsyn();// 异步搜索
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private void initMap() {
        aMap = mapView.getMap();

        LocationUtil.getInstance().startLocation(new AMapLocationListener() {

            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation.getErrorCode() == 0) {
                    city = aMapLocation.getCity();
//                    keyword = aMapLocation.getAoiName();
                    keyword = "";
                    startPoi(aMapLocation.getLatitude(), aMapLocation.getLongitude());

                    LatLng latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    addMarkersToMap(latLng);
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f));
                    LocationUtil.getInstance().stopLocation();
                } else {
                    LocationUtil.getInstance().startLocation();
                }
            }

        });

        //拖拽地图的回调
        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                aMap.clear();
                addMarkersToMap(cameraPosition.target);
            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                startPoi(cameraPosition.target.latitude, cameraPosition.target.longitude);
            }
        });

    }

    /**
     * 获取周边Poi的回调类
     */
    private class MyPoiSearchListener implements PoiSearch.OnPoiSearchListener {

        @Override
        public void onPoiSearched(PoiResult poiResult, int i) {
            if (i == AMapException.CODE_AMAP_SUCCESS) {
                if (poiResult != null && poiResult.getQuery() != null) {
                    ArrayList<PoiItem> pois = poiResult.getPois();
                    if (pois.size() == 0) {
                        ToastUtils.showShort("暂无周边数据");
                    }
                    mList.clear();
                    mList.addAll(pois);
                    adapter.notifyDataSetChanged();
                }
            }
        }

        @Override
        public void onPoiItemSearched(PoiItem poiItem, int i) {

        }
    }


    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap(LatLng latLng) {
        markerOption = new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(latLng)
                .draggable(true);
        aMap.addMarker(markerOption);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
        LocationUtil.getInstance().stopLocation();
        LocationUtil.getInstance().destroyLocation();
    }

    @OnClick({R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }
}
