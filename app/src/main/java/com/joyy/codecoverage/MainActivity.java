package com.joyy.codecoverage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.joyy.codecoverage.util.MathCalc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "[CodeCoverage]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "before onCreate()");
        super.onCreate(savedInstanceState);
        Log.d(TAG, "after onCreate()");
        Log.d(TAG, "run 1 + 2 = " + MathCalc.add(1, 2));
        Log.d(TAG, "run 1 * 2 = " + MathCalc.multiply(1, 2));
        Log.d(TAG, "run 1 / 2 = " + MathCalc.divide(1, 2));
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "before onDestroy()");
        super.onDestroy();
        Log.d(TAG, "after onDestroy()");
        generateCoverageReport();
    }

    private void generateCoverageReport() {
//        String DEFAULT_COVERAGE_FILE_PATH = getFilesDir().getPath() + "/coverage.ec";
        String DEFAULT_COVERAGE_FILE_PATH = Environment.getExternalStorageDirectory().getPath() + "/data.exec";
        File file = new File(DEFAULT_COVERAGE_FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.d(TAG, "异常 : " + e);
                e.printStackTrace();
            }
        }
        Log.d(TAG, "generateCoverageReport():" + DEFAULT_COVERAGE_FILE_PATH);
        try {
            OutputStream out = new FileOutputStream(DEFAULT_COVERAGE_FILE_PATH, false);
            Object agent = Class.forName("org.jacoco.agent.rt.RT")
                    .getMethod("getAgent")
                    .invoke(null);

            out.write((byte[]) agent.getClass().getMethod("getExecutionData", boolean.class)
                    .invoke(agent, false));
            out.close();
        } catch (Exception e) {
            Log.d(TAG, e.toString(), e);
        }
    }
}

