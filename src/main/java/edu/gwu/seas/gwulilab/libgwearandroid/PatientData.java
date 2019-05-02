package edu.gwu.seas.gwulilab.libgwearandroid;

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
    private Integer timestamp;
    @SerializedName("mac")
    @Expose
    private String mac;
    @SerializedName("sensor")
    @Expose
    private String sensor;
    @SerializedName("value")
    @Expose
    private Double value;
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
    public Integer getTimestamp() {
        return timestamp;
    }

    /**
     *
     * (Required)
     *
     */
    public void setTimestamp(Integer timestamp) {
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

}