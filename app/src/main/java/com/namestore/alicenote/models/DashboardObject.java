package com.namestore.alicenote.models;

import java.io.Serializable;

/**
 * Created by Nhocnhinho on 5/26/2015.
 */


public class DashboardObject implements Serializable {

    private int ID;

    private String tvNameSevice;
    private String tvDate;
    private String tvTimeStart;
    private String tvNameStaff;
    private String tvDuration;

    public String getTvNameSevice() {
        return tvNameSevice;
    }

    public String getTvDate() {
        return tvDate;
    }

    public String getTvTimeStart() {
        return tvTimeStart;
    }

    public String getTvNameStaff() {
        return tvNameStaff;
    }

    public String getTvDuration() {
        return tvDuration;
    }

    public void setTvNameSevice(String tvNameSevice) {
        this.tvNameSevice = tvNameSevice;
    }

    public void setTvDate(String tvDate) {
        this.tvDate = tvDate;
    }

    public void setTvTimeStart(String tvTimeStart) {
        this.tvTimeStart = tvTimeStart;
    }

    public void setTvDuration(String tvDuration) {
        this.tvDuration = tvDuration;
    }

    public void setTvNameStaff(String tvNameStaff) {
        this.tvNameStaff = tvNameStaff;
    }


    public DashboardObject(int ID) {
        super();
        this.ID = ID;

    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public DashboardObject(int ID, String tvNameSevice, String tvDate, String tvTimeStart, String tvNameStaff, String tvDuration) {
        super();
        this.ID = ID;
        this.tvNameSevice = tvNameSevice;
        this.tvDate =tvDate;
        this.tvTimeStart =tvTimeStart;
        this.tvNameStaff=tvNameStaff;
        this.tvDuration=tvDuration;
    }
}
