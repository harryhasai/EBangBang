package com.harry.ebangbang.function.modify_address;

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
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.function.add_address.receiving_address.ReceivingAddressActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/12/14.
 * 修改收货地址
 */
public class ModifyAddressActivity extends BaseActivity<ModifyAddressPresenter> {

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
    private String addressId;
    private String isDefault;
    private String name;
    private String address;
    private String phone;

    @Override
    protected int setupView() {
        return R.layout.activity_modify_address;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        tvTitle.setText("修改地址");

        addressId = getIntent().getStringExtra("addressId");
        //0不默认 1默认
        isDefault = getIntent().getStringExtra("isDefault");
        name = getIntent().getStringExtra("name");
        address = getIntent().getStringExtra("address");
        phone = getIntent().getStringExtra("phone");
        longitude = getIntent().getStringExtra("longitude");
        latitude = getIntent().getStringExtra("latitude");

        initParams();

    }

    private void initParams() {
        if (isDefault.equals("1")) {
            rbYes.setChecked(true);
        } else {
            rbNo.setChecked(true);
        }
        etName.setText(name);
        etAddress.setText(address);
        etPhone.setText(phone);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.MODIFY_ADDRESS_ACTIVITY_MODIFY_ADDRESS);
        return tags;
    }

    @Override
    protected ModifyAddressPresenter bindPresenter() {
        return new ModifyAddressPresenter();
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
        mPresenter.putAddress(addressId, name, detailAddress, longitude, latitude, isDefault, phone);
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
