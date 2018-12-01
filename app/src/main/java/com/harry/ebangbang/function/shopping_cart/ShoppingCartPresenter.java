package com.harry.ebangbang.function.shopping_cart;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.network.entity.ShoppingCartEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/11/23.
 */
public class ShoppingCartPresenter extends BasePresenter<ShoppingCartFragment> {

    private final ShoppingCartModel model;

    public ShoppingCartPresenter() {
        model = new ShoppingCartModel();
    }

    public void getShoppingList() {
        model.getShoppingList(new Observer<ShoppingCartEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SHOPPING_CART_FRAGMENT_GET_SHOPPING_LIST, d);
            }

            @Override
            public void onNext(ShoppingCartEntity shoppingCartEntity) {
                if (shoppingCartEntity.code == 1) {
                    view.getShoppingList(shoppingCartEntity.data);
                } else {
                    ToastUtils.showShort(shoppingCartEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 刷新列表, 用于局部刷新, 加减 选择的状态
     */
    public void updateShoppingList() {
        model.getShoppingList(new Observer<ShoppingCartEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SHOPPING_CART_FRAGMENT_GET_SHOPPING_LIST, d);
            }

            @Override
            public void onNext(ShoppingCartEntity shoppingCartEntity) {
                if (shoppingCartEntity.code == 1) {
                    view.updateShoppingList(shoppingCartEntity.data);
                } else {
                    ToastUtils.showShort(shoppingCartEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void delete(String shopId) {
        model.delete(shopId, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SHOPPING_CART_FRAGMENT_DELETE, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    ToastUtils.showShort("删除成功");
                    view.delete();
                } else {
                    ToastUtils.showShort(commonEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void add(String shopId, String goodsId) {
        model.add(shopId, goodsId, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SHOPPING_CART_FRAGMENT_ADD, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    updateShoppingList();
                } else {
                    ToastUtils.showShort(commonEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void reduce(String shopId, String goodsId) {
        model.reduce(shopId, goodsId, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SHOPPING_CART_FRAGMENT_REDUCE, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    updateShoppingList();
                } else {
                    ToastUtils.showShort(commonEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * @param isChecked //0选中 1 不选中
     */
    public void check(String shopId, String goodsId, int isChecked) {
        model.check(shopId, goodsId, isChecked, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SHOPPING_CART_FRAGMENT_CHECK, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    updateShoppingList();
                } else {
                    ToastUtils.showShort(commonEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
