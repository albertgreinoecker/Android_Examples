package at.ac.htlinn.androidexamples.location;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

import at.ac.htlinn.androidexamples.R;

/**
 * do not forget to set permissions in AndroidManifest and also within the Location Settings of the Phone
 * Here, latitude, longitude and altitude are periodically fetched (for further processing)
 */
public class SimpleLocationActivity extends AppCompatActivity {

    @SuppressLint("MissingPermission")   //ignore the warning
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_location);

        //Settings how frequently updates should be fetched
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(5000); //this is the lowest rate at which update is called
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        //this callback is periodically called
        LocationCallback mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                TextView locationText = findViewById(R.id.location_text);
                Log.d("LOCATION", "calling callback");
                if (locationResult == null) {
                    Log.d("LOCATION", "locationResult is null");
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        String locationStr = String.format("lat: %.2f long: %.2f alt: %.2f", location.getLatitude(), location.getLongitude(), location.getAltitude());
                        locationText.setText(locationStr);
                    }
                }
            }
        };
        LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(locationRequest, mLocationCallback, Looper.getMainLooper());
    }
}