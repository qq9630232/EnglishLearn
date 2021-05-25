package com.example.englishlearn.bean;

/**
 * Created by songdechuan on 2021/5/24.
 */

public class WordProgressBean {


    /**
     * code : 200
     * message : success
     * data : {"knowCount":0,"unKnowCount":0}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * knowCount : 0
         * unKnowCount : 0
         */

        private int knowCount;
        private int unKnowCount;

        public int getKnowCount() {
            return knowCount;
        }

        public void setKnowCount(int knowCount) {
            this.knowCount = knowCount;
        }

        public int getUnKnowCount() {
            return unKnowCount;
        }

        public void setUnKnowCount(int unKnowCount) {
            this.unKnowCount = unKnowCount;
        }
    }
}
