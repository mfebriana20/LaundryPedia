package com.laundrypedia.laundrypedia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewModel {
    @SerializedName("id_review")
    @Expose
    private String idReview;
    @SerializedName("id_cust")
    @Expose
    private String idCust;
    @SerializedName("id_binatu")
    @Expose
    private String idBinatu;
    @SerializedName("star")
    @Expose
    private String star;
    @SerializedName("review_date")
    @Expose
    private String reviewDate;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("id_order")
    @Expose
    private String idOrder;

    public String getIdReview() {
        return idReview;
    }

    public void setIdReview(String idReview) {
        this.idReview = idReview;
    }

    public String getIdCust() {
        return idCust;
    }

    public void setIdCust(String idCust) {
        this.idCust = idCust;
    }

    public String getIdBinatu() {
        return idBinatu;
    }

    public void setIdBinatu(String idBinatu) {
        this.idBinatu = idBinatu;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }
}
