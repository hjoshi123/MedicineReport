package me.blume.medicinereport;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Retro {

    @POST("1cemvhx1")
    Call<String> sendCoded(@Body String code);

    @POST("check")
    @FormUrlEncoded
    Call<RespinsePOJO> lotValidate(@Header("Content-Type") String lot_val, @FieldMap Map<String, String> fields);

}
