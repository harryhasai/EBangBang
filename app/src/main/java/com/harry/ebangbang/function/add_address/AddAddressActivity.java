package com.harry.ebangbang.function.add_address;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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
    @BindView(R.id.rb_man)
    RadioButton rbMan;
    @BindView(R.id.rb_women)
    RadioButton rbWomen;
    @BindView(R.id.radio_group1)
    RadioGroup radioGroup1;
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

    @Override
    protected int setupView() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);


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

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ConstantFinal.COMMON_REQUEST_CODE && resultCode == ConstantFinal.COMMON_RESULT_CODE) {

        }
    }
}
