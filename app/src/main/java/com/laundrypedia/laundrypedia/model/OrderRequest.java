package com.laundrypedia.laundrypedia.model;

import java.util.ArrayList;

public class OrderRequest {

    String datePickup,timePickup,locationPickup,notePickup;
    String dateDelivery,timeDelivery,locationDelivery,noteDelivery;

    String layananModels;

    public String getDatePickup() {
        return datePickup;
    }

    public void setDatePickup(String datePickup) {
        this.datePickup = datePickup;
    }

    public String getTimePickup() {
        return timePickup;
    }

    public void setTimePickup(String timePickup) {
        this.timePickup = timePickup;
    }

    public String getLocationPickup() {
        return locationPickup;
    }

    public void setLocationPickup(String locationPickup) {
        this.locationPickup = locationPickup;
    }

    public String getNotePickup() {
        return notePickup;
    }

    public void setNotePickup(String notePickup) {
        this.notePickup = notePickup;
    }

    public String getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(String dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public String getTimeDelivery() {
        return timeDelivery;
    }

    public void setTimeDelivery(String timeDelivery) {
        this.timeDelivery = timeDelivery;
    }

    public String getLocationDelivery() {
        return locationDelivery;
    }

    public void setLocationDelivery(String locationDelivery) {
        this.locationDelivery = locationDelivery;
    }

    public String getNoteDelivery() {
        return noteDelivery;
    }

    public void setNoteDelivery(String noteDelivery) {
        this.noteDelivery = noteDelivery;
    }

    public String getLayananModels() {
        return layananModels;
    }

    public void setLayananModels(String layananModels) {
        this.layananModels = layananModels;
    }
}
