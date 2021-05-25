package com.example.englishlearn.bean;

import java.util.List;

/**
 * Created by songdechuan on 2021/5/24.
 */

public class WordBean {

    /**
     * code : 200
     * message : success
     * data : [{"wordId":1,"wordName":"spit","audio":"[spit]","explanation":"n. 唾液，口水，小雨 v. 唾吐，吐出，降小雨","example":"The boys were spitting out of the train window ","grade":1,"study":0,"remember":0,"collection":0},{"wordId":2,"wordName":"spit","audio":"[spit]","explanation":"n. 唾液，口水，小雨 v. 唾吐，吐出，降小雨","example":"The boys were spitting out of the train window ","grade":1,"study":1,"remember":0,"collection":0},{"wordId":3,"wordName":"blast","audio":" [blɑ:st]","explanation":"n. 一阵(强风)，爆炸声，爆破  v. 爆破，摧毁","example":"The bomb blast killed several harmless passers-by","grade":1,"study":1,"remember":1,"collection":0},{"wordId":4,"wordName":"spill","audio":" [spil]","explanation":"v. 溢出，洒，使...流出，泄漏 n. 溢出，流","example":"Don`studywords`\u2019t jog me, or you\u2019ll make me spill something!","grade":1,"study":0,"remember":0,"collection":0},{"wordId":5,"wordName":"candidate","audio":"[\u2019kændidit]","explanation":"n. 候选人，求职者","example":"  In my estimation, he is a more suitable candidate.","grade":1,"study":0,"remember":0,"collection":0},{"wordId":6,"wordName":"slip ","audio":" [slip]","explanation":"v.滑倒；滑脱；疏忽；溜走 adj. 滑动的；有活结的；活络的","example":" The ship slipped into the harbour at night","grade":1,"study":0,"remember":0,"collection":1},{"wordId":7,"wordName":"consume","audio":"  [kən\u2019sju:m]","explanation":"v. 消耗，花费，挥霍","example":"  Consuming or eager to consume great amounts of food; ravenous","grade":1,"study":0,"remember":0,"collection":0},{"wordId":8,"wordName":"breed ","audio":" [bri:d]","explanation":"v. 繁殖，养育 n. 品种，血统","example":"  I breed some chicks for pets.","grade":1,"study":0,"remember":0,"collection":0},{"wordId":9,"wordName":"slide","audio":"  [slaid]","explanation":"vi. 滑，滑动，滑入，悄悄地溜走","example":" House values may begin to slide","grade":1,"study":1,"remember":1,"collection":0},{"wordId":10,"wordName":"dispose","audio":" [di\u2019spəuz]","explanation":"vt. 倾向于，处置","example":"  I must dispose of the trouble","grade":1,"study":0,"remember":0,"collection":0},{"wordId":11,"wordName":"transmit","audio":" [trænz\u2019mit]","explanation":"vt. 传输，传送，代代相传，传达 ","example":" This infection is transmitted by mosquitoes","grade":1,"study":0,"remember":0,"collection":0},{"wordId":12,"wordName":"transport","audio":" [træns\u2019pɔ:t]","explanation":"n.运输；输送；运送；运输机 v.运输，流放，为强烈的情绪所激动","example":" The goods have been cased up for transport. ","grade":1,"study":0,"remember":0,"collection":0},{"wordId":13,"wordName":"acute ","audio":"[ə\u2019kju:t]","explanation":"adj. 敏锐的，剧烈的","example":"The patient is complaining of acute earache.","grade":2,"study":1,"remember":1,"collection":0},{"wordId":14,"wordName":"acknowledge","audio":"[ək\u2019nɔlidʒ]","explanation":"vt. 承认，公认，告知收到，表示感谢，注意到","example":"He is unwilling to acknowledge defeat.","grade":2,"study":0,"remember":0,"collection":0},{"wordId":15,"wordName":"absurd","audio":" [əb\u2019sə:d]","explanation":"adj. 荒唐的","example":" He looked absurd in that get-up","grade":2,"study":0,"remember":0,"collection":0},{"wordId":16,"wordName":"acquaint","audio":"[ə\u2019kweint]","explanation":"vt. 使...熟知","example":"You must acquaint yourself with your new duties.","grade":2,"study":0,"remember":0,"collection":0},{"wordId":17,"wordName":"abrupt","audio":"[ə\u2019brʌpt]","explanation":"adj. 突然的，意外的，唐突的，陡峭的","example":"It\u2019s very unlike him to be so abrupt.","grade":2,"study":0,"remember":0,"collection":0}]
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
         * wordId : 1
         * wordName : spit
         * audio : [spit]
         * explanation : n. 唾液，口水，小雨 v. 唾吐，吐出，降小雨
         * example : The boys were spitting out of the train window
         * grade : 1
         * study : 0
         * remember : 0
         * collection : 0
         */

        private int wordId;
        private String wordName;
        private String audio;
        private String explanation;
        private String example;
        private int grade;
        private int study;
        private int remember;
        private int collection;

        public int getWordId() {
            return wordId;
        }

        public void setWordId(int wordId) {
            this.wordId = wordId;
        }

        public String getWordName() {
            return wordName;
        }

        public void setWordName(String wordName) {
            this.wordName = wordName;
        }

        public String getAudio() {
            return audio;
        }

        public void setAudio(String audio) {
            this.audio = audio;
        }

        public String getExplanation() {
            return explanation;
        }

        public void setExplanation(String explanation) {
            this.explanation = explanation;
        }

        public String getExample() {
            return example;
        }

        public void setExample(String example) {
            this.example = example;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public int getStudy() {
            return study;
        }

        public void setStudy(int study) {
            this.study = study;
        }

        public int getRemember() {
            return remember;
        }

        public void setRemember(int remember) {
            this.remember = remember;
        }

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }
    }
}
