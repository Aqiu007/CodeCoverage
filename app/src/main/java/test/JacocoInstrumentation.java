package test;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class JacocoInstrumentation extends Instrumentation {
    public static String TAG = "[JacocoInstrumentation]";
    private Intent mIntent;

    @Override
    public void onCreate(Bundle bundle) {
        Log.d(TAG, "onCreate(" + bundle + ")");
        super.onCreate(bundle);
        String DEFAULT_COVERAGE_FILE_PATH = getContext().getFilesDir().getPath() + "/coverage.ec";

        File file = new File(DEFAULT_COVERAGE_FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.d(TAG, "异常 : " + e);
                e.printStackTrace();
            }
        }
        mIntent = new Intent(getTargetContext(), InstrumentedActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start(); //调用onStart
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart()");
        super.onStart();
        startActivitySync(mIntent);
    }
    //adb shell am instrument com.joyy.codecoverage/test.JacocoInstrumentation
}