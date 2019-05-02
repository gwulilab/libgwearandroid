# libgwearandroid
[![Build Status](https://travis-ci.org/gwulilab/libgwearandroid.svg?branch=master)](https://travis-ci.org/gwulilab/libgwearandroid)

Android-optimised library for connecting sensor-enabling Android apps to our cloud system

# Dependencies
- None

# Usage
## Gradle
The library (for now) must be cloned and then imported to your Android project. Instructions to that can be found [here](https://developer.android.com/studio/projects/android-library#AddDependency) under "Add your library as a dependency".

First, download the project either through git or extract it from the zip file. As with step 1, click File > New > Import Module then select where you downloaded libgwearandroid.

For step 2, the following line should be added to `settings.gradle` (should be done automatically): 
```gradle
include ':app', ':libgwearandroid'
```

For step 3, this should be added to your app's `build.gradle` dependencies:
```gradle
dependencies {
    implementation project(":libgwearandroid")
}
```

## Module Code
Use the following code snippets in your project:

### Import Statement
```java
import edu.gwu.seas.gwulilab.libgwearandroid.GWear;
```

### Object Initialisation and Usage
```java
private GWear gwear;

// Some AWS settings
private final String baseUrl = "https://your.url.here.found.in.Amazon.RDS";
private final String apiKey = "x-api-key: YouAPIGatewayKeyHere";

// onCreate code snippet
protected void onCreate(Bundle savedInstanceState) {
    gwear = new GWear(baseUrl, apiKey);
}

// Usage in a function where you want to send data
public void addPoint() {
    try {
        long epochTime = System.currentTimeMillis() / 1000;

        gwear.sendPatientData(1, (int)epochTime, "abc123",
                "test_sensor", 4.7, "test_metric");
    } catch (Exception e) {
        Log.e("db", e.toString());
    }
}
```

### Android Manifest
You also need to add the following to your AndroidManifest.xml:

`<uses-permission android:name="android.permission.INTERNET" />`
