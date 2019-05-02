package edu.gwu.seas.gwulilab.libgwearandroid;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @GET("patient-data")
    Call<List<PatientData>> getPatientData(@Header("x-api-key") String apiKey);

    @Headers("x-api-key:TuAVwnAabd7MmZQ2fCVAX1KPCO1xXm7h6Of6cE9c")
    @POST("patient-data")
    Call<PostResponse> sendPatientData(@Body PatientData patientData);

}
