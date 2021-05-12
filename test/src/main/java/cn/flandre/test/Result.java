package cn.flandre.test;

public class Result {
    private Content[] data;
    private int errorCode;
    private String errorMsg;

    public Content[] getData() {
        return data;
    }

    public static class Content{
        private String[] children;
        private int courseId;
        private int id;
        private String name;
        private int order;
        private int parentChapterId;
        private boolean userControlSetTop;
        private int visible;

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }
    }
}
