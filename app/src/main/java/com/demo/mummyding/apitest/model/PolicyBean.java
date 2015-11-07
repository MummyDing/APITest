package com.demo.mummyding.apitest.model;

/**
 * Created by mummyding on 15-11-7.
 */
public class PolicyBean {
    private String title;
    private String author;
    private String link;
    private String description;
    private String pubTime;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    @Override
    public String toString() {
        String res = "\n\n\ntitle:  "+getTitle()+"\nlink:  "+getLink()+
                "\ndescription: "+getDescription()+"\nauthor:   "
                +getAuthor()+"\npubTime:   "+getPubTime();
        return res;
    }
}
