package com.wudongdong.navigationbar;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

/**
 * 可以使用title
 * 创建人：吴冬冬<br/>
 * 创建时间：2019/12/18 19:53 <br/>
 */
public class NavigationBar extends AbsNavigationBar<NavigationBar.Builder> {
    protected NavigationBar(Builder builder) {
        super(builder);
    }

    /*void setA() {
        getBuilder().setA();
    }*/


    /**
     * 新的builder
     * 如果是全局统一样式，可以不需要传递布局进来，直接在构造方法里面设置好
     * 还需要添加一些比较直观的方法。如：setLeftTextStr setTitleStr setRightTextStr 等等方法
     */
    public static class Builder extends AbsNavigationBar.Builder<Builder, AbsNavigationBar.Builder.NavigationParams, NavigationBar> {

        public Builder(@NonNull Context context, @LayoutRes int layoutId, @NonNull ViewGroup parent) {
            super(context, layoutId, parent);
        }

        /*public Builder setA(){
            return this;
        }*/

        @Override
        public NavigationBar build() {
            return new NavigationBar(this);
        }
    }
}
