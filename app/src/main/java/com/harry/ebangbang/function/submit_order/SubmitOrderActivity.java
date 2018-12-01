package com.harry.ebangbang.function.submit_order;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.ConstantFinal;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.function.add_address.AddAddressActivity;
import com.harry.ebangbang.function.choose_coupon.ChooseCouponActivity;
import com.harry.ebangbang.function.payment_method.PaymentMethodActivity;
import com.harry.ebangbang.network.entity.GeneratePrepaidOrdersEntity;
import com.harry.ebangbang.network.entity.SubmitOrderEntity;
import com.harry.ebangbang.utils.DateFormatUtils;
import com.harry.ebangbang.utils.SPUtils;
import com.ruffian.library.RTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/11/27.
 * 提交订单页面
 */
public class SubmitOrderActivity extends BaseActivity<SubmitOrderPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_person_name)
    TextView tvPersonName;
    @BindView(R.id.tv_new_address)
    RTextView tvNewAddress;
    @BindView(R.id.tv_person_phone)
    TextView tvPersonPhone;
    @BindView(R.id.tv_person_address)
    TextView tvPersonAddress;
    @BindView(R.id.tv_send_out)
    TextView tvSendOut;
    @BindView(R.id.tv_send_out_time)
    RTextView tvSendOutTime;
    @BindView(R.id.fl_send_out)
    FrameLayout flSendOut;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.tv_box_price)
    TextView tvBoxPrice;
    @BindView(R.id.tv_transport_price)
    TextView tvTransportPrice;
    @BindView(R.id.tv_discount_desc)
    TextView tvDiscountDesc;
    @BindView(R.id.tv_discount_price)
    TextView tvDiscountPrice;
    @BindView(R.id.fl_discount_price)
    FrameLayout flDiscountPrice;
    @BindView(R.id.tv_cash_coupon)
    RTextView tvCashCoupon;
    @BindView(R.id.fl_cash_coupon)
    FrameLayout flCashCoupon;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private String shopId;
    private String ids;
    /**
     * 延迟时间
     */
    private int delayTime;
    /**
     * 商品总价
     */
    private double total;
    /**
     * 地址ID
     */
    private String addresseeId;
    /**
     * 餐盒费
     */
    private double countBox;
    /**
     * 配送费
     */
    private double dispatchingCost;
    /**
     * 满减
     */
    private double minus;

    @Override
    protected int setupView() {
        return R.layout.activity_submit_order;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        shopId = getIntent().getStringExtra("shopId");
        ids = getIntent().getStringExtra("ids");

        tvTitle.setText("提交订单");

        mPresenter.getPageDetail(shopId, ids);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.SUBMIT_ORDER_ACTIVITY_GET_PAGE_DETAIL);
        tags.add(DisposableFinal.SUBMIT_ORDER_ACTIVITY_SUBMIT_ORDER);
        return tags;
    }

    @Override
    protected SubmitOrderPresenter bindPresenter() {
        return new SubmitOrderPresenter();
    }

    @OnClick({R.id.ll_back, R.id.tv_new_address, R.id.fl_send_out, R.id.fl_cash_coupon, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_new_address://新增地址
                startActivityForResult(new Intent(this, AddAddressActivity.class), ConstantFinal.COMMON_REQUEST_CODE);
                break;
            case R.id.fl_send_out://立即送出
                sendOutTime();
                break;
            case R.id.fl_cash_coupon://商家代金券
                Intent intent = new Intent(this, ChooseCouponActivity.class);
                intent.putExtra("total", String.valueOf(total));
                startActivityForResult(intent, ConstantFinal.COUPON_REQUEST_CODE);
                break;
            case R.id.btn_submit://提交
                submitOrder();
                break;
        }
    }

    /**
     * 选择送出时间
     */
    private void sendOutTime() {
        long arriveTime = System.currentTimeMillis() + delayTime * 60 * 1000;
        long dateTime = 1000 * 60 * 20;
        final List<String> timeList = new ArrayList<>();
        timeList.add("立即送出 | 预计" + DateFormatUtils.getFormatedDateTime(DateFormatUtils.PATTERN_7, arriveTime));
        timeList.add(DateFormatUtils.getFormatedDateTime(DateFormatUtils.PATTERN_7, arriveTime + dateTime));
        timeList.add(DateFormatUtils.getFormatedDateTime(DateFormatUtils.PATTERN_7, arriveTime + dateTime * 2));
        timeList.add(DateFormatUtils.getFormatedDateTime(DateFormatUtils.PATTERN_7, arriveTime + dateTime * 3));
        timeList.add(DateFormatUtils.getFormatedDateTime(DateFormatUtils.PATTERN_7, arriveTime + dateTime * 4));
        timeList.add(DateFormatUtils.getFormatedDateTime(DateFormatUtils.PATTERN_7, arriveTime + dateTime * 5));

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setCancelable(true);
        View view = View.inflate(this, R.layout.dialog_select_time, null);
        TextView tvCurrentTime = view.findViewById(R.id.tv_current_time);
        tvCurrentTime.setText(getDayOfWeek());
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SelectTimeAdapter adapter = new SelectTimeAdapter(R.layout.item_select_time, timeList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {
                    tvSendOut.setText("立即送出");
                    tvSendOutTime.setText("大约" + delayTime + "分钟送达");
                } else {
                    tvSendOut.setText("指定时间");
                    tvSendOutTime.setText(timeList.get(position));
                }
                bottomSheetDialog.dismiss();
            }
        });
    }

    private String getDayOfWeek() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                return "今日(周日)";
            case 2:
                return "今日(周一)";
            case 3:
                return "今日(周二)";
            case 4:
                return "今日(周三)";
            case 5:
                return "今日(周四)";
            case 6:
                return "今日(周五)";
            case 7:
                return "今日(周六)";
        }
        return "";
    }

    /**
     * 初始化页面参数
     *
     * @param data
     */
    public void initParam(SubmitOrderEntity.DataBean data) {
        tvPersonName.setText(data.addressee.name);//订餐人
        tvPersonPhone.setText(data.addressee.phone); //订餐人电话
        tvPersonAddress.setText(data.addressee.site);//订餐人地址
        tvSendOut.setText("立即送出");
        delayTime = data.deliveryTime;
        tvSendOutTime.setText("大约" + delayTime + "分钟送达");
        tvShopName.setText(data.shop.shopName); //店家名称

        for (int i = 0; i < data.goods.size(); i++) {
            SubmitOrderEntity.DataBean.GoodsBean goodsBean = data.goods.get(i);
            View view = View.inflate(this, R.layout.item_submit_order, null);
            ImageView ivFoodImg = view.findViewById(R.id.iv_food_img);
            TextView tvFoodName = view.findViewById(R.id.tv_food_name);
            TextView tvFoodPrice = view.findViewById(R.id.tv_food_price);
            TextView tvFoodCount = view.findViewById(R.id.tv_food_count);

            Picasso.with(this)
                    .load(SPUtils.getString(UserInfo.HEADER_BASE.name(), "") + goodsBean.goodsimg)
                    .error(R.drawable.ic_error)
//                .transform(new PicassoCircleTransform())
                    .resize(ConvertUtils.dp2px(34), ConvertUtils.dp2px(34))
                    .centerCrop()
                    .into(ivFoodImg);

            tvFoodName.setText(goodsBean.goodsName); //商品名称
            tvFoodPrice.setText("¥ " + goodsBean.price); //商品价格
            tvFoodCount.setText("x " + goodsBean.standby); //商品个数
            llContainer.addView(view);
        }
        countBox = data.countBox;
        tvBoxPrice.setText("¥ " + countBox); //餐盒费
        dispatchingCost = data.dispatchingCost;
        tvTransportPrice.setText("¥ " + dispatchingCost); //配送费
        tvDiscountDesc.setText("满" + data.meetMinus.meet + "减" + data.meetMinus.minus);
        minus = data.meetMinus.minus;
        tvDiscountPrice.setText("- ¥ " + minus);//满减
        total = data.total;
        tvTotalPrice.setText(String.valueOf(total)); //总价

        addresseeId = String.valueOf(data.addressee.id);
    }

    /**
     * 提交订单
     */
    private void submitOrder() {
//        shopId – 商铺id
//        addresseeId – 收件人id
//        money – 总金额
//        mealBoxMoney – 餐盒费
//        dispatchingMoney – 配送费
//        meetSubtract – 满减减去金额
//        goodsIds – 商品ids id 商品id num 商品数量 例如 [ { "id": 1, "num": 2 }, { "id": 2, "num": 3 } ]
//        remark – 备注
        String remark = etRemark.getText().toString().trim();
        remark = TextUtils.isEmpty(remark) ? "" : remark;
        mPresenter.submitOrder(shopId, addresseeId, String.valueOf(total), String.valueOf(countBox),
                String.valueOf(dispatchingCost), String.valueOf(minus), ids, remark);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ConstantFinal.COMMON_REQUEST_CODE && resultCode == ConstantFinal.COMMON_RESULT_CODE) {
            mPresenter.getPageDetail(shopId, ids);
        } else if (requestCode == ConstantFinal.COUPON_REQUEST_CODE && resultCode == ConstantFinal.COUPON_RESULT_CODE) {
            double money = data.getDoubleExtra("money", 0);
            tvCashCoupon.setText("-¥" + money);
            tvCashCoupon.setTextColor(getResources().getColor(R.color.red));
            total = total - money;
            tvTotalPrice.setText(String.valueOf(total)); //总价
        }

    }

    /**
     * 生成订单成功 跳转到选择支付的页面
     */
    public void goToPay(GeneratePrepaidOrdersEntity.DataBean dataBean) {
        Intent intent = new Intent(this, PaymentMethodActivity.class);
        intent.putExtra("orderNumber", dataBean.orderNumber);
        intent.putExtra("money", dataBean.money);
        intent.putExtra("orderFormId", String.valueOf(dataBean.orderFormId));
        startActivity(intent);
        setResult(ConstantFinal.COMMON_RESULT_CODE);
        finish();
    }
}
