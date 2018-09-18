package edu.gwu.seas.gwulilab.libgwearandroid;

import android.util.Log;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Database connection between an Android app and InfluxDB
 *
 * @author Nam Tran
 * @version 0.2
 */
public class DatabaseConnection extends Thread {

    // Variables storing connection data for the InfluxDB database (final because they never change)
    private final String url;
    private final String username;
    private final String password;
    private final String databaseName;

    // Network connection
    private InfluxDB influxDB; // Connection to InfluxDB

    // Data handling
    private Queue<Point> buffer = new LinkedList<>();
    private int maxPossibleBufferSize = 1024;
    private int sendBufferSize = 10;

    /**
     * Constructor
     *
     * @param url URL of the InfluxDB database, including port
     * @param username InfluxDB login username
     * @param password InfluxDB login password
     * @param databaseName Target database for this app
     */
    public DatabaseConnection(String url, String username, String password, String databaseName) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.databaseName = databaseName;
    }

    /**
     * Constructor
     *
     * @param url URL of the InfluxDB database, including port
     * @param databaseName Target database for this app
     */
    public DatabaseConnection(String url, String databaseName) {
        this(url, "", "", databaseName);
    }

    @Override
    public void run() {
        // Constantly attempts to send data while the thread is uninterrupted
        while (!isInterrupted()) {
            sendData();
        }
    }

    /**
     * Adds a data point to a buffer, that will send when the buffer reaches a certain size
     *
     * @param point Data point to add
     */
    public void addData(Point point) {
        // Throws out data if overflowing
        if (buffer.size() == maxPossibleBufferSize) {
            buffer.remove();
        }

        buffer.add(point);
    }

    /**
     * Sets what buffer size the app sends data at
     *
     * @param sendBufferSize Size that buffer has to be before sending
     */
    public void setSendBufferSize(int sendBufferSize) {
        this.sendBufferSize = sendBufferSize;
    }


    /**
     * Writes data to InfluxDB as a batch when enough points are collected
     */
    private void sendData() {
        if (buffer.size() >= sendBufferSize) {
            try {
                // Establishes a connection to the database
                if (username.isEmpty()) {
                    influxDB = InfluxDBFactory.connect(url);
                } else {
                    influxDB = InfluxDBFactory.connect(url, username, password);
                }

                // Sets console logging level to basic
                influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);

                // Creates the batch
                BatchPoints batchPoints = BatchPoints
                        .database(databaseName)
                        .build();

                // Adds each data point from the buffer into the batch
                for (int i = 0; i < sendBufferSize; i++) {
                    batchPoints.point(buffer.poll());
                }

                // Writes the batch to InfluxDB
                influxDB.write(batchPoints);

                // Closes the data connection
                influxDB.close();

                // For handling connection exceptions
            } catch (Exception e) {
                Log.e("db", e.toString());
            }
        }
    }
}
