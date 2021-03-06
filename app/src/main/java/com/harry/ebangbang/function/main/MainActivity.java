package com.harry.ebangbang.function.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.R;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.function.classification.ClassificationFragment;
import com.harry.ebangbang.function.home.HomeFragment;
import com.harry.ebangbang.function.mine.MineFragment;
import com.harry.ebangbang.function.shopping_cart.ShoppingCartFragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    private HomeFragment homeFragment;
    private ClassificationFragment classificationFragment;
    private ShoppingCartFragment shoppingCartFragment;
    private MineFragment mineFragment;

    @Override
    protected int setupView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        setupBottomNavigationBar();

        bottomNavigationBar.selectTab(0);
    }

    /**
     * 切换首页
     */
    public void swichTab(int position) {
        bottomNavigationBar.selectTab(position);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        // super.onSaveInstanceState(outState, outPersistentState);
        //不保存因为异常原因丢失掉的界面状态
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    /**
     * 初始化底部导航栏
     */
    private void setupBottomNavigationBar() {

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        //角标的数字
//        BadgeItem numberBadgeItem = new BadgeItem()
//                .setBorderWidth(0)
//                .setBackgroundColorResource(R.color.colorAccent)
//                .setText("5")
//                .setHideOnSelect(false);

//        numberBadgeItem.toggle(false); //开启或者关闭角标数字提示, 不传参数也可以

//        ShapeBadgeItem shapeBadgeItem = new ShapeBadgeItem()

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_bottom_navigation1, "首页").setInActiveColor(R.color.black1).setActiveColorResource(R.color.app_status_bar_color))
                .addItem(new BottomNavigationItem(R.drawable.ic_bottom_navigation2, "分类").setInActiveColor(R.color.black1).setActiveColorResource(R.color.app_status_bar_color))
                .addItem(new BottomNavigationItem(R.drawable.ic_bottom_navigation3, "购物车").setInActiveColor(R.color.black1).setActiveColorResource(R.color.app_status_bar_color))
                .addItem(new BottomNavigationItem(R.drawable.ic_bottom_navigation4, "我的").setInActiveColor(R.color.black1).setActiveColorResource(R.color.app_status_bar_color))
                .setFirstSelectedPosition(0)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                if (homeFragment != null) ft.hide(homeFragment);
                if (classificationFragment != null) ft.hide(classificationFragment);
                if (shoppingCartFragment != null) ft.hide(shoppingCartFragment);
                if (mineFragment != null) ft.hide(mineFragment);

                switch (position) {
                    case 0:
                        if (homeFragment == null) {
                            homeFragment = new HomeFragment();
                            ft.add(R.id.fl_container, homeFragment);
                        } else {
                            ft.show(homeFragment);
                        }
                        break;
                    case 1:
                        if (classificationFragment == null) {
                            classificationFragment = new ClassificationFragment();
                            ft.add(R.id.fl_container, classificationFragment);
                        } else {
                            ft.show(classificationFragment);
                        }
                        break;
                        case 2:
                        if (shoppingCartFragment == null) {
                            shoppingCartFragment = new ShoppingCartFragment();
                            ft.add(R.id.fl_container, shoppingCartFragment);
                        } else {
                            ft.show(shoppingCartFragment);
                        }
                        break;
                    case 3:
                        if (mineFragment == null) {
                            mineFragment = new MineFragment();
                            ft.add(R.id.fl_container, mineFragment);
                        } else {
                            ft.show(mineFragment);
                        }
                        break;
                    default:
                        break;
                }
                ft.commit();
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {     //判断当前是否点击的是back键
            exitSystemByDoubleClick();
        }
        return false;
    }

    /**
     * 是否双击退出应用程序
     */
    private boolean isExit = false;

    /**
     * 双击退出应用程序
     */
    private void exitSystemByDoubleClick() {
        Timer timer;
        if (!isExit) {
            isExit = true;
            ToastUtils.showShort("再按一次退出程序");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; //取消退出
                }
            }, 2000);       // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            System.exit(0);
        }
    }
}
