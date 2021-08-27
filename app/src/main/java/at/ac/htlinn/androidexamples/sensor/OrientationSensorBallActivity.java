package at.ac.htlinn.androidexamples.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;

import at.ac.htlinn.androidexamples.R;

/**
 * This demo shows:
 * * How the Orientation sensor works (values calculated by ACCELEROMETER and MAGNETIC_FIELD
 * * no need for XML view, we use a custom class for displaying the ball
 * * how a touchlistener works
 */
public class OrientationSensorBallActivity extends AppCompatActivity implements SensorEventListener, View.OnTouchListener {

    private BallGameView mv;
    private SensorManager sm;
    private boolean on = false;

    Sensor accelerometer;
    Sensor magnetometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mv = new BallGameView(this); //this is the view where the game is displayed
        setContentView(mv);
        mv.setOnTouchListener(this); // react on touching on the display

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //Deprecated, use TYPE_ACCELEROMETER and TYPE_MAGNETIC_FIELD instead
        //Sensor acc = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        //sm.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    /**
     * Called when activity is restartet
     */
    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        sm.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);

    }

    /**
     * Called when activity is paused. Listeners have to be unregistered
     */
    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    float[] gravity = null;
    float[] geomagnetic = null;

    /**
     * Called when a sensor value is changed.
     *
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            gravity = sensorEvent.values;
        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            geomagnetic = sensorEvent.values;
        if (gravity != null && geomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, gravity, geomagnetic);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                float azimut = orientation[0]; // orientation contains: azimut, pitch and roll
                float pitch = Math.round(Math.toDegrees(orientation[1]));
                float roll = Math.round(Math.toDegrees(orientation[2]));
                mv.setCx((float) (mv.getCx() + roll * 0.5));
                mv.setCy((float) (mv.getCy() - pitch * 0.5));
                mv.invalidate();
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    /**
     * Move the actual point to the touch position
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        mv.setCx(motionEvent.getX());
        mv.setCy(motionEvent.getY());
        mv.invalidate();
        on = true;
        return true;
    }
}