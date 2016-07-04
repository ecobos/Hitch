package org.lbc.hitch.domain;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Kelby on 11/13/2015.
 */

@IgnoreExtraProperties
public class Trip {

    private long tripId;
    private String driver_id;
    private int passengerCount;
    private String departingDestination;
    private String finalDestination;
    private String departingTime;
    private String cancellationTime;
    private Date tripDate; // TODO: is this the date type we want

    public Trip() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    // TODO create setter for setting cancellationTime
    public Trip(long tripId, String driver_id, int passengerCount, String departingDestination, String finalDestination, String departingTime, String cancellationTime, Date tripDate) {
        this.tripId = tripId;
        this.driver_id = driver_id;
        this.passengerCount = passengerCount;
        this.departingDestination = departingDestination;
        this.finalDestination = finalDestination;
        this.departingTime = departingTime;
        this.cancellationTime = cancellationTime;
        this.tripDate = tripDate;
    }

    public String getCancellationTime() {
        return cancellationTime;
    }

    public long getTripId() {
        return tripId;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public String getDepartingDestination() {
        return departingDestination;
    }

    public String getFinalDestination() {
        return finalDestination;
    }

    public String getDepartingTime() {
        return departingTime;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setCancellationTime(String cancellationTime) {
        this.cancellationTime = cancellationTime;
    }

    public void setDepartingTime(String departingTime) {
        this.departingTime = departingTime;
    }

    public void setDepartingDestination(String departingDestination) {
        this.departingDestination = departingDestination;
    }

    public void setFinalDestination(String finalDestination) {
        this.finalDestination = finalDestination;
    }

    @Exclude
    @Override
    public String toString() {
        return "Trip{" +
                "driver_id='" + driver_id + '\'' +
                ", passengerCount=" + passengerCount +
                ", departingDestination='" + departingDestination + '\'' +
                ", finalDestination='" + finalDestination + '\'' +
                ", departingTime='" + departingTime + '\'' +
                ", cancellationTime='" + cancellationTime + '\'' +
                '}';
    }
}
