package org.lbc.hitch.domain;

import com.google.firebase.database.IgnoreExtraProperties;
//Firebase require your Pojo to have public variables or getter/setter.

@IgnoreExtraProperties
public class Date {

    private String year;
    private String month;
    private String day;

    public Date()
    {

    }

    public Date(String day, String month, String year)
    {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
