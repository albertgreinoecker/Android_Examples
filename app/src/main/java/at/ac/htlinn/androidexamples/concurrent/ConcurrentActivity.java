package at.ac.htlinn.androidexamples.concurrent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import at.ac.htlinn.androidexamples.ActivityEntry;
import at.ac.htlinn.androidexamples.R;

public class ConcurrentActivity extends AppCompatActivity   implements AdapterView.OnItemSelectedListener {

    Spinner activitySelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concurrent);

        activitySelect = findViewById(R.id.concurrent_select);
        List<ActivityEntry> entries = ActivityEntry.allConcurrent();
        ArrayAdapter entryAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, entries);
        activitySelect.setAdapter(entryAdapter);
        activitySelect.setOnItemSelectedListener(this);
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