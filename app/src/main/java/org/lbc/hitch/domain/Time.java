package org.lbc.hitch.domain;

/**
 * Created by ecobos on 7/10/16.
 */
public class Time {

    private String hour;
    private String minute;

    public Time(){

    }

    public Time(String hour, String minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public String getMinute() {
        return minute;
    }

    public String getHour() {
        return hour;
    }


}
