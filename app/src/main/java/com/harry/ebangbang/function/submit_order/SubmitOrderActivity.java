package com.harry.ebangbang.function.submit_order;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.function.add_address.AddAddressActivity;
import com.harry.ebangbang.network.entity.SubmitOrderEntity;
import com.harry.ebangbang.utils.SPUtils;
import com.ruffian.library.RTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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

    @Override
    protected int setupView() {
        return R.layout.activity_submit_order;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        String shopId = getIntent().getStringExtra("shopId");
        String ids = getIntent().getStringExtra("ids");

        tvTitle.setText("提交订单");

        mPresenter.getPageDetail(shopId, ids);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.SUBMIT_ORDER_ACTIVITY_GET_PAGE_DETAIL);
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
                startActivity(new Intent(this, AddAddressActivity.class));
                break;
            case R.id.fl_send_out://立即送出
                break;
            case R.id.fl_cash_coupon://商家代金券
                break;
            case R.id.btn_submit://提交
                break;
        }
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
        tvSendOutTime.setText("大约" + data.deliveryTime + "分钟送达");
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
        tvBoxPrice.setText("¥ " + data.countBox); //餐盒费
        tvTransportPrice.setText("¥ " + data.dispatchingCost); //配送费
        tvDiscountDesc.setText("满" + data.meetMinus.meet + "减" + data.meetMinus.minus);
        tvDiscountPrice.setText("- ¥ " + data.meetMinus.minus);//满减
        tvTotalPrice.setText(String.valueOf(data.total)); //总价
    }
}
