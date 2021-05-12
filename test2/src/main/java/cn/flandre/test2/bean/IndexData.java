package cn.flandre.test2.bean;

public class IndexData {
    private Data data;
    private int errorCode;
    private String errorMsg;

    public Data getData() {
        return data;
    }

    public static class Data {
        private int curPage;
        private Datas[] datas;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;

        public Datas[] getDatas() {
            return datas;
        }
    }

    public static class Datas {
        private String apkLink;
        private int audit;
        private String author;
        private boolean canEdit;
        private int chapterId;
        private String chapterName;
        private boolean collect;
        private int courseId;
        private String desc;
        private String descMd;
        private String envelopePic;
        private boolean fresh;
        private String host;
        private int id;
        private String link;
        private String niceDate;
        private String niceShareDate;
        private String origin;
        private String prefix;
        private String projectLink;
        private long publishTime;
        private int realSuperChapterId;
        private int selfVisible;
        private long shareDate;
        private String shareUser;
        private int superChapterId;
        private String superChapterName;
        private Tags[] tags;
        private String title;
        private int type;
        private int userId;
        private int visible;
        private int zan;

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }
    }

    public static class Tags{
        private String name;
        private String url;
    }
}
