package com.jimikongjian.models;

import java.sql.Timestamp;

/**
 * Created by fuxiuyang on 17-5-16.
 */
public class ApartmentInfo {

    private int id;

    private int apartmentId;

    private int rentAll;

    private int appointAll;

    private int rentNumber;

    private Timestamp apartmentDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public int getRentAll() {
        return rentAll;
    }

    public void setRentAll(int rentAll) {
        this.rentAll = rentAll;
    }

    public int getAppointAll() {
        return appointAll;
    }

    public void setAppointAll(int appointAll) {
        this.appointAll = appointAll;
    }

    public int getRentNumber() {
        return rentNumber;
    }

    public void setRentNumber(int rentNumber) {
        this.rentNumber = rentNumber;
    }

    public Timestamp getApartmentDay() {
        return apartmentDay;
    }

    public void setApartmentDay(Timestamp apartmentDay) {
        this.apartmentDay = apartmentDay;
    }
}
