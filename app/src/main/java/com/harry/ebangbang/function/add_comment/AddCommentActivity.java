package com.harry.ebangbang.function.add_comment;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.network.entity.AddCommentEntity;
import com.harry.ebangbang.utils.DateFormatUtils;
import com.harry.ebangbang.utils.PicassoCircleTransform;
import com.harry.ebangbang.utils.SPUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/12/12.
 * 添加评价页面
 */
public class AddCommentActivity extends BaseActivity<AddCommentPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_rider_img)
    ImageView ivRiderImg;
    @BindView(R.id.tv_rider_name)
    TextView tvRiderName;
    @BindView(R.id.tv_rider_time)
    TextView tvRiderTime;
    @BindView(R.id.cb_yes)
    CheckBox cbYes;
    @BindView(R.id.cb_no)
    CheckBox cbNo;
    @BindView(R.id.iv_shop_img)
    ImageView ivShopImg;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.cb_star1)
    CheckBox cbStar1;
    @BindView(R.id.cb_star2)
    CheckBox cbStar2;
    @BindView(R.id.cb_star3)
    CheckBox cbStar3;
    @BindView(R.id.cb_star4)
    CheckBox cbStar4;
    @BindView(R.id.cb_star5)
    CheckBox cbStar5;
    @BindView(R.id.et_comment)
    EditText etComment;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private AddCommentEntity.DataBean mData;

    @Override
    protected int setupView() {
        return R.layout.activity_add_comment;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("添加评价");

        String orderFormId = getIntent().getStringExtra("orderFormId");

        mPresenter.getCommentInfo(orderFormId);

        initCheckBoxListener();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.ADD_COMMENT_ACTIVITY_GET_COMMENT_INFO);
        tags.add(DisposableFinal.ADD_COMMENT_ACTIVITY_ADD_COMMENT);
        return tags;
    }

    @Override
    protected AddCommentPresenter bindPresenter() {
        return new AddCommentPresenter();
    }

    public void getCommentInfo(AddCommentEntity.DataBean data) {
        mData = data;
        Picasso.with(this)
                .load(SPUtils.getString(UserInfo.HEADER_BASE.name(), "") + data.rideImg)
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_place_holder)
                .transform(new PicassoCircleTransform())
                .resize(ConvertUtils.dp2px(48), ConvertUtils.dp2px(48))
                .centerCrop()
                .into(ivRiderImg);

        Picasso.with(this)
                .load(SPUtils.getString(UserInfo.HEADER_BASE.name(), "") + data.shopLogo)
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_place_holder)
                .transform(new PicassoCircleTransform())
                .resize(ConvertUtils.dp2px(48), ConvertUtils.dp2px(48))
                .centerCrop()
                .into(ivShopImg);

        tvRiderName.setText(data.rideName);
        String time = DateFormatUtils.getFormatedDateTime(DateFormatUtils.PATTERN_1, data.accomplishTime) + "左右送达";
        tvRiderTime.setText(time);
        tvShopName.setText(data.shopName);
    }

    @OnClick({R.id.ll_back, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_submit:
                if (mData != null) {
                    if (!cbStar1.isChecked()) {
                        ToastUtils.showShort("请选择星级");
                        return;
                    }
                    int score = 1;
                    if (cbStar5.isChecked()) {
                        score = 5;
                    } else if (cbStar4.isChecked()) {
                        score = 4;
                    } else if (cbStar3.isChecked()) {
                        score = 3;
                    } else if (cbStar2.isChecked()) {
                        score = 2;
                    }
                    String rideScore;
                    if (!cbYes.isChecked() && !cbNo.isChecked()) {
                        ToastUtils.showShort("请选择对骑手小哥的满意度");
                        return;
                    }
                    if (cbYes.isChecked()) {
                        rideScore = "0";
                    } else {
                        rideScore = "1";
                    }
                    String content = etComment.getText().toString().trim();
                    if (TextUtils.isEmpty(content)) {
                        ToastUtils.showShort("请填写评论内容");
                        return;
                    }
                    mPresenter.addComment(String.valueOf(score), rideScore, String.valueOf(mData.shopId),
                            String.valueOf(mData.orderId), String.valueOf(mData.rideId), content, null);
                }
                break;
        }
    }

    private void initCheckBoxListener() {
        cbYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbNo.setChecked(false);
                    cbYes.setTextColor(getResources().getColor(R.color.red1));
                    cbNo.setTextColor(getResources().getColor(R.color.black));
                } else {
                    cbNo.setChecked(true);
                    cbYes.setTextColor(getResources().getColor(R.color.black));
                    cbNo.setTextColor(getResources().getColor(R.color.red1));
                }
            }
        });
        cbNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbYes.setChecked(false);
                    cbYes.setTextColor(getResources().getColor(R.color.black));
                    cbNo.setTextColor(getResources().getColor(R.color.red1));
                } else {
                    cbYes.setChecked(true);
                    cbYes.setTextColor(getResources().getColor(R.color.red1));
                    cbNo.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
        cbStar1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    cbStar2.setChecked(false);
                    cbStar3.setChecked(false);
                    cbStar4.setChecked(false);
                    cbStar5.setChecked(false);
                }
            }
        });
        cbStar2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    cbStar3.setChecked(false);
                    cbStar4.setChecked(false);
                    cbStar5.setChecked(false);
                } else {
                    cbStar1.setChecked(true);
                }
            }
        });
        cbStar3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    cbStar4.setChecked(false);
                    cbStar5.setChecked(false);
                } else {
                    cbStar1.setChecked(true);
                    cbStar2.setChecked(true);
                }
            }
        });
        cbStar4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    cbStar5.setChecked(false);
                } else {
                    cbStar1.setChecked(true);
                    cbStar2.setChecked(true);
                    cbStar3.setChecked(true);
                }
            }
        });
        cbStar5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbStar1.setChecked(true);
                    cbStar2.setChecked(true);
                    cbStar3.setChecked(true);
                    cbStar4.setChecked(true);
                }
            }
        });
    }
}
