package assignment1.keshav.com.assignment1;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import assignment1.keshav.com.assignment1.sensors.sensorimpli.AccelerometerSensor;

/**
 * Created by Keshav on 3/1/2015.
 */
public class SensorDetailActivity extends FragmentActivity
{
    private TextView info;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.fragment_sensor_detail);

        // Get bundle data
        String type = "default";
        Bundle extras = this.getIntent().getExtras();
        type = extras.getString("sensortype");

        Fragment fragment = SensorFragmentFactory.getInstance().getSensorFragment(type);

        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.sensor_detail_container, fragment)
                .commit();

    }
}
