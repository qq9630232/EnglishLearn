package com.example.englishlearn.utils.Network;


import android.content.pm.ApplicationInfo;

import com.example.englishlearn.base.BaseApplication;

public class BaseAppUtils {
    /**
     * 但是当我们没在AndroidManifest.xml中设置其debug属性时:
     * 使用Eclipse运行这种方式打包时其debug属性为true,使用Eclipse导出这种方式打包时其debug属性为法false.
     * 在使用ant打包时，其值就取决于ant的打包参数是release还是debug.
     * 因此在AndroidManifest.xml中最好不设置android:debuggable属性置，而是由打包方式来决定其值.
     *
     * @return
     * @author SHANHY
     * @date 2015-8-7
     */
    public static boolean isDebug() {
        ApplicationInfo info = BaseApplication.getApp().getApplicationInfo();
        return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }



}
