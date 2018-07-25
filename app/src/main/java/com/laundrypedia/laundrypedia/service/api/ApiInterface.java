package com.laundrypedia.laundrypedia.service.api;

import com.laundrypedia.laundrypedia.model.LaundryList;
import com.laundrypedia.laundrypedia.model.LayananList;
import com.laundrypedia.laundrypedia.model.ReviewList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by yzzzd on 4/12/16.
 */
public interface ApiInterface {

    @FormUrlEncoded
    @POST("cust/login.php")
    Call<ResponseBody> loginCust(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET()
    Call<LaundryList> getJSONLaundry(@Url String url);

    @GET()
    Call<LayananList> getJSONLayanan(@Url String url);

    @GET()
    Call<ReviewList> getJSONReview(@Url String url);
}
