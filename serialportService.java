

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import tp.xmaihh.serialport.SerialHelper;
import tp.xmaihh.serialport.bean.ComBean;

/**
 * A minimal Android Service for demonstrating serial port communication using SerialHelper.
 */
public class SerialService extends Service {

    private SerialHelper serialHelper;
    private static final String TAG = "SerialService";

    @Override
    public void onCreate() {
        super.onCreate();

        // Step 1: Initialize SerialHelper with target port and baud rate
        serialHelper = new SerialHelper("/dev/ttyS1", 9600) {

            // Step 2: Override callback for receiving data
            @Override
            protected void onDataReceived(ComBean comBean) {
                if (comBean != null) {
                    // Convert received bytes to hexadecimal string
                    String receivedData = comBean.getHexString();
                    Log.d(TAG, "Received: " + receivedData);

                    // Process the received data here (if needed)
                }
            }
        };

        try {
            // Step 3: Open serial port connection
            serialHelper.open();
            Log.d(TAG, "Serial port opened.");
        } catch (Exception e) {
            Log.e(TAG, "Failed to open serial port: " + e.getMessage());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Optional: Send a sample command after service starts
        if (serialHelper != null && serialHelper.isOpen()) {
            String command = "68656C6C6F"; // Example HEX command "hello"
            serialHelper.sendHex(command);
            Log.d(TAG, "Sent: " + command);
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Step 4: Clean up and close the port when service is destroyed
        if (serialHelper != null && serialHelper.isOpen()) {
            serialHelper.close();
            Log.d(TAG, "Serial port closed.");
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
