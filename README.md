# 🔌 Android Serial Port Communication Example

This is a minimal example of how to use a serial port (`/dev/ttyS1`) on Android using the `SerialHelper` class from the `xmaihh` library.

---

## ✅ Features

- Opens a serial port with a custom baud rate
- Receives data as hexadecimal strings
- Sends example hex command on startup
- Gracefully closes the connection when the service is stopped

---

## 📦 Dependencies

You must include the [xmaihh/SerialPort](https://github.com/xmaihh/SerialPortHelper) library in your project:

```groovy
dependencies {
    implementation 'com.github.xmaihh:SerialPortHelper:2.1.6'
}
```

Also add JitPack to your repositories in build.gradle:
```grovy
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

🔧 AndroidManifest.xml
```manifest
<uses-permission android:name="android.permission.SERIAL_PORT" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

<application>
    <service android:name=".SerialService" />
</application>
```

🚀 How It Works
The SerialService is started as a background Android service.

It opens the serial port /dev/ttyS1 with baud rate 9600.
When data is received, it is logged as a hexadecimal string.
A sample hex command (5020FA) is sent on service start.
When the service is stopped, the serial port is closed.

📄 Example Usage
To start the service from an Activity:
```java
Intent intent = new Intent(this, SerialService.class);
startService(intent);
```

🛑 Stop the Service
```java
Intent intent = new Intent(this, SerialService.class);
stopService(intent);
```



