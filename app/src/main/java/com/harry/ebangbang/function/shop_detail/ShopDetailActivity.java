package com.harry.ebangbang.function.shop_detail;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gavin.com.library.StickyDecoration;
import com.gavin.com.library.listener.GroupListener;
import com.google.gson.Gson;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.ConstantFinal;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.function.goods_detail.GoodsDetailActivity;
import com.harry.ebangbang.function.shop_detail.view.ComplexPopup;
import com.harry.ebangbang.function.shop_detail.view.ComplexPopupAdapter;
import com.harry.ebangbang.function.shop_detail.view.ComplexPopupEntity;
import com.harry.ebangbang.function.shop_detail.view.MyLinearLayoutManager;
import com.harry.ebangbang.function.shopping_cart.JsonFormatBean;
import com.harry.ebangbang.function.submit_order.SubmitOrderActivity;
import com.harry.ebangbang.network.entity.ShopDetailCategoryEntity;
import com.harry.ebangbang.network.entity.ShopDetailChildEntity;
import com.harry.ebangbang.utils.SPUtils;
import com.ruffian.library.RTextView;
import com.squareup.picasso.Picasso;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/12/3.
 * 店家详情页面
 */
public class ShopDetailActivity extends BaseActivity<ShopDetailPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_shopping_cart_number)
    TextView tvShoppingCartNumber;
    @BindView(R.id.iv_shop_img)
    ImageView ivShopImg;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_sales_volume)
    TextView tvSalesVolume;
    @BindView(R.id.tv_shop_address)
    TextView tvShopAddress;
    @BindView(R.id.tv_shop_event)
    TextView tvShopEvent;
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    @BindView(R.id.rv_child_category)
    RecyclerView rvChildCategory;
    @BindView(R.id.tv_shopping_cart)
    RTextView tvShoppingCart;
    @BindView(R.id.tv_customer_service)
    RTextView tvCustomerService;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    @BindView(R.id.ll_shadow)
    LinearLayout ll_shadow;
    private String shopId;
    private ShopDetailCategoryAdapter categoryAdapter;
    private List<ShopDetailChildEntity.DataBean.GoodsBean> childList;
    private List<ShopDetailCategoryEntity.DataBean> categoryList;
    private ShopDetailChildAdapter childAdapter;
    private ComplexPopup popup;
    private List<ComplexPopupEntity> popupList;
    private ComplexPopupAdapter popupAdapter;

    @Override
    protected int setupView() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        shopId = getIntent().getStringExtra("shopId");
        childList = new ArrayList<>();
        categoryList = new ArrayList<>();

        initRecyclerView();
        initPopupWindow();

        mPresenter.getCategory(shopId);
        mPresenter.getChild(shopId);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.SHOP_DETAIL_ACTIVITY_GET_CATEGORY);
        tags.add(DisposableFinal.SHOP_DETAIL_ACTIVITY_GET_CHILD);
        tags.add(DisposableFinal.SHOP_DETAIL_ACTIVITY_ADD_GOODS);
        return tags;
    }

    @Override
    protected ShopDetailPresenter bindPresenter() {
        return new ShopDetailPresenter();
    }

    @OnClick({R.id.ll_back, R.id.tv_shopping_cart, R.id.tv_customer_service, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                exitAndSaveGoods();
                break;
            case R.id.tv_shopping_cart://打开购物车
                popup.showAtAnchorView(rlBottom, YGravity.ABOVE, XGravity.ALIGN_LEFT);
                break;
            case R.id.tv_customer_service:
                break;
            case R.id.btn_submit:
                addGoods();
                break;
        }
    }

    private void initPopupWindow() {
        popup = ComplexPopup.create(this).setDimView(ll_shadow).apply();
        popupList = new ArrayList<>();
        popupAdapter = popup.setAdapter(popupList);

        popup.removeAll(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupList.clear();
                popupAdapter.notifyDataSetChanged();
                popup.dismiss();

                for (int i = 0; i < childList.size(); i++) {
                    childList.get(i).goodsCount = 0;
                }
                childAdapter.notifyDataSetChanged();
                tvShoppingCartNumber.setVisibility(View.GONE);

                tvTotalPrice.setText("0");
            }
        });

        popupAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ComplexPopupEntity popupBean = popupList.get(position);
                switch (view.getId()) {
                    case R.id.iv_dialog_plus:
                        popupBean.count++;
                        popupAdapter.notifyDataSetChanged();

                        for (int i = 0; i < childList.size(); i++) {
                            ShopDetailChildEntity.DataBean.GoodsBean goodsBean = childList.get(i);
                            if (goodsBean.id == popupBean.id) {
                                goodsBean.goodsCount++;
                            }
                        }
                        childAdapter.notifyDataSetChanged();
                        break;
                    case R.id.iv_dialog_reduce:
                        popupBean.count--;
                        if (popupBean.count <= 0) {
                            popupList.remove(position);
                            for (int i = 0; i < childList.size(); i++) {
                                ShopDetailChildEntity.DataBean.GoodsBean goodsBean = childList.get(i);
                                if (goodsBean.id == popupBean.id) {
                                    goodsBean.goodsCount = 0;
                                }
                            }
                            popup.setCountText(popupList.size());
                        } else {
                            for (int i = 0; i < childList.size(); i++) {
                                ShopDetailChildEntity.DataBean.GoodsBean goodsBean = childList.get(i);
                                if (goodsBean.id == popupBean.id) {
                                    goodsBean.goodsCount--;
                                }
                            }
                        }
                        popupAdapter.notifyDataSetChanged();
                        childAdapter.notifyDataSetChanged();
                        break;
                }
                tvShoppingCartNumber.setText(String.valueOf(popupList.size()));
                calculationTotalPrice();
            }
        });
    }

    private void initRecyclerView() {
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        final MyLinearLayoutManager childLayoutManager = new MyLinearLayoutManager(this);
        rvChildCategory.setLayoutManager(childLayoutManager);

        categoryAdapter = new ShopDetailCategoryAdapter(R.layout.item_shop_detail_category);
        rvCategory.setAdapter(categoryAdapter);
        childAdapter = new ShopDetailChildAdapter(childList);
        rvChildCategory.setAdapter(childAdapter);

        categoryAdapter.setOnTextViewClickListener(new ShopDetailCategoryAdapter.OnTextViewClickListener() {
            @Override
            public void onClick(int shopClassifyId) {
                if (childList.size() != 0) {
                    for (int i = 0; i < childList.size(); i++) {
                        if (childList.get(i).classId == shopClassifyId) {
                            int firstItem = childLayoutManager.findFirstVisibleItemPosition();
                            int lastItem = childLayoutManager.findLastVisibleItemPosition();
                            if (i <= firstItem) {
                                rvChildCategory.scrollToPosition(i);
                            } else if (i <= lastItem) {
                                int top = rvChildCategory.getChildAt(i - firstItem).getTop();
                                rvChildCategory.smoothScrollBy(0, top);
                            } else {
                                rvChildCategory.scrollToPosition(i);
                            }
                        }
                    }
                }
            }
        });

        rvChildCategory.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int visibleItemPosition = childLayoutManager.findFirstCompletelyVisibleItemPosition();

                    for (int i = 0; i < categoryList.size(); i++) {
                        if (categoryList.get(i).shopClassifyId == childList.get(visibleItemPosition).classId) {
                            categoryAdapter.setSelection(rvCategory, i);
                        }
                    }
                }
            }
        });

        childAdapter.setOnChangeNumberListener(new ShopDetailChildAdapter.OnChangeNumberListener() {

            @Override
            public void plus(int adapterPosition, int id) {
                ShopDetailChildEntity.DataBean.GoodsBean bean = childList.get(adapterPosition);
                bean.goodsCount++;

                childAdapter.notifyDataSetChanged();

                if (popupList.size() == 0) {
                    popupList.add(new ComplexPopupEntity(bean.id, bean.goodsCount, bean.name, bean.price));
                } else {
                    int sameIndex = -1;
                    for (int i = 0; i < popupList.size(); i++) {
                        if (popupList.get(i).id == bean.id) {
                            sameIndex = i;
                        }
                    }
                    if (sameIndex == -1) {//没有 就加一条
                        popupList.add(new ComplexPopupEntity(bean.id, bean.goodsCount, bean.name, bean.price));
                    } else {//有找到 更改
                        popupList.get(sameIndex).count = bean.goodsCount;
                    }

                }
                popup.setCountText(popupList.size());
                popupAdapter.notifyDataSetChanged();

                tvShoppingCartNumber.setVisibility(View.VISIBLE);
                tvShoppingCartNumber.setText(String.valueOf(popupList.size()));

                calculationTotalPrice();
            }

            @Override
            public void reduce(int adapterPosition, int id) {
                ShopDetailChildEntity.DataBean.GoodsBean bean = childList.get(adapterPosition);
                bean.goodsCount--;
                if (bean.goodsCount <= 0) {
                    bean.goodsCount = 0;
                    for (int i = 0; i < popupList.size(); i++) {
                        if (popupList.get(i).id == bean.id) {
                            popupList.remove(i);
                        }
                    }
                    popup.setCountText(popupList.size());
                } else {
                    for (int i = 0; i < popupList.size(); i++) {
                        if (popupList.get(i).id == bean.id) {
                            popupList.get(i).count = bean.goodsCount;
                        }
                    }
                }
                childAdapter.notifyDataSetChanged();
                popupAdapter.notifyDataSetChanged();

                if (popupList.size() == 0) {
                    tvShoppingCartNumber.setVisibility(View.GONE);
                    tvTotalPrice.setText("0");
                } else {
                    calculationTotalPrice();
                    tvShoppingCartNumber.setText(String.valueOf(popupList.size()));
                }

            }

            @Override
            public void onItemClick(int goodsId) {
                Intent intent = new Intent(ShopDetailActivity.this, GoodsDetailActivity.class);
                intent.putExtra("goodsId", String.valueOf(goodsId));
                if (popupList.size() != 0) {
                    for (int i = 0; i < popupList.size(); i++) {
                        if (popupList.get(i).id == goodsId) {
                            intent.putExtra("goodsCount", String.valueOf(popupList.get(i).count));
                        }
                    }
                }
                startActivityForResult(intent, ConstantFinal.COMMON_REQUEST_CODE);
            }
        });

        StickyDecoration stickyDecoration = StickyDecoration.Builder
                .init(new GroupListener() {
                    @Override
                    public String getGroupName(int position) {
                        return childList.get(position).className;
                    }
                })
                .setGroupBackground(Color.parseColor("#f8f6fc"))        //背景色
                .setGroupHeight(ConvertUtils.dp2px(30))     //高度
