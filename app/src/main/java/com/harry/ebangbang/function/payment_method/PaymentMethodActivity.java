package com.harry.ebangbang.function.payment_method;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.application.EBangBangApplication;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.event.PaySuccessEvent;
import com.harry.ebangbang.network.entity.WxPayEntity;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/11/30.
 * 选择支付方式页面
 */
public class PaymentMethodActivity extends BaseActivity<PaymentMethodPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.cb_wx)
    CheckBox cbWx;
    @BindView(R.id.cb_al)
    CheckBox cbAl;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    private String orderFormId;

    @Override
    protected int setupView() {
        return R.layout.activity_payment_mothod;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        String orderNumber = getIntent().getStringExtra("orderNumber");
        String money = getIntent().getStringExtra("money");
        orderFormId = getIntent().getStringExtra("orderFormId");

        tvTitle.setText("付款");
        SpannableString spannableString = new SpannableString("¥" + money);
        spannableString.setSpan(new RelativeSizeSpan(0.5f), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvMoney.setText(spannableString);
        tvOrderNumber.setText("订单号" + orderNumber);

        cbWx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbAl.setChecked(false);
                } else {
                    cbAl.setChecked(true);
                }
            }
        });

        cbAl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbWx.setChecked(false);
                } else {
                    cbWx.setChecked(true);
                }
            }
        });
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.PAYMENT_METHOD_ACTIVITY_WX_PAY);
        return tags;
    }

    @Override
    protected PaymentMethodPresenter bindPresenter() {
        return new PaymentMethodPresenter();
    }

    @OnClick({R.id.ll_back, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_commit:
                if (cbWx.isChecked()) {
                    mPresenter.wxPay(orderFormId);
                } else if (cbAl.isChecked()) {
                    mPresenter.aliPay(orderFormId);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销注册
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 吊起微信支付
     */
    public void wxPay(final WxPayEntity.MsgBean bean) {
        Runnable payRunnable = new Runnable() {  //这里注意要放在子线程
            @Override
            public void run() {
                PayReq request = new PayReq(); //调起微信APP的对象
                //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
                request.appId = bean.appid;
                request.partnerId = bean.partnerid;
                request.prepayId = bean.prepayid;
                request.packageValue = bean.packageX;
                request.nonceStr = bean.noncestr;
                request.timeStamp = String.valueOf(bean.timestamp);
                request.sign = bean.sign;
//                iwxapi.sendReq(request);//发送调起微信的请求
                EBangBangApplication.getAppContext().mWXAPI.sendReq(request);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private static final int SDK_PAY_FLAG = 1;

    /**
     * 吊起支付宝支付
     */
    public void aliPay(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(PaymentMethodActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    //对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showShort("支付成功: ");
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShort("支付失败: " + payResult);
                    }
                    break;
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void paySuccessEvent(PaySuccessEvent paySuccessEvent) {
        finish();
    }

}
