package at.ac.htlinn.androidexamples;

import java.util.ArrayList;
import java.util.List;

import at.ac.htlinn.androidexamples.simplecalculator.SimpleCalculatorActivity;

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

    public static List<ActivityEntry> all()
    {
        ArrayList<ActivityEntry> all = new ArrayList<ActivityEntry>();
        all.add(new ActivityEntry("Erstes Programm", SimpleCalculatorActivity.class));
        all.add(new ActivityEntry("Zweites Programm", SimpleCalculatorActivity.class));
        return all;
    }
}
