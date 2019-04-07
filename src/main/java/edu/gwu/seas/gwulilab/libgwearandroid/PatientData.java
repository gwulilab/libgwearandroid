package edu.gwu.seas.gwulilab.libgwearandroid;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientData {

    @SerializedName("id")
    @Expose
    private Integer id;
    /**
     *
     * (Required)
     *
     */
    @SerializedName("timestamp")
    @Expose
    private List<Integer> timestamp = null;
    @SerializedName("mac")
    @Expose
    private String mac;
    @SerializedName("sensor")
    @Expose
    private String sensor;
    @SerializedName("value")
    @Expose
    private List<Double> value = null;
    @SerializedName("metric")
    @Expose
    private String metric;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * (Required)
     *
     */
    public List<Integer> getTimestamp() {
        return timestamp;
    }

    /**
     *
     * (Required)
     *
     */
    public void setTimestamp(List<Integer> timestamp) {
        this.timestamp = timestamp;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public List<Double> getValue() {
        return value;
    }

    public void setValue(List<Double> value) {
        this.value = value;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

}