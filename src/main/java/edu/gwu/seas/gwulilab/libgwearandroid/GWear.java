package edu.gwu.seas.gwulilab.libgwearandroid;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GWear {

    Retrofit retrofit;
    ApiService apiService;

    public GWear() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://213xjpuh93.execute-api.us-east-1.amazonaws.com/beta/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public Single<PatientData> getPatientData() {
        return apiService.getPatientData();
    }

    public void sendPatientData(int id,
                                List<Integer> timestamp,
                                String mac,
                                String sensor,
                                List<Double> value,
                                String metric) {

        PatientData patientData = new PatientData();

        patientData.setId(id);
        patientData.setMac(mac);
        patientData.setMetric(metric);
        patientData.setSensor(sensor);
        patientData.setTimestamp(timestamp);
        patientData.setValue(value);

        apiService.sendPatientData(patientData);
    }
}
