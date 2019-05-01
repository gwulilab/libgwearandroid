package edu.gwu.seas.gwulilab.libgwearandroid;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GWear {

    Retrofit retrofit;
    ApiService apiService;

    String apiKey;

    public GWear(String baseUrl, String apiKey) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        this.apiKey = apiKey;
    }

    public List<PatientData> getPatientData() {
        final List<PatientData> patientData = new ArrayList<>();

        Call<List<PatientData>> call = apiService.getPatientData(apiKey);

        call.enqueue(new Callback<List<PatientData>>() {
            @Override
            public void onResponse(Call<List<PatientData>> call, Response<List<PatientData>> response) {
                if (response.isSuccessful()) {
                    patientData.clear();
                    patientData.addAll(response.body());

                    Log.i("returned", patientData.toString());
                } else {
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        System.out.println("Could not parse error body");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PatientData>> call, Throwable t) {
                Log.e("db", t.toString());
            }
        });

        return patientData;
    }

    public void sendPatientData(int id,
                                int timestamp,
                                String mac,
                                String sensor,
                                double value,
                                String metric) {

        PatientData patientData = new PatientData();

        patientData.setId(id);
        patientData.setTimestamp(timestamp);
        patientData.setMac(mac);
        patientData.setSensor(sensor);
        patientData.setValue(value);
        patientData.setMetric(metric);

        Call<PostResponse> postPatientData = apiService.sendPatientData(patientData);

        postPatientData.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("returned", "Post successful!");
                } else {
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        System.out.println("Could not parse error body");
                    }
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.e("db", t.toString());
            }
        });

        apiService.sendPatientData(patientData);
    }
}
