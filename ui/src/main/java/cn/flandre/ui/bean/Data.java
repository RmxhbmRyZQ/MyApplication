package cn.flandre.ui.bean;

import java.util.LinkedHashMap;

public class Data {
    private int code;
    private String message;
    private Content data;

    public Content getData() {
        return data;
    }

    public static class Content{
        private LinkedHashMap<String, String> scoring_trend;

        public LinkedHashMap<String, String> getScoring_trend() {
            return scoring_trend;
        }
    }
}
