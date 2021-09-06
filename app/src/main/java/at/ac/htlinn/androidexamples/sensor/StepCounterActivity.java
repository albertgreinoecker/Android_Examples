package at.ac.htlinn.androidexamples.sensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);


        stepCounter = sm.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        sm.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        TextView stepsText = findViewById(R.id.steps_text);
        stepsText.setText(String.format("Number of steps: %d (%d)", ++step, sensorEvent.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}