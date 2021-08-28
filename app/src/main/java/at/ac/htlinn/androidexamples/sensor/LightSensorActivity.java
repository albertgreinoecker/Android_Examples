package at.ac.htlinn.androidexamples.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import at.ac.htlinn.androidexamples.R;
/**
 * Implementation of a simple light sensor
 */
public class LightSensorActivity extends AppCompatActivity  implements SensorEventListener  {

    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor light = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sm.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        TextView lightText = findViewById(R.id.light_text);
        lightText.setText(String.format("Light Value: %.2f", sensorEvent.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}