package com.demo.mummyding.apitest.model;

import java.util.Date;

/**
 * Created by mummyding on 15-11-8.
 */
public class DateBean {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private Date date =null;
    private static final String MONTH [] =
            {"","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        for(int i=0;i<MONTH.length;i++)
            if(MONTH[i].equalsIgnoreCase(month)){
                this.month = i;
                return;
            }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public String toString() {
        String date = getYear()+"年"+getMonth()+"月"+getDay()+"日"+getHour()+"点"+getMonth()+"分"+getSecond()+"秒";
        return date;
    }

    public Date getDate() {
        if(date == null){
            date = new Date(year,month,day,hour,minute,second);
        }
        return date;
    }
}
