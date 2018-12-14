package com.harry.ebangbang.function.order_detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.function.add_comment.AddCommentActivity;
import com.harry.ebangbang.function.payment_method.PaymentMethodActivity;
import com.harry.ebangbang.network.entity.OrderDetailEntity;
import com.harry.ebangbang.utils.DateFormatUtils;
import com.harry.ebangbang.utils.SPUtils;
import com.harry.ebangbang.utils.SpannableStringUtils;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/12/7.
 * 订单详情页面
 */
public class OrderDetailActivity extends BaseActivity<OrderDetailPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.map_view)
    MapView mapView;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.tv_call_rider)
    TextView tvCallRider;
    @BindView(R.id.ll_status)
    LinearLayout llStatus;
    @BindView(R.id.tv_person_name)
    TextView tvPersonName;
    @BindView(R.id.tv_person_phone)
    TextView tvPersonPhone;
    @BindView(R.id.tv_person_address)
    TextView tvPersonAddress;
    @BindView(R.id.btn_evaluate)
    Button btnEvaluate;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.tv_box_price)
    TextView tvBoxPrice;
    @BindView(R.id.tv_transport_price)
    TextView tvTransportPrice;
    @BindView(R.id.tv_full_discount)
    TextView tvFullDiscount;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_order_remark)
    TextView tvOrderRemark;
    @BindView(R.id.fl_submit)
    FrameLayout flSubmit;
    @BindView(R.id.tv_rider_name)
    TextView tvRiderName;
    @BindView(R.id.ll_order_information)
    LinearLayout llOrderInformation;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private AMap aMap;
    private OrderDetailEntity.DataBean mDataBean;

    @Override
    protected int setupView() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mapView.onCreate(savedInstanceState);

        tvTitle.setText("订单详情");

        String orderId = getIntent().getStringExtra("orderId");
        mPresenter.getOrderDetail(orderId);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.ORDER_DETAIL_ACTIVITY_GET_ORDER_DETAIL);
        tags.add(DisposableFinal.ORDER_DETAIL_ACTIVITY_CONFIRM);
        return tags;
    }

    @Override
    protected OrderDetailPresenter bindPresenter() {
        return new OrderDetailPresenter();
    }

    public void getOrderDetail(OrderDetailEntity.DataBean bean) {
        mDataBean = bean;
        //订单状态  -1未付款 2 待配送  4待收货  5 订单已完成未评价   6退返货    7完成并已评价
        switch (bean.orderFormStatus) {
            case -1://未付款
                btnSubmit.setText("去付款");
                break;
            case 0: //商家接单中
                btnSubmit.setText("返回");
                break;
            case 1: //商家已接单, 骑手未接单
                btnSubmit.setText("返回");
                break;
            case 2: //待配送
                mapView.setVisibility(View.VISIBLE);
                llStatus.setVisibility(View.VISIBLE);
                btnSubmit.setText("确认收货");
                initStatus(bean);
                initMap(bean);
                break;
            case 4://待收货
                mapView.setVisibility(View.VISIBLE);
                llStatus.setVisibility(View.VISIBLE);
                btnSubmit.setText("确认收货");
                initStatus(bean);
                initMap(bean);
                break;
            case 5://订单已完成未评价
                btnEvaluate.setVisibility(View.VISIBLE);
                flSubmit.setVisibility(View.GONE);
                llOrderInformation.setVisibility(View.VISIBLE);
                initOrderInfo(bean);
                break;
            case 6://退返货
                llOrderInformation.setVisibility(View.VISIBLE);
                btnSubmit.setText("去退货");
                initOrderInfo(bean);
                break;
            case 7://完成并已评价
                llOrderInformation.setVisibility(View.VISIBLE);
                btnSubmit.setText("返回");
                initOrderInfo(bean);
                break;
        }

        tvPersonName.setText(bean.userName);
        tvPersonPhone.setText(bean.userphone);
        tvPersonAddress.setText(bean.userSite);

        tvShopName.setText(bean.shopName);
        for (int i = 0; i < bean.goods.size(); i++) {
            OrderDetailEntity.DataBean.GoodsBean goodsBean = bean.goods.get(i);
            View view = View.inflate(this, R.layout.item_submit_order, null);
            ImageView ivFoodImg = view.findViewById(R.id.iv_food_img);
            TextView tvFoodName = view.findViewById(R.id.tv_food_name);
            TextView tvFoodPrice = view.findViewById(R.id.tv_food_price);
            TextView tvFoodCount = view.findViewById(R.id.tv_food_count);

            Picasso.with(this)
                    .load(SPUtils.getString(UserInfo.HEADER_BASE.name(), "") + goodsBean.goodsLogo)
                    .error(R.drawable.ic_error)
                    .placeholder(R.drawable.ic_place_holder)
//                .transform(new PicassoCircleTransform())
                    .resize(ConvertUtils.dp2px(32), ConvertUtils.dp2px(32))
                    .centerCrop()
                    .into(ivFoodImg);
            tvFoodName.setText(goodsBean.goodsName);
            tvFoodPrice.setText("¥" + goodsBean.goodsPrice);
            tvFoodCount.setText("x " + goodsBean.num);
            llContainer.addView(view);
        }

        tvBoxPrice.setText("¥ " + bean.mealBoxMoney);
        tvTransportPrice.setText("¥ " + bean.dispatchingMoney);
        tvFullDiscount.setText("-¥ " + bean.meetSubtract);
        String money = "合计: ¥" + bean.actualMoney;
        tvTotalPrice.setText(SpannableStringUtils.setTextColor(money, 4, money.length()));
    }

    private void initMap(OrderDetailEntity.DataBean bean) {
        aMap = mapView.getMap();

        if (bean.orderFormStatus == 2) {//取货中, 显示商家定位点
            LatLng shopLatLng = new LatLng(Double.valueOf(bean.shopLatitude), Double.valueOf(bean.shopLongitude));
            addMarkersToMap(shopLatLng, BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        } else {
            //配送中, 显示客户定位点
            LatLng userLatLng = new LatLng(Double.valueOf(bean.userLatitude), Double.valueOf(bean.userLongitude));
            addMarkersToMap(userLatLng, BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        }

        LatLng rideLatLng = new LatLng(Double.valueOf(bean.rideLatitude), Double.valueOf(bean.rideLongitude));
        addMarkersToMap(rideLatLng, BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location_style));
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(rideLatLng, 17f));
    }

    /**
     * 初始化状态信息
     */
    private void initStatus(final OrderDetailEntity.DataBean bean) {
        if (bean.orderFormStatus == 2) {
            tvStatus.setText("正在取货中");
        } else {
            tvStatus.setText("正在配送中");
        }
        String distance;
        if (bean.distance < 1) {
            if (bean.distance < 0.5) {
                distance = "<500m";
            } else {
                distance = ">500m";
            }
        } else {
            BigDecimal b = new BigDecimal(bean.distance);
            distance = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue() + "km";
        }
        tvDistance.setText("剩余" + distance);
        tvCallRider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + bean.ridePhone);
                intent.setData(data);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化订单信息
     */
    private void initOrderInfo(OrderDetailEntity.DataBean bean) {
        tvOrderNumber.setText(bean.orderNumber);
        String time = DateFormatUtils.getFormatedDateTime(DateFormatUtils.PATTERN_1, bean.orderFormtime);
        tvOrderTime.setText(time);
        tvOrderRemark.setText(bean.remark);
        tvRiderName.setText(bean.rideName);
    }

    @OnClick({R.id.ll_back, R.id.btn_evaluate, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_evaluate:
                if (mDataBean != null) {
                    Intent intent = new Intent(this, AddCommentActivity.class);
                    intent.putExtra("orderFormId", String.valueOf(mDataBean.orderFormId));
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.btn_submit:
                if (mDataBean != null) {
                    switch (mDataBean.orderFormStatus) {
                        case -1://未付款
                            Intent intent = new Intent(this, PaymentMethodActivity.class);
                            intent.putExtra("money", String.valueOf(mDataBean.actualMoney));
                            intent.putExtra("orderNumber",mDataBean.orderNumber);
                            intent.putExtra("orderFormId", String.valueOf(mDataBean.orderFormId));
                            startActivity(intent);
                            finish();
                            break;
                        case 0: //商家接单中
                            finish();
                            break;
                        case 1: //商家已接单, 骑手未接单
                            finish();
                            break;
                        case 2: //待配送
                            //确认收货
                            mPresenter.confirm(String.valueOf(mDataBean.orderFormId));
                            break;
                        case 4://待收货
                            //确认收货
                            mPresenter.confirm(String.valueOf(mDataBean.orderFormId));
                            break;
                        case 5://订单已完成未评价
                            break;
                        case 6://退返货
                            //去退货
                            ToastUtils.showShort("暂无退货功能, 敬请期待");
                            break;
                        case 7://完成并已评价
                            finish();
                            break;
                    }
                }
                break;
        }
    }

    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap(LatLng latLng, BitmapDescriptor bitmapDescriptor) {
        MarkerOptions markerOption = new MarkerOptions()
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location_style))
                .icon(bitmapDescriptor)
                .position(latLng)
                .draggable(false);
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
    }
}
