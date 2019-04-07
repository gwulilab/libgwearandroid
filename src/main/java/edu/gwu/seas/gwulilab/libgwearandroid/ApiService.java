package edu.gwu.seas.gwulilab.libgwearandroid;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers("{Content-Type:application/json, x-api-key:TuAVwnAabd7MmZQ2fCVAX1KPCO1xXm7h6Of6cE9c}")
    @GET("/")
    Single<PatientData> getPatientData();

    @Headers("{Content-Type:application/json, x-api-key:TuAVwnAabd7MmZQ2fCVAX1KPCO1xXm7h6Of6cE9c}")
    @POST("/")
    Single<PostResponse> sendPatientData(@Body PatientData patientData);

}
