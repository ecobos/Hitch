package org.lbc.hitch.domain;

/**
 * Created by ecobos on 7/10/16.
 */
public class Geo
{
    private String lat;
    private String lon;

    public Geo()
    {

    }

    public Geo(String lat, String lon)
    {
        this.lat =lat;
        this.lon = lon;
    }

    public String getLat()
    {
        return lat;
    }

    public String getLon()
    {
        return lon;
    }
}
