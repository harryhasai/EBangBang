package com.harry.ebangbang.function.errand_service;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.ConstantFinal;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.function.errand_list.ErrandListActivity;
import com.harry.ebangbang.function.errand_service.select_address.SelectAddressActivity;
import com.harry.ebangbang.function.payment_method.PaymentMethodActivity;
import com.harry.ebangbang.function.submit_order.SelectTimeAdapter;
import com.harry.ebangbang.network.entity.ErrandServiceEntity;
import com.harry.ebangbang.network.entity.ErrandServicePriceEntity;
import com.harry.ebangbang.utils.DateFormatUtils;
import com.ruffian.library.RTextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/12/15.
 * 跑腿服务
 */
public class ErrandServiceActivity extends BaseActivity<ErrandServicePresenter> {

    @BindView(R.id.tv_send_address)
    RTextView tvSendAddress;
    @BindView(R.id.tv_receive_address)
    RTextView tvReceiveAddress;
    @BindView(R.id.tv_weight)
    RTextView tvWeight;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.tv_time)
    RTextView tvTime;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    private String sendId;
    private String receiveId;
    /**
     * 物品重量
     */
    private String weightText;
    /**
     * 取货时间戳
     */
    private long pickUpTime;

    @Override
    protected int setupView() {
        return R.layout.activity_errand_service;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.ERRAND_SERVICE_ACTIVITY_SUBMIT);
        tags.add(DisposableFinal.ERRAND_SERVICE_ACTIVITY_PRICE);
        return tags;
    }

    @Override
    protected ErrandServicePresenter bindPresenter() {
        return new ErrandServicePresenter();
    }

    @OnClick({R.id.ll_back, R.id.tv_errand_list, R.id.fl_send_address, R.id.fl_receive_address, R.id.fl_weight, R.id.fl_time, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_errand_list:
                startActivity(new Intent(this, ErrandListActivity.class));
                break;
            case R.id.fl_send_address:      //发货地址
                startActivityForResult(new Intent(this, SelectAddressActivity.class), ConstantFinal.SEND_REQUEST_CODE);
                break;
            case R.id.fl_receive_address:   //收货地址
                startActivityForResult(new Intent(this, SelectAddressActivity.class), ConstantFinal.RECEIVE_REQUEST_CODE);
                break;
            case R.id.fl_weight:
                selectWeight();
                break;
            case R.id.fl_time:
                sendOutTime();
                break;
            case R.id.tv_submit:
                String remark = etRemark.getText().toString().trim();
                String price = tvPrice.getText().toString();
                if (TextUtils.isEmpty(receiveId)) {
                    ToastUtils.showShort("请选择收件地址");
                    return;
                } else if (TextUtils.isEmpty(sendId)) {
                    ToastUtils.showShort("请选择发件地址");
                    return;
                } else if (TextUtils.isEmpty(weightText)) {
                    ToastUtils.showShort("请选择重量");
                    return;
                } else if (pickUpTime == 0) {
                    ToastUtils.showShort("请选择取件时间");
                    return;
                } else if (TextUtils.isEmpty(price)) {
                    ToastUtils.showShort("价格为空, 不能提交");
                    return;
                }
                mPresenter.submit(receiveId, sendId, weightText, String.valueOf(pickUpTime),
                        remark, price.replaceAll("¥", ""));
                break;
        }
    }

    private void selectWeight() {
        List<String> weightList = new ArrayList<>();
        weightList.add("小于5kg");
        int weight = 5;
        for (int i = 0; i < 16; i++) {
            weightList.add(weight + "");
            weight++;
        }
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setCancelable(true);
        View view = View.inflate(this, R.layout.dialog_select_weight, null);
        RecyclerView rvWeight = view.findViewById(R.id.rv_weight);
        rvWeight.setLayoutManager(new LinearLayoutManager(this));
        SelectWeightAdapter adapter = new SelectWeightAdapter(R.layout.item_select_weight, weightList);
        rvWeight.setAdapter(adapter);

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {
                    weightText = "0";
                } else {
                    weightText = (String) adapter.getData().get(position);
                }
                tvWeight.setText((String) adapter.getData().get(position));
                bottomSheetDialog.dismiss();
                if (!TextUtils.isEmpty(sendId) && !TextUtils.isEmpty(receiveId)) {
                    mPresenter.price(sendId, receiveId, weightText);
                } else {
                    ToastUtils.showShort("请选择收发地址, 否则无法计算配送费");
                    tvWeight.setText("");
                }
            }
        });
    }

    /**
     * 选择送出时间
     */
    private void sendOutTime() {
        final List<String> timeList = new ArrayList<>();
        final List<Long> millisList = new ArrayList<>();

        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 50, 0);
        long dayOfLastTimeMillis = calendar.getTimeInMillis();
        long time = 20 * 60 * 1000;//间隔时间
        long beforeTime = dayOfLastTimeMillis - time;
        timeList.add(DateFormatUtils.getFormatedDateTime(DateFormatUtils.PATTERN_7, dayOfLastTimeMillis));
        millisList.add(dayOfLastTimeMillis);

        while (true) {
            if (beforeTime <= currentTimeMillis) {
                break;
            }
            timeList.add(DateFormatUtils.getFormatedDateTime(DateFormatUtils.PATTERN_7, beforeTime));
            millisList.add(beforeTime);
            beforeTime = beforeTime - time;
        }
        Collections.reverse(timeList);//倒序排列
        Collections.reverse(millisList);

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
                pickUpTime = millisList.get(position);
                tvTime.setText(timeList.get(position));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ConstantFinal.SEND_REQUEST_CODE && resultCode == ConstantFinal.COMMON_RESULT_CODE) {
            if (data != null) {
                sendId = data.getStringExtra("ID");
                String sendAddress = data.getStringExtra("address");
                tvSendAddress.setText(sendAddress);
                tvWeight.setText("");
            }
        } else if (requestCode == ConstantFinal.RECEIVE_REQUEST_CODE && resultCode == ConstantFinal.COMMON_RESULT_CODE) {
            if (data != null) {
                receiveId = data.getStringExtra("ID");
                String receiveAddress = data.getStringExtra("address");
                tvReceiveAddress.setText(receiveAddress);
                tvWeight.setText("");
            }
        }
    }

    /**
     * @param errandServicePriceEntity 获取到配送费
     */
    public void price(ErrandServicePriceEntity errandServicePriceEntity) {
        tvPrice.setText("¥" + errandServicePriceEntity.money);
    }

    /**
     * 提交成功 清空文本
     *
     * @param data
     */
    public void clearText(ErrandServiceEntity.DataBean data) {
        Intent intent = new Intent(this, PaymentMethodActivity.class);
        intent.putExtra("orderNumber", data.orderNumber);
        intent.putExtra("orderFormId", String.valueOf(data.orderId));
        intent.putExtra("money", tvPrice.getText().toString().trim().replaceAll("¥", ""));
        intent.putExtra("isErrand", true);
        startActivity(intent);

        sendId = "";
        receiveId = "";
        weightText = "";
        pickUpTime = 0L;
        tvPrice.setText("");
        tvReceiveAddress.setText("");
        tvSendAddress.setText("");
        tvWeight.setText("");
        tvTime.setText("");
        etRemark.setText("");

    }
}
