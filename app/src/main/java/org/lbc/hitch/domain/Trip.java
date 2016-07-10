package org.lbc.hitch.domain;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Trip {

    private String driver_id;
    private Geo start_point;
    private Geo end_point;
    private Time departing_time;
    private String cancellationTime;
    private Date departing_date;

    public Trip() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    // TODO create setter for setting cancellationTime
    public Trip( String driver_id, Geo departingDestination, Geo finalDestination, Time departingTime, String cancellationTime, Date date) {
        this.driver_id = driver_id;
        this.start_point = departingDestination;
        this.end_point = finalDestination;
        this.departing_time = departingTime;
        this.cancellationTime = cancellationTime;
        this.departing_date = date;
    }

    public String getCancellationTime() {
        return cancellationTime;
    }

    public String getDriverId() {
        return driver_id;
    }

    public Geo getStart_point() {
        return start_point;
    }

    public Geo getEnd_point() {
        return end_point;
    }

    public Time getDeparting_time() {
        return departing_time;
    }

    public Date getDeparting_date() {
        return departing_date;
    }

    public void setCancellationTime(String cancellationTime) {
        this.cancellationTime = cancellationTime;
    }

    public void setDeparting_time(Time departing_time) {
        this.departing_time = departing_time;
    }

    public void setStart_point(Geo start_point) {
        this.start_point = start_point;
    }

    public void setEnd_point(Geo end_point) {
        this.end_point = end_point;
    }

    @Exclude
    @Override
    public String toString() {
        return "Trip{" +
                "driver_id='" + driver_id + '\'' +
                ", start_point='" + start_point + '\'' +
                ", end_point='" + end_point + '\'' +
                ", departing_time='" + departing_time + '\'' +
                ", cancellationTime='" + cancellationTime + '\'' +
                '}';
    }
}
