package com.harry.ebangbang.function.shop_detail.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.harry.ebangbang.R;
import com.zyyoona7.popup.BasePopup;

import java.util.List;

/**
 * Created by Harry on 2018/12/6.
 */
public class ComplexPopup extends BasePopup<ComplexPopup> {

    private Context context;
    private TextView tvDialogNum;
    private RecyclerView rvDialog;
    private TextView tvRemoveAll;

    public static ComplexPopup create(Context context) {
        return new ComplexPopup(context);
    }

    protected ComplexPopup(Context context) {
        this.context = context;
        setContext(context);
    }

    @Override
    protected void initAttributes() {
        setContentView(R.layout.dialog_shopping_cart, ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenHeight() / 3);
        setFocusAndOutsideEnable(true);
        setAnimationStyle(R.style.dialogWindowAnim);
        setBackgroundDimEnable(true);//允许背景变暗
        setDimValue(0.4f);//变暗的透明度(0-1)，0为完全透明
        setDimColor(Color.BLACK);//变暗的背景颜色
    }

    @Override
    protected void initViews(View view, ComplexPopup complexPopup) {
        tvDialogNum = view.findViewById(R.id.tv_dialog_num);
        rvDialog = view.findViewById(R.id.rv_dialog);
        rvDialog.setLayoutManager(new LinearLayoutManager(context));
        tvRemoveAll = view.findViewById(R.id.tv_remove_all);
    }

    public void setCountText(int num) {
        tvDialogNum.setText("已选择" + num + "件商品");
    }

    public ComplexPopupAdapter setAdapter(List<ComplexPopupEntity> data) {
        ComplexPopupAdapter adapter = new ComplexPopupAdapter(R.layout.item_dialog_shopping_dialog, data);
        rvDialog.setAdapter(adapter);
        return adapter;
    }

    public void removeAll(View.OnClickListener clickListener) {
        tvRemoveAll.setOnClickListener(clickListener);
    }
}
