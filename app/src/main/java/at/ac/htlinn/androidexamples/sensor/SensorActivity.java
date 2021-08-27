package at.ac.htlinn.androidexamples.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import at.ac.htlinn.androidexamples.ActivityEntry;
import at.ac.htlinn.androidexamples.R;

/**
 * This demo shows...
 * * How to list all Sensors available
 * * How to generate logging information
 */
public class SensorActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    Spinner activitySelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        listAllSensors();
        activitySelect = findViewById(R.id.sensor_demo_select);
        List<ActivityEntry> entries = ActivityEntry.allSensor();
        ArrayAdapter entryAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, entries);
        activitySelect.setAdapter(entryAdapter);
        activitySelect.setOnItemSelectedListener(this);
    }

    private void listAllSensors()
    {
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);
        TextView allSensors = findViewById(R.id.all_sensors);

        for (Sensor s : sensors)
        {
            String name = s.getName();
            String type = s.getStringType();
            String vendor = s.getVendor();
            String sensInfo =  String.format("%s: %s, %s", type, name, vendor);
            Log.d("SENSOR",sensInfo);

            allSensors.append(sensInfo + "\n");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ActivityEntry entry = (ActivityEntry)activitySelect.getSelectedItem();
        if (entry.getActivity() != null) {
            Intent intent = new Intent(this, entry.getActivity());
            startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}