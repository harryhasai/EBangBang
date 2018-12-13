package com.harry.ebangbang.function.user_info;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.BaseActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/12/13.
 * 修改个人信息页面
 */
public class UserInfoActivity extends BaseActivity<UserInfoPresenter> {

    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.rl_nick_name)
    RelativeLayout rlNickName;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.rl_birthday)
    RelativeLayout rlBirthday;

    @Override
    protected int setupView() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        String nickName = getIntent().getStringExtra("nickName");
        String birthday = getIntent().getStringExtra("birthday");

        tvNickName.setText(nickName);
        tvBirthday.setText(birthday);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.USER_INFO_ACTIVITY_SAVE);
        return tags;
    }

    @Override
    protected UserInfoPresenter bindPresenter() {
        return new UserInfoPresenter();
    }

    @OnClick({R.id.ll_back, R.id.tv_save, R.id.rl_nick_name, R.id.rl_birthday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_save:
                String nickName = tvNickName.getText().toString().trim();
                String birthday = tvBirthday.getText().toString().trim();
                if (TextUtils.isEmpty(nickName) || TextUtils.isEmpty(birthday)) {
                    ToastUtils.showShort("个人资料信息不能为空");
                    return;
                }
                mPresenter.save(nickName, birthday);
                break;
            case R.id.rl_nick_name:
                modifyNickName();
                break;
            case R.id.rl_birthday:
                showDataSelector();
                break;
        }
    }

    /**
     * 选择日期
     */
    private void showDataSelector() {
        DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                                         @Override
                                         public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                             tvBirthday.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
                                         }
                                     }
                , Calendar.getInstance().get(Calendar.YEAR)
                , Calendar.getInstance().get(Calendar.MONTH)
                , Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                .show(getFragmentManager(), "DatePickerDialog");
    }

    /**
     * 修改昵称
     */
    private void modifyNickName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = View.inflate(this, R.layout.dialog_user_info, null);
        final EditText etNickName = dialogView.findViewById(R.id.et_nick_name);
        Button btnConfirm = dialogView.findViewById(R.id.btn_confirm);
        if (!TextUtils.isEmpty(tvNickName.getText().toString().trim())) {
            etNickName.setText(tvNickName.getText().toString().trim());
            etNickName.setSelection(tvNickName.getText().toString().trim().length());
        }
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        dialog.show();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etNickName.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showShort("昵称不能为空");
                    return;
                }
                tvNickName.setText(name);
                dialog.dismiss();
            }
        });
    }
}
