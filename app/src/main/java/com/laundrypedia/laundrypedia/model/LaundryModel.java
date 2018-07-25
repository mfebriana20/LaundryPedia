package com.laundrypedia.laundrypedia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaundryModel {

    @SerializedName("id_binatu")
    @Expose
    private String idBinatu;
    @SerializedName("store_name")
    @Expose
    private String storeName;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("postcode")
    @Expose
    private Object postcode;
    @SerializedName("saldo")
    @Expose
    private String saldo;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("activation_code")
    @Expose
    private String activationCode;
    @SerializedName("nama_pemilik")
    @Expose
    private String namaPemilik;
    @SerializedName("no_rekening")
    @Expose
    private String noRekening;
    @SerializedName("activation_status")
    @Expose
    private String activationStatus;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;

    public String getIdBinatu() {
        return idBinatu;
    }

    public void setIdBinatu(String idBinatu) {
        this.idBinatu = idBinatu;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getPostcode() {
        return postcode;
    }

    public void setPostcode(Object postcode) {
        this.postcode = postcode;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public String getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(String activationStatus) {
        this.activationStatus = activationStatus;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }
}
