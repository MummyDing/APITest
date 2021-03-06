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
    private String pubTime ;

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

    public void setPubTime(String pubTime,boolean isFromat) {
        if(isFromat) this.pubTime = pubTime;
        else this.pubTime = format(pubTime);
       /* this.pubTime.setDay(Integer.parseInt(Reg.RegexUtil(",.{1,2}-", pubTime)));//Reg.RegexUtil(",.{1,2}-", pubTime)));
        this.pubTime.setMonth(Reg.RegexUtil("-.{3}-", pubTime));
        this.pubTime.setYear(Integer.valueOf(Reg.RegexUtil("-.{4} ", pubTime)).intValue());
        this.pubTime.setHour(Integer.parseInt(Reg.RegexUtil(" .{2}:", pubTime)));
        this.pubTime.setMinute(Integer.parseInt(Reg.RegexUtil(":.{2}:", pubTime)));
        this.pubTime.setSecond(Integer.parseInt(Reg.RegexUtil(":.{2} ", pubTime)));*/
    }
    private String format(String pubTime){

        String date = Reg.RegexUtil("-.{4} ", pubTime)+"年"+
                Reg.RegexUtil("-.{3}-", pubTime)+"月"+
                Reg.RegexUtil(",.{1,2}-", pubTime)+"日"+
                Reg.RegexUtil(" .{2}:", pubTime)+"点"+
                Reg.RegexUtil(":.{2}:", pubTime)+"分"+
                Reg.RegexUtil(":.{2} ", pubTime)+"秒";
        return date;
    }

    @Override
    public String toString() {
        String res = "\n\n\ntitle:  "+getTitle()+"\nlink:  "+getLink()+
                "\ndescription: "+getDescription()+"\nauthor:   "
                +getAuthor()+"\npubTime:   "+getPubTime();
        return res;
    }
}
