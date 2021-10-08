package at.ac.htlinn.androidexamples;

import java.util.ArrayList;
import java.util.List;

import at.ac.htlinn.androidexamples.concurrent.ConcurrentActivity;
import at.ac.htlinn.androidexamples.concurrent.asynctask.SimpleAsyncTaskActivity;
import at.ac.htlinn.androidexamples.googlemaps.SimpleMapsActivity;
import at.ac.htlinn.androidexamples.location.SimpleLocationActivity;
import at.ac.htlinn.androidexamples.sensor.LightSensorActivity;
import at.ac.htlinn.androidexamples.sensor.OrientationSensorBallActivity;
import at.ac.htlinn.androidexamples.sensor.ProximitySensorActivity;
import at.ac.htlinn.androidexamples.sensor.SensorActivity;
import at.ac.htlinn.androidexamples.sensor.StepCounterActivity;
import at.ac.htlinn.androidexamples.simplecalculator.SimpleCalculatorActivity;
import at.ac.htlinn.androidexamples.table.TableInViewActivity;
import at.ac.htlinn.androidexamples.ttt.TicTacToe;

public class ActivityEntry {
    private String name;

    public ActivityEntry(String name, Class activity) {
        this.name = name;
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public Class getActivity() {
        return activity;
    }

    private Class activity;

    @Override
    public String toString() {
        return name;
    }

    /**
     * All Demos listed on the main page
     */
    public static List<ActivityEntry> all()
    {
        ArrayList<ActivityEntry> all = new ArrayList<ActivityEntry>();
        all.add(new ActivityEntry("...please select the demo...", null));
        all.add(new ActivityEntry("Simple Calculator", SimpleCalculatorActivity.class));
        all.add(new ActivityEntry("TTT - TableView Demo", TicTacToe.class));
        all.add(new ActivityEntry("Generate a table in Activity", TableInViewActivity.class));
        all.add(new ActivityEntry("List all Sensors", SensorActivity.class));
        all.add(new ActivityEntry("Google Maps", SimpleMapsActivity.class));
        all.add(new ActivityEntry("Location Service", SimpleLocationActivity.class));
        all.add(new ActivityEntry("Concurrent Examples", ConcurrentActivity.class));
        return all;
    }

    /**
     * All demos listed on the Sensor page
     */
    public static List<ActivityEntry> allSensor()
    {
        ArrayList<ActivityEntry> all = new ArrayList<ActivityEntry>();
        all.add(new ActivityEntry("...please select the demo...", null));
        all.add(new ActivityEntry("Orientation Sensor: Ball game", OrientationSensorBallActivity.class));
        all.add(new ActivityEntry("Step Counter", StepCounterActivity.class));
        all.add(new ActivityEntry("Proximity Sensor", ProximitySensorActivity.class));
        all.add(new ActivityEntry("Light Sensor", LightSensorActivity.class));

        return all;
    }

    /**
     * All demos listed on the Sensor page
     */
    public static List<ActivityEntry> allConcurrent()
    {
        ArrayList<ActivityEntry> all = new ArrayList<ActivityEntry>();
        all.add(new ActivityEntry("...please select the demo...", null));
        all.add(new ActivityEntry("Simple calls using AsyncTask", SimpleAsyncTaskActivity.class));
        return all;
    }
}
