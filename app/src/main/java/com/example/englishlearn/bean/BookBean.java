package com.example.englishlearn.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by songdechuan on 2021/5/25.
 */

public class BookBean implements Serializable{

    /**
     * code : 200
     * message : success
     * data : [{"bookId":1,"bookName":"肖申克的救赎","bookUser":"斯蒂芬·埃德温·金"},{"bookId":2,"bookName":"泰坦尼克号","bookUser":"舒特斯曼"},{"bookId":3,"bookName":"阿甘正传","bookUser":"温斯顿·格鲁姆"}]
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

    public static class DataBean implements Serializable{
        /**
         * bookId : 1
         * bookName : 肖申克的救赎
         * bookUser : 斯蒂芬·埃德温·金
         */

        private int bookId;
        private String bookName;
        private String bookUser;
        private String description;
        private String content;

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getBookUser() {
            return bookUser;
        }

        public void setBookUser(String bookUser) {
            this.bookUser = bookUser;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
