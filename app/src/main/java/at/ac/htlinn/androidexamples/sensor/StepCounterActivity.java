package at.ac.htlinn.androidexamples.sensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;

import at.ac.htlinn.androidexamples.R;

/**
 * Implementation of a simple step detector
 */
public class StepCounterActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sm;
    private Sensor stepCounter;
    private int step = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("STEP_COUNTER", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        stepCounter = sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sm.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_UI);
        Log.d("STEP_COUNTER", "onCreate Last");
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d("STEP_COUNTER", "onSensorChanged");
        TextView stepsText = findViewById(R.id.steps_text);
        stepsText.setText(String.format("Number of steps: %d (%.0f)", ++step, sensorEvent.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}