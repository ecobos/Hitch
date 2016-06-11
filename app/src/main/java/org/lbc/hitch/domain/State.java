package org.lbc.hitch.domain;

/**
 * Created by Kelby on 4/8/2016
 * List of states as enums
 * TODO write unit test
 */
public enum State {
    ALABAMA("ALABAMA", "AL"),
    ALASKA("ALASKA", "AK"),
    ARIZONA("ARIZONA", "AZ"),
    ARKANSAS("ARKANSAS","AR"),
    CALIFORNIA("CALIFORNIA","CA"),
    COLORADO("COLORADO", "CO"),
    CONNECTICUT("CONNECTICUT","CT"),
    DELAWARE("DELAWARE","DE"),
    FLORIDA("FLORIDA","FL"),
    GEORGIA("GEORGIA", "GA"),
    HAWAII("HAWAII", "HI"),
    IDAHO("IDAHO", "ID"),
    ILLINOIS("ILLINOIS", "IL"),
    INDIANA("INDIANA",	"IN"),
    IOWA("IOWA","IA"),
    KANSAS("KANSAS", "KS"),
    KENTUCKY("KENTUCKY", "KY"),
    LOUISIANA("LOUISIANA", "LA"),
    MAINE("MAINE", "ME"),
    MARYLAND("MARYLAND","MD"),
    MASSACHUSETTS("MASSACHUSETTS", "MA"),
    MICHIGAN("MICHIGAN", "MI"),
    MINNESOTA("MINNESOTA", "MN"),
    MISSISSIPPI("MISSISSIPPI", "MS"),
    MISSOURI("MISSOURI", "MO"),
    MONTANA("MONTANA", "MT"),
    NEBRASKA("NEBRASKA", "NE"),
    NEVADA("NEVADA", "NV"),
    NEW_HAMPSHIRE("NEW HAMPSHIRE", "NH"),
    NEW_JERSEY("NEW JERSEY", "NJ"),
    NEW_MEXICO("NEW MEXICO", "NM"),
    NEW_YORK("NEW YORK", "NY"),
    NORTH_CAROLINA("NORTH CAROLINA", "NC"),
    NORTH_DAKOTA("NORTH DAKOTA", "ND"),
    OHIO("OHIO", "OH"),
    OKLAHOMA("OKLAHOMA", "OK"),
    OREGON("OREGON", "OR"),
    PENNSYLVANIA("PENNSYLVANIA", "PA"),
    RHODE_ISLAND("RHODE ISLAND", "RI"),
    SOUTH_CAROLINA("SOUTH CAROLINA", "SC"),
    SOUTH_DAKOTA("SOUTH DAKOTA", "SD"),
    TENNESSEE("TENNESSEE", "TN"),
    TEXAS("TEXAS", "TX"),
    UTAH("UTAH", "UT"),
    VERMONT("VERMONT","VT"),
    VIRGINIA("VIRGINIA", "VA"),
    WASHINGTON("WASHINGTON", "WA"),
    WEST_VIRGINIA("WEST VIRGINIA", "WV"),
    WISCONSIN("WISCONSIN", "WI"),
    WYOMING("WYOMING","WY");

    private String name;
    private String code;

    State(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
