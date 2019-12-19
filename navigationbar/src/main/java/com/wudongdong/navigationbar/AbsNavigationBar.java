package com.wudongdong.navigationbar;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

/**
 * title基类
 * builder设计模式
 * 创建人：吴冬冬<br/>
 * 创建时间：2019/12/18 17:49 <br/>
 */
public class AbsNavigationBar<B extends AbsNavigationBar.Builder> implements INavigation {
    private B mBuilder;
    private View mNavigationBar;

    protected AbsNavigationBar(B builder) {
        mBuilder = builder;
        createNavigationBar();
    }

    @Override
    public void createNavigationBar() {
        mNavigationBar = LayoutInflater.from(mBuilder.mParams.mContext)
                .inflate(mBuilder.mParams.mLayoutId, mBuilder.mParams.mParent, false);
        // 添加
        attachParent(mNavigationBar, mBuilder.mParams.mParent);
        // 绑定参数
        attachNavigationParams();
    }

    /**
     * 绑定参数
     */
    @Override
    public void attachNavigationParams() {
        //设置文本
        setTextStr();
        //设置点击事件
        setOnClickListener();
    }

    /**
     * 设置点击事件
     */
    private void setOnClickListener() {
        SparseArray<View.OnClickListener> listenerSA = mBuilder.mParams.mViewClickListenerSA;
        if (listenerSA != null) {
            for (int i = 0; i < listenerSA.size(); i++) {
                int viewId = listenerSA.keyAt(i);
                View.OnClickListener onClickListener = listenerSA.valueAt(i);
                ViewGroup parent = mBuilder.mParams.mParent;
                if (parent == null) {
                    break;
                }
                View view = parent.findViewById(viewId);
                view.setOnClickListener(onClickListener);
            }
        }
    }

    /**
     * 设置文本
     */
    private void setTextStr() {
        SparseArray<String> textSA = mBuilder.mParams.mTextSA;
        if (textSA != null) {
            //配置文本
            for (int i = 0; i < textSA.size(); i++) {
                int viewId = textSA.keyAt(i);
                String value = textSA.valueAt(i);
                ViewGroup parent = mBuilder.mParams.mParent;
                if (parent == null) {
                    break;
                }
                TextView tv = parent.findViewById(viewId);
                tv.setText(value);
            }
        }
    }

    /**
     * 添加到父布局
     */
    @Override
    public void attachParent(View navigationBar, ViewGroup parent) {
        parent.addView(navigationBar, 0);
    }

    /**
     * 返回 Builder
     */
    public B getBuilder() {
        return mBuilder;
    }

    /**
     * builder构建类
     */
    public static abstract class Builder<B extends Builder, P extends Builder.NavigationParams, A extends AbsNavigationBar> {
        public final P mParams;

        public Builder(@NonNull Context context, @LayoutRes int layoutResId, @NonNull ViewGroup parent) {
            mParams = (P) new NavigationParams(context, layoutResId, parent);
        }

        /**
         * 设置文本
         */
        public B setText(int viewId, String string) {
            SparseArray<String> sa = mParams.mTextSA;
            if (sa == null) {
                sa = new SparseArray<>();
                mParams.mTextSA = sa;
            }
            sa.append(viewId, string);

            return (B) this;
        }

        /**
         * 设置文本
         */
        public B setOnClickListener(int viewId, View.OnClickListener onClickListener) {
            SparseArray<View.OnClickListener> sa = mParams.mViewClickListenerSA;
            if (sa == null) {
                sa = new SparseArray<>();
                mParams.mViewClickListenerSA = sa;
            }
            sa.append(viewId, onClickListener);

            return (B) this;
        }

        public abstract A build();

        public static class NavigationParams {
            public Context mContext;
            public int mLayoutId;
            public ViewGroup mParent;

            public SparseArray<String> mTextSA;
            public SparseArray<View.OnClickListener> mViewClickListenerSA;

            public NavigationParams(Context context, int layoutId, ViewGroup parent) {
                mContext = context;
                mLayoutId = layoutId;
                mParent = parent;
            }
        }
    }
}
