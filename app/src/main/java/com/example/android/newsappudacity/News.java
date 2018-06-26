package com.example.android.newsappudacity;

public class News {

    private String mTitle;
    private String mWebUrl;
    private String mSectionName;
    private String mDate;
    private String mAuthor;

    public News(String title, String webUrl, String sectionName, String Date, String author) {
        mTitle = title;
        mWebUrl = webUrl;
        mSectionName = sectionName;
        mDate = Date;
        mAuthor = author;
    }


    public String getTitle() {return mTitle;}
    public void setTitle(String title) {mTitle = title;}

    public String getWebUrl() {return mWebUrl;}
    public void setWebUrl(String webUrl) {
        mWebUrl = webUrl;
    }

    public String getsectionName() {
        return mSectionName;
    }
    public void setsectionName(String sectionName) { mSectionName = sectionName;}

    public String getAuthor() {return mAuthor;}
    public void setAuthor(String author) {mAuthor = author;}

    public String getDate() {
        return mDate;
    }
    public void setDate(String Date) { mDate = Date;}

    @Override
    public String toString() {
        return "News{" +
                "title" + mTitle + '\'' +
                "author='" + mAuthor + '\'' +
                "sectionName" + mSectionName + '\'' +
                "Date" + mDate + '\'' +
                '}';
    }

}

