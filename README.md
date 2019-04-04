# libgwearandroid
[![Build Status](https://travis-ci.org/gwulilab/libgwearandroid.svg?branch=master)](https://travis-ci.org/gwulilab/libgwearandroid)

Android-optimised library for connecting sensor-enabling Android apps to our cloud system

# Dependencies
- [influxdb-java](https://github.com/influxdata/influxdb-java)

# Usage
## Gradle
The library (for now) must be cloned and then imported to your Android project. Instructions to that can be found [here](https://developer.android.com/studio/projects/android-library#AddDependency).

In the case of step 2, the following line should be added: 
```gradle
include ':app', ':libgwearandroid'
```

For step 3, this should be added:
```gradle
dependencies {
    implementation 'org.influxdb:influxdb-java:2.10'
    implementation project(":libgwearandroid")
}
```

## Module Code
Use the following code snippets in your project:

### Import Statement
```java
import edu.gwu.seas.gwulilab.libgwearandroid.DatabaseConnection;
import org.influxdb.dto.Point;
```

### Object Initialisation and Usage
```java
private DatabaseConnection database;

// onCreate code snippet
database = new DatabaseConnection("my-server.url", "influxdb-database-name");
database.start();

// Code to go in a button or timer or something
addPoint()

/**
 * Adds a data point to send to InfluxDB
 */
public void addPoint() {
    try {
        Point point = Point.measurement("testMeasure")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("region", "us-east-1")
                .addField("value", 123.0)
                .build();

        database.addData(point);
    } catch (Exception e) {
        Log.e("db", e.toString());
    }
}
```
