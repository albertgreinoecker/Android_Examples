package at.ac.htlinn.androidexamples.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import at.ac.htlinn.androidexamples.R;
/**
 * Implementation of a simple proximity sensor
 */
public class ProximitySensorActivity extends AppCompatActivity implements SensorEventListener  {

    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_sensor);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor prox = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sm.registerListener(this, prox, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        TextView proxText = findViewById(R.id.prox_text);
        proxText.setText(String.format("Proximity Value: %.2f", sensorEvent.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}