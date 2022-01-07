package test;

import android.os.Environment;
import android.util.Log;

import com.joyy.codecoverage.MainActivity;

import java.io.FileOutputStream;
import java.io.OutputStream;


public class InstrumentedActivity extends MainActivity {
    public static String TAG = "[InstrumentedActivity]";

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
        generateCoverageReport();
    }

    private void generateCoverageReport() {
//        String DEFAULT_COVERAGE_FILE_PATH = getFilesDir().getPath() + "/coverage.ec";
        String DEFAULT_COVERAGE_FILE_PATH = Environment.getExternalStorageDirectory().getPath() + "/coverage.ec";
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