package com.example.englishlearn.utils.Network;

public interface OnRequestResult {
    /**
     * 请求成功
     */
    void onSuccess(String json);

    /**
     * 请求失败
     */
    void onFail();

    /**
     * 当前没有网络
     */
    void netUnlink();
}
