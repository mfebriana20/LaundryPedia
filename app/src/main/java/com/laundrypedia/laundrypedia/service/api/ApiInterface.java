package com.laundrypedia.laundrypedia.service.api;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by yzzzd on 4/12/16.
 */
public interface ApiInterface {

    @FormUrlEncoded
    @POST("cust/login.php")
    Call<ResponseBody> loginCust(
            @Field("email") String email,
            @Field("password") String pass
    );
}
