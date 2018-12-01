package com.harry.ebangbang.function.add_address;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.ConstantFinal;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.function.add_address.receiving_address.ReceivingAddressActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/11/29.
 * 新增地址页面
 */
public class AddAddressActivity extends BaseActivity<AddAddressPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.rb_yes)
    RadioButton rbYes;
    @BindView(R.id.rb_no)
    RadioButton rbNo;
    @BindView(R.id.radio_group2)
    RadioGroup radioGroup2;
    @BindView(R.id.btn_save)
    Button btnSave;
    private String latitude;
    private String longitude;

    @Override
    protected int setupView() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        tvTitle.setText("新增地址");
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.ADD_ADDRESS_ACTIVITY_PUT_ADDRESS);
        return tags;
    }

    @Override
    protected AddAddressPresenter bindPresenter() {
        return new AddAddressPresenter();
    }

    @OnClick({R.id.ll_back, R.id.tv_location, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_location:
                startActivityForResult(new Intent(this, ReceivingAddressActivity.class), ConstantFinal.COMMON_REQUEST_CODE);
                break;
            case R.id.btn_save:
                saveAddress();
                break;
        }
    }

    private void saveAddress() {
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String location = tvLocation.getText().toString().trim();
        String detailAddress = etAddress.getText().toString().trim();
        String isDefault;
        if (rbYes.isChecked()) {
            isDefault = "1";
        } else {
            isDefault = "0";
        }
        if (TextUtils.isEmpty(name)) {
            ToastUtils.showShort("请填写联系人");
            return;
        } else if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort("请填写手机号");
            return;
        } else if (TextUtils.isEmpty(location)) {
            ToastUtils.showShort("请选择收货地址");
            return;
        } else if (TextUtils.isEmpty(detailAddress)) {
            ToastUtils.showShort("请填写详细地址");
            return;
        }
        mPresenter.putAddress(name, detailAddress, longitude, latitude, isDefault, phone);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ConstantFinal.COMMON_REQUEST_CODE && resultCode == ConstantFinal.COMMON_RESULT_CODE) {
            String title = data.getStringExtra("title");
            latitude = data.getStringExtra("latitude");
            longitude = data.getStringExtra("longitude");
            tvLocation.setText(title);
        }
    }
}