//                .setDivideColor(Color.parseColor("#EE96BC"))            //分割线颜色
//                .setDivideHeight(ConvertUtils.dp2px(2))     //分割线高度 (默认没有分割线)
                .setGroupTextColor(Color.BLACK)                                    //字体颜色 （默认）
                .setGroupTextSize(ConvertUtils.dp2px(14))    //字体大小
                .setTextSideMargin(ConvertUtils.dp2px(10))  // 边距   靠左时为左边距  靠右时为右边距
                //.setHeaderCount(2)                                             // header数量（默认0）
                .build();
        rvChildCategory.addItemDecoration(stickyDecoration);


    }

    public void getCategory(List<ShopDetailCategoryEntity.DataBean> data) {
        categoryList.clear();
        categoryList.addAll(data);
        categoryAdapter.setNewData(data);
    }

    public void getChild(ShopDetailChildEntity.DataBean data) {
        tvTitle.setText(data.shopName);
        Picasso.with(this)
                .load(SPUtils.getString(UserInfo.HEADER_BASE.name(), "") + data.shopLogo)
                .error(R.drawable.ic_error)
//                .transform(new PicassoCircleTransform())
                .resize(ConvertUtils.dp2px(82), ConvertUtils.dp2px(82))
                .centerCrop()
                .into(ivShopImg);
        tvShopName.setText(data.shopName);
        tvSalesVolume.setText("月售" + data.monthSales + "单");
        String distance;
        if (data.distance < 1) {
            if (data.distance < 0.5) {
                distance = "<500m";
            } else {
                distance = ">500m";
            }
        } else {
            BigDecimal b = new BigDecimal(data.distance);
            distance = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue() + "km";
        }
        tvShopAddress.setText(distance + " | " + data.shopSite);
        if (data.meetMInus.size() > 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < data.meetMInus.size(); i++) {
                ShopDetailChildEntity.DataBean.MeetMInusBean bean = data.meetMInus.get(i);
                String s = "满" + bean.meet + "立减" + bean.minus;
                if (i == (data.meetMInus.size() - 1)) {
                    builder.append(s);
                } else {
                    builder.append(s).append(",");
                }
            }
            tvShopEvent.setText(builder);
        } else {
            tvShopEvent.setVisibility(View.INVISIBLE);
        }

        childList.clear();
        childList.addAll(data.goods);
        childAdapter.notifyDataSetChanged();
    }

    private void addGoods() {
        if (popupList.size() == 0) {
            ToastUtils.showShort("您还未选择商品, 不能结算");
            return;
        }
        List<JsonFormatBean> jsonList = new ArrayList<>();
        for (int i = 0; i < popupList.size(); i++) {
            ComplexPopupEntity popupEntity = popupList.get(i);
            //[{"id":"1","num":"4"},{"id":"4","num":"1"}]
//            jsonList.add(new JsonFormatBean(String.valueOf(popupEntity.id), String.valueOf(popupEntity.count)));
            jsonList.add(new JsonFormatBean(popupEntity.id, popupEntity.count));
        }
        Gson gson = new Gson();
        String s = gson.toJson(jsonList);

        mPresenter.addGoods(shopId, s);
    }

    private void exitAndSaveGoods() {
        if (popupList.size() > 0) {
            List<JsonFormatBean> jsonList = new ArrayList<>();
            for (int i = 0; i < popupList.size(); i++) {
                ComplexPopupEntity popupEntity = popupList.get(i);
                //[{"id":"1","num":"4"},{"id":"4","num":"1"}]
//                jsonList.add(new JsonFormatBean(String.valueOf(popupEntity.id), String.valueOf(popupEntity.count)));
                jsonList.add(new JsonFormatBean(popupEntity.id, popupEntity.count));
            }
            Gson gson = new Gson();
            String s = gson.toJson(jsonList);

            mPresenter.exitAndSaveGoods(shopId, s);
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        exitAndSaveGoods();
//        super.onBackPressed();
    }

    /**
     * 提交完成, 跳转订单页面
     */
    public void goToSubmitOrder(String s) {
        Intent intent = new Intent(this, SubmitOrderActivity.class);
        intent.putExtra("shopId", shopId);
        intent.putExtra("ids", s);
        startActivity(intent);
        finish();
    }

    private void calculationTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < popupList.size(); i++) {
            ComplexPopupEntity entity = popupList.get(i);
            totalPrice = totalPrice + entity.count * entity.price;
        }
        tvTotalPrice.setText(String.valueOf(totalPrice));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == ConstantFinal.COMMON_REQUEST_CODE && resultCode == ConstantFinal.COMMON_RESULT_CODE) {
                String goodsId = data.getStringExtra("goodsId");
                int goodsCount = data.getIntExtra("goodsCount", 0);
                String goodsName = data.getStringExtra("goodsName");
                double goodsPrice = data.getDoubleExtra("goodsPrice", 0);

                for (int i = 0; i < childList.size(); i++) {
                    ShopDetailChildEntity.DataBean.GoodsBean goodsBean = childList.get(i);
                    if (goodsBean.id == Integer.valueOf(goodsId)) {
                        goodsBean.goodsCount = goodsCount;
                        childAdapter.notifyDataSetChanged();
                    }
                }

                ComplexPopupEntity e = new ComplexPopupEntity(Integer.valueOf(goodsId), goodsCount, goodsName, goodsPrice);
                if (popupList.size() == 0 && goodsCount > 0) {
                    popupList.add(e);
                } else {
                    int sameIndex = -1;
                    for (int i = 0; i < popupList.size(); i++) {
                        ComplexPopupEntity entity = popupList.get(i);
                        if (String.valueOf(entity.id).equals(goodsId)) {
                            sameIndex = i;
                        }
                    }
                    if (sameIndex < 0) {
                        popupList.add(e);
                    } else {
                        popupList.get(sameIndex).count = goodsCount;
                    }

                }

                for (int i = 0; i < popupList.size(); i++) {
                    if (popupList.get(i).count == 0) {
                        popupList.remove(i);
                    }
                }

                if (popupList.size() == 0) {
                    tvShoppingCartNumber.setVisibility(View.GONE);
                } else {
                    tvShoppingCartNumber.setVisibility(View.VISIBLE);
                    tvShoppingCartNumber.setText(String.valueOf(popupList.size()));
                }
                popupAdapter.notifyDataSetChanged();
                calculationTotalPrice();
            }
        }
    }
}
