package com.namestore.alicenote.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kienht on 11/9/16.
 */

public class FirstSetup {

    public String bussinessName;

    public String bussinessType;

    public String state;

    public String city;

    public String postCode;

    public String address;

    public String workDay;

    public int startTimeSalon;

    public int endTimeSalon;

    public boolean checkedWordDay = false;

    List<SubServices> subServicesList = new ArrayList<>();

    public String getBussinessName() {
        return bussinessName;
    }

    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName;
    }

    public String getBussinessType() {
        return bussinessType;
    }

    public void setBussinessType(String bussinessType) {
        this.bussinessType = bussinessType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkDay() {
        return workDay;
    }

    public void setWorkDay(String workDay) {
        this.workDay = workDay;
    }

    public int getStartTimeSalon() {
        return startTimeSalon;
    }

    public void setStartTimeSalon(int startTimeSalon) {
        this.startTimeSalon = startTimeSalon;
    }

    public int getEndTimeSalon() {
        return endTimeSalon;
    }

    public void setEndTimeSalon(int endTimeSalon) {
        this.endTimeSalon = endTimeSalon;
    }

    public boolean isCheckedWordDay() {
        return checkedWordDay;
    }

    public void setCheckedWordDay(boolean checkedWordDay) {
        this.checkedWordDay = checkedWordDay;
    }

    public List<SubServices> getSubServicesList() {
        return subServicesList;
    }

    public void setSubServicesList(List<SubServices> subServicesList) {
        this.subServicesList = subServicesList;
    }
}
