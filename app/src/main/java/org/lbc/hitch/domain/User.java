package org.lbc.hitch.domain;

import java.sql.Timestamp;

/**
 * Created by Kelby on 4/8/2016
 * User DAO domain access object to use within app, maybe serialized to pass between activities
 * Consider creating DTO (domain transfer object) to first get JSON from JSON server using Jackson Mapper
 * TODO write unit test
 */
public class User {
    private String userId;
    private String name;
    private String lastName;
    private String gender;
    private Integer age;
    private String city;
    private State state;
    private Timestamp memberSinceDate;

    // TODO consider making constructorless depending on how user is retrieved
    // TODO consider jackson mapping if all json
    public User(String userId, String name, String lastName, String gender, Integer age, String city, State state, Timestamp memberSinceDate) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.city = city;
        this.state = state;
        this.memberSinceDate = memberSinceDate;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public State getState() {
        return state;
    }

    public Timestamp getMemberSinceDate() {
        return memberSinceDate;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setMemberSinceDate(Timestamp memberSinceDate) {
        this.memberSinceDate = memberSinceDate;
    }
}
