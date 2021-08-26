package at.ac.htlinn.androidexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner activitySelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activitySelect = findViewById(R.id.select_activity);

        List<ActivityEntry> entries = ActivityEntry.all();
        ArrayAdapter entryAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, entries);
        activitySelect.setAdapter(entryAdapter);
        activitySelect.setSelection(0,false); //Avoid selection during initialization
        activitySelect.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ActivityEntry entry = (ActivityEntry)activitySelect.getSelectedItem();
        Intent intent = new Intent(this, entry.getActivity());
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}