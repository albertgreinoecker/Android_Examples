package at.ac.htlinn.androidexamples.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import at.ac.htlinn.androidexamples.R;

/**
 * This demo shows...
 * * How to list all Sensors available
 * * How to generate logging information
 */
public class SensorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        listAllSensors();
    }

    private void listAllSensors()
    {
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);

        for (Sensor s : sensors)
        {
            String name = s.getName();
            String type = s.getStringType();
            String vendor = s.getVendor();
            String sensInfo =  String.format("%s: %s, %s", type, name, vendor);
            Log.d("SENSOR",sensInfo);
        }
    }
}