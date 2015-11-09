package com.demo.mummyding.apitest.model;

import android.util.Log;

import com.demo.mummyding.apitest.utils.Reg;

/**
 * Created by mummyding on 15-11-7.
 */
public class PolicyBean {
    private String title;
    private String author;
    private String link;
    private String description;
    private DateBean pubTime = new DateBean();

    public PolicyBean() {
    }

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
        return  description ;
    }
    public void setDescription(String description) {
        this.description = Reg.RegexReplaceUtil("<[^>]+>",description,"");
    }

    public String getPubTime() {
        return pubTime.toString();
    }

    public void setPubTime(String pubTime) {
        this.pubTime.setDay(Integer.parseInt(Reg.RegexUtil(",.{1,2}-", pubTime)));//Reg.RegexUtil(",.{1,2}-", pubTime)));
        this.pubTime.setMonth(Reg.RegexUtil("-.{3}-", pubTime));
        this.pubTime.setYear(Integer.valueOf(Reg.RegexUtil("-.{4} ", pubTime)).intValue());
        this.pubTime.setHour(Integer.parseInt(Reg.RegexUtil(" .{2}:", pubTime)));
        this.pubTime.setMinute(Integer.parseInt(Reg.RegexUtil(":.{2}:", pubTime)));
        this.pubTime.setSecond(Integer.parseInt(Reg.RegexUtil(":.{2} ", pubTime)));
    }

    @Override
    public String toString() {
        String res = "\n\n\ntitle:  "+getTitle()+"\nlink:  "+getLink()+
                "\ndescription: "+getDescription()+"\nauthor:   "
                +getAuthor()+"\npubTime:   "+getPubTime();
        return res;
    }
}
