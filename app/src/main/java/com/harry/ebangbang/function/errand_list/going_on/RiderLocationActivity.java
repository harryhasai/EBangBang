package com.harry.ebangbang.function.errand_list.going_on;

import android.os.Bundle;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.harry.ebangbang.R;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.base.presenter.BasePresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/12/15.
 * 显示骑手位置的页面
 */
public class RiderLocationActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.map_view)
    MapView mapView;
    private AMap aMap;

    @Override
    protected int setupView() {
        return R.layout.activity_rider_location;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("骑手位置");
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        aMap = mapView.getMap();

        double latitude = getIntent().getDoubleExtra("latitude", 0);
        double longitude = getIntent().getDoubleExtra("longitude", 0);

        LatLng latLng = new LatLng(latitude, longitude);
        addMarkersToMap(latLng);
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f));
    }

    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap(LatLng latLng) {
        MarkerOptions markerOption = new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(latLng)
                .draggable(true);
        aMap.addMarker(markerOption);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
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
    }

    @OnClick(R.id.ll_back)
    public void onViewClicked() {
        finish();
    }
}
