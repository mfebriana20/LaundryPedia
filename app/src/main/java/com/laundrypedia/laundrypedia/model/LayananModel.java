package com.laundrypedia.laundrypedia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LayananModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nama_layanan")
    @Expose
    private String namaLayanan;
    @SerializedName("picture")
    @Expose
    private Object picture;
    @SerializedName("item_price")
    @Expose
    private String itemPrice;
    @SerializedName("description")
    @Expose
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaLayanan() {
        return namaLayanan;
    }

    public void setNamaLayanan(String namaLayanan) {
        this.namaLayanan = namaLayanan;
    }

    public Object getPicture() {
        return picture;
    }

    public void setPicture(Object picture) {
        this.picture = picture;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
