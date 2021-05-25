package com.example.englishlearn.base;


import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ztc
 * 2019/8/2
 */

public class BaseApplication extends Application {
    private static BaseApplication mBaseApplication;
    private List<Activity> mActivityList = new ArrayList<>();

    @Override
    final protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;

    }





    /**
     * 把界面添加到集合
     *
     * @param activity activity对象
     */
    final public void addActivity(Activity activity) {
        if (!mActivityList.contains(activity)) {
            mActivityList.add(activity);
        }
    }

    /**
     * 移除Activity
     *
     * @param activity activity对象
     */
    final public void removeActivity(Activity activity) {
        if (!mActivityList.isEmpty()) {
            mActivityList.remove(activity);
        }
    }

    /**
     * 移除所有Activity
     */
    final public void clearActivity() {
        for (int i = mActivityList.size(); i > 0; i--) {
            Activity activity = mActivityList.get(i - 1);
            if (activity != null) {
                activity.finish();
            }
            mActivityList.remove(i - 1);
        }
    }

    public static BaseApplication getApp() {
        return mBaseApplication;
    }

}