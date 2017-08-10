package com.vumanhcuong.rssfeedreader.models;


public class Item {
    private String mTitle;
    private String mPubDate;
    private String mLink;
    private String mDescription;

    public Item() {
        mTitle = "";
        mPubDate = "";
        mLink = "";
        mDescription = "";
    }

    public Item(String title, String pubDate, String link, String description) {
        mTitle = title;
        mPubDate = pubDate;
        mLink = link;
        mDescription = description;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPubDate() {
        return mPubDate;
    }

    public void setPubDate(String pubDate) {
        mPubDate = pubDate;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
