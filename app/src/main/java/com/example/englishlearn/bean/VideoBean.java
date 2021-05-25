package com.example.englishlearn.bean;

import java.util.List;

/**
 * Created by songdechuan on 2021/5/22.
 */

public class VideoBean {


    /**
     * code : 200
     * message : success
     * data : [{"id":1,"title":"空中英语课堂","content":"1","url":"http://aiccsca-1251489075.cos.ap-beijing.myqcloud.com/asr/video1.mp4","is_active":"1"},{"id":2,"title":"英语语法入门","content":"1","url":"http://aiccsca-1251489075.cos.ap-beijing.myqcloud.com/asr/video2.mp4","is_active":"1"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * title : 空中英语课堂
         * content : 1
         * url : http://aiccsca-1251489075.cos.ap-beijing.myqcloud.com/asr/video1.mp4
         * is_active : 1
         */

        private int id;
        private String title;
        private String content;
        private String url;
        private String is_active;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIs_active() {
            return is_active;
        }

        public void setIs_active(String is_active) {
            this.is_active = is_active;
        }
    }
}
