package com.harry.ebangbang.function.mine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.ConstantFinal;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.BaseFragment;
import com.harry.ebangbang.function.address_management.AddressManagementActivity;
import com.harry.ebangbang.function.login.LoginActivity;
import com.harry.ebangbang.function.order_manage.OrderManageActivity;
import com.harry.ebangbang.function.user_info.UserInfoActivity;
import com.harry.ebangbang.network.entity.MineEntity;
import com.harry.ebangbang.utils.ImageUtil;
import com.harry.ebangbang.utils.PicassoCircleTransform;
import com.harry.ebangbang.utils.PictureSelectorUtils;
import com.harry.ebangbang.utils.SPUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.ruffian.library.RTextView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Harry on 2018/11/6.
 * 我的
 */
public class MineFragment extends BaseFragment<MinePresenter> {

    @BindView(R.id.iv_user_header)
    ImageView ivUserHeader;
    @BindView(R.id.tv_sign_out)
    TextView tvSignOut;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_wallet)
    RTextView tvWallet;
    @BindView(R.id.tv_red_wallet)
    RTextView tvRedWallet;
    @BindView(R.id.tv_cash_coupon)
    RTextView tvCashCoupon;
    @BindView(R.id.fl_order_manager)
    FrameLayout flOrderManager;
    Unbinder unbinder;
    private MineEntity.UserDetailsBean mUserBean;

    @Override
    protected int setupView() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        mPresenter.getUserInfo();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.MINE_FRAGMENT_GET_USER_INFO);
        tags.add(DisposableFinal.MINE_FRAGMENT_UPLOAD);
        tags.add(DisposableFinal.MINE_FRAGMENT_GET_CUSTOMER_SERVICE);
        return tags;
    }

    @Override
    protected MinePresenter bindPresenter() {
        return new MinePresenter();
    }

    /**
     * 退出登录
     */
    private void signOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setMessage("确认退出登录吗?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(mActivity, LoginActivity.class));
                        SPUtils.putBoolean(UserInfo.IS_LOGIN.name(), false);
                        mActivity.finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_user_header, R.id.tv_sign_out, R.id.fl_order_manager, R.id.fl_user_info,
            R.id.fl_customer_service, R.id.fl_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_header://修改头像
                PictureSelectorUtils.selectPicture(this);
                break;
            case R.id.tv_sign_out:
                signOut();
                break;
            case R.id.fl_order_manager: //订单管理
                startActivity(new Intent(mActivity, OrderManageActivity.class));
                break;
            case R.id.fl_customer_service: //客服电话
                mPresenter.getCustomerService();
                break;
            case R.id.fl_user_info: //个人信息
                if (mUserBean != null) {
                    Intent intent = new Intent(mActivity, UserInfoActivity.class);
                    intent.putExtra("nickName", mUserBean.nickname);
                    intent.putExtra("birthday", mUserBean.birthday);
                    startActivityForResult(intent, ConstantFinal.COMMON_REQUEST_CODE);
                } else {
                    ToastUtils.showShort("未获取到个人资料, 请检测当前网络");
                }
                break;
            case R.id.fl_address://收货地址管理
                startActivity(new Intent(mActivity, AddressManagementActivity.class));
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mUserBean == null) {
            mPresenter.getUserInfo();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PictureConfig.CHOOSE_REQUEST) {//修改头像 结果回调
            if (data == null) {
                return; //什么都不选择, 直接点击返回或者取消按钮的时候return掉
            }
            List<LocalMedia> medias = PictureSelector.obtainMultipleResult(data);
            // 1.media.getPath(); 为原图path
            // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
            // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
            // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
            String compressPath = medias.get(0).getCompressPath();
            String base64 = ImageUtil.image2Base64(compressPath);

            Picasso.with(mActivity)
                    .load(new File(medias.get(0).getPath()))
                    .error(R.drawable.ic_error)
                    .placeholder(R.drawable.ic_place_holder)
                    .transform(new PicassoCircleTransform())
                    .resize(ConvertUtils.dp2px(57), ConvertUtils.dp2px(57))
                    .centerCrop()
                    .into(ivUserHeader);

            mPresenter.upload(base64);
        } else if (requestCode == ConstantFinal.COMMON_REQUEST_CODE && resultCode == ConstantFinal.COMMON_RESULT_CODE) {
            //修改个人昵称
            mPresenter.getUserInfo();
        }

    }

    public void getUserInfo(MineEntity.UserDetailsBean userBean) {
        mUserBean = userBean;
        Picasso.with(mActivity)
                .load(SPUtils.getString(UserInfo.HEADER_BASE.name(), "") + userBean.headAddress)
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_place_holder)
                .transform(new PicassoCircleTransform())
                .resize(ConvertUtils.dp2px(57), ConvertUtils.dp2px(57))
                .centerCrop()
                .into(ivUserHeader);

        tvUserName.setText(userBean.nickname);

        SPUtils.putString(UserInfo.HEADER_URL.name(), userBean.headAddress);
        SPUtils.putString(UserInfo.NICK_NAME.name(), userBean.nickname);
    }

    /**
     * @param phone 客服电话
     */
    public void getCustomerService(final String phone) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("提示").setMessage("确定要拨打客服电话吗?")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + phone);
                        intent.setData(data);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}
