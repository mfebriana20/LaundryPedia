package com.laundrypedia.laundrypedia.model;

public class Layanan {
    private String namaLayanan, itemPrice, description;

    public Layanan(String namaLayanan, String itemPrice, String description) {
        this.namaLayanan = namaLayanan;
        this.itemPrice = itemPrice;
        this.description = description;
    }


    public String getNamaLayanan() {
        return namaLayanan;
    }

    public void setNamaLayanan(String namaLayanan) {
        this.namaLayanan = namaLayanan;
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
