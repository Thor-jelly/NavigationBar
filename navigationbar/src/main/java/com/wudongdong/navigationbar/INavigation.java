package com.wudongdong.navigationbar;

import android.view.View;
import android.view.ViewGroup;

/**
 * 创建人：吴冬冬<br/>
 * 创建时间：2019/12/18 17:45 <br/>
 */
public interface INavigation {
    void createNavigationBar();

    /**
     * 绑定参数
     */
    void attachNavigationParams();

    /**
     * 将Navigation添加到父布局
     */
    void attachParent(View navigationBar, ViewGroup parent);
}
