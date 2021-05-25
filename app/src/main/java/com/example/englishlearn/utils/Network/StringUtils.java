package com.example.englishlearn.utils.Network;

import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * @author ztc
 * @Data 2018/8/2
 */

public class StringUtils {
    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim())
                && !"null".equalsIgnoreCase(value.trim()) &&
                !"\"null\"".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断多个字符串是否相等，如果其中有一个为空字符串或者null，则返回false，只有全相等才返回true
     */
    public static boolean isEquals(String... agrs) {
        String last = null;
        for (int i = 0; i < agrs.length; i++) {
            String str = agrs[i];
            if (isEmpty(str)) {
                return false;
            }
            if (last != null && !str.equalsIgnoreCase(last)) {
                return false;
            }
            last = str;
        }
        return true;
    }

    /**
     * 判断请求数据的格式
     *
     * @param date 请求下来的数据是否为json
     * @return true 为是json  false 为不是json
     */
    public static boolean isJson(String date) {
        if (TextUtils.isEmpty(date)) {
            return false;
        } else if (date.startsWith("{") && date.endsWith("}")) {
            return true;
        } else if (date.startsWith("[") && date.endsWith("]")) {
            return true;
        }
        return false;
    }

    /**
     * 获取textView的文字
     *
     * @param textView
     * @return
     */
    public static String getText(TextView textView) {
        if (textView == null)
            return "";
        return textView.getText().toString().trim();
    }

    private static final SparseArray<DecimalFormat> sDecimalFormatMap = new SparseArray<>(4 * 2);

    /**
     * 保留小数点后两位
     */
    public static String formatNumber(double d) {
        return formatNumber(d, 2);
    }


    /**
     * 保留小数点后n位
     */
    public static String formatNumber(double d, int n) {
        DecimalFormat df = sDecimalFormatMap.get(n);
        if (df != null) {
            return df.format(d);
        } else if (n == 0x03) {
            df = new DecimalFormat("######0.000");
        } else if (n == 0x02) {
            df = new DecimalFormat("######0.00");
        } else if (n == 0x01) {
            df = new DecimalFormat("######0.0");
        } else if (n == 0x00) {
            df = new DecimalFormat("######0");
        } else {
            //需要使用新的位数请在上方添加,并且改变 sDecimalFormatMap 初始化的长度
            throw new RuntimeException("异常小数点位数" + n);
        }
        sDecimalFormatMap.put(n, df);
        return df.format(d);
    }


    /**
     * 保留小数点后1位
     * 带逗号
     */
    public static String formatNumber2(double d, int n) {
        n += 0x10;
        DecimalFormat df = sDecimalFormatMap.get(n);
        if (df != null) {
            return df.format(d);
        } else if (n == 0x13) {
            df = new DecimalFormat("###,###,##0.000");
        } else if (n == 0x12) {
            df = new DecimalFormat("###,###,##0.00");
        } else if (n == 0x11) {
            df = new DecimalFormat("###,###,##0.0");
        } else if (n == 0x10) {
            df = new DecimalFormat("###,###,##0");
        } else {
            //需要使用新的位数请在上方添加,并且改变 sDecimalFormatMap 初始化的长度
            throw new RuntimeException("异常小数点位数" + n);
        }
        sDecimalFormatMap.put(n, df);
        return df.format(d);
    }

}
