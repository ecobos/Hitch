package org.lbc.hitch.domain;

import java.io.Serializable;

/**
 * Created by Kelby on 11/13/2015.
 */
public class Trip implements Serializable {

    private String driver_id;
    private int passengerCount;
    private String departingDestination;
    private String finalDestination;
    private String departingTime;
    private String cancellationTime = null;

    // TODO consider removing cancelledAt param since this is for a new trip
    // TODO create setter for setting cancellationTime
    public Trip(String driver, int passengers, String departingDest, String finalDest,
                String departingTime, String cancelledAt) {
        this.driver_id = driver;
        this.passengerCount = passengers;
        this.departingDestination = departingDest;
        this.finalDestination = finalDest;
        this.departingTime = departingTime;
        this.cancellationTime = cancelledAt;
    }

    public String getDriverByID() {
        return driver_id;
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

    public String getCancellationTime() {
        return cancellationTime;
    }
}
