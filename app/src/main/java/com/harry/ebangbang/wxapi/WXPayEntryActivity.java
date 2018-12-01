package com.harry.ebangbang.wxapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.harry.ebangbang.application.EBangBangApplication;
import com.harry.ebangbang.event.PaySuccessEvent;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Harry on 2018/12/1.
 */
public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //这句没有写,是不能执行回调的方法的
        EBangBangApplication.getAppContext().mWXAPI.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        //0     成功	展示成功页面
        //-1    错误	可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
        //-2    用户取消	无需处理。发生场景：用户不支付了，点击取消，返回APP。
        if(baseResp.errCode==0){
            Toast.makeText(this,"支付成功!",Toast.LENGTH_SHORT).show();
            EventBus.getDefault().postSticky(new PaySuccessEvent());
        }else if(baseResp.errCode==-1){
            Toast.makeText(this,"支付失败!",Toast.LENGTH_SHORT).show();

        }else if(baseResp.errCode==-2){
            Toast.makeText(this,"取消支付!",Toast.LENGTH_SHORT).show();

        }
        finish();
    }

}
