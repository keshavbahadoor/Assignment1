package assignment1.keshav.com.assignment1.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.gc.materialdesign.views.Slider;
import com.github.mikephil.charting.charts.LineChart;

import assignment1.keshav.com.assignment1.R;
import assignment1.keshav.com.assignment1.persistance.DBWorkerThread;
import assignment1.keshav.com.assignment1.services.DBService;

/**
 * Uses template design pattern
 */
public abstract class SensorTemplateFragment extends Fragment implements SensorEventListener
{
    /**+
     * Used to calculate when data should be stored
     */
    protected float SENSOR_SENSITIVITY = 0.5F;
    protected float DB_INTERVAL = 2F;
    protected long prevTimeStamp = 0L;
    protected long prevDBTimeStamp = 0L;

    protected SensorEnum sensorEnum;
    protected String sensorName;
    protected String sensorDescription;
    protected boolean registered = false;
    protected SensorManager sensorManager;
    protected Sensor sensor;
    protected int sensorType;
    protected ToggleButton toggleButton;
    protected Button refreshButton;
    protected DBService dbService;
    protected DBWorkerThread dbWorkerThread;
    protected LineChart lineChart;
    protected Slider sensitivitySlider;

    /**
     * Constructor
     */
    public SensorTemplateFragment( )
    {
        this.sensorName = "Sensor";
        this.sensorDescription = "Description of the Sensor";
        this.sensorEnum = SensorEnum.DEFAULT;
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.sensorManager = (SensorManager) this.getActivity().getSystemService(Context.SENSOR_SERVICE);
        this.sensor = sensorManager.getDefaultSensor(this.sensorType);
        this.dbService = DBService.getInstance(this.getActivity());
        this.dbWorkerThread = new DBWorkerThread(sensorEnum, dbService);
        this.dbWorkerThread.run();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_sensor_detail_container, container, false);
        ((TextView) rootView.findViewById(R.id.sensor_name)).setText(this.sensorName);
        this.refreshButton = (Button) rootView.findViewById(R.id.sensor_start);
        this.lineChart = (LineChart) rootView.findViewById(R.id.line_chart_detail);
        this.sensitivitySlider = (Slider) rootView.findViewById(R.id.sensor_sensitivity);

        // set listener for refresh button
        this.refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineChart.invalidate();
            }
        });

        // Set listener for toggle
        toggleButton = (ToggleButton) rootView.findViewById(R.id.sensor_toggle_button);
        toggleButton.setChecked(false);
        toggleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sensorToggle();
            }
        });

        // Set listener for slider / seekbar
        sensitivitySlider.setOnValueChangedListener(new Slider.OnValueChangedListener() {
            @Override
            public void onValueChanged(int i) {
                SENSOR_SENSITIVITY = i/10;
            }
        });

        return rootView;
    }

    public void sensorToggle()
    {
        toggleButton.setChecked(! toggleButton.isChecked());
        if (toggleButton.isChecked())
        {
            sensorManager.unregisterListener(this);
            changeRegisteredState(false);
        }
        else
        {
            sensorStart();
        }
    }

    public void sensorStart()
    {
        if (sensor != null)
        {//Register Listener
            toggleButton.setChecked(true);
            sensorManager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_NORMAL);
            changeRegisteredState(true);
            Toast.makeText(getActivity(), this.sensorName + " Found", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "No "+ this.sensorName +" Found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        if ( (sensorEvent.timestamp - prevTimeStamp)/1000000000.0 > SENSOR_SENSITIVITY)
        {
            prevTimeStamp = sensorEvent.timestamp;
            ((TextView) getActivity().findViewById(R.id.sensor_value_x)).setText("" + sensorEvent.values[0]);
            ((TextView) getActivity().findViewById(R.id.sensor_value_y)).setText("" + sensorEvent.values[1]);
            ((TextView) getActivity().findViewById(R.id.sensor_value_z)).setText("" + sensorEvent.values[2]);

            // HOOK
            this.hook_onSensorChanged(sensorEvent.values);
        }

        if ( (sensorEvent.timestamp - prevDBTimeStamp)/1000000000.0 > DB_INTERVAL)
        {
            prevDBTimeStamp = sensorEvent.timestamp;

            // HOOK
            this.hook_sensorPersistence(sensorEvent.values);
        }
    }

    @Override
    public void onStop()
    {
        super.onStop();
        toggleButton.setChecked(false);
        sensorManager.unregisterListener(this);
        changeRegisteredState(false);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    /**
     * registers sensor
     * @param state
     */
    public void changeRegisteredState(boolean state)
    {
        registered = state;
        this.toggleButton.setChecked(registered);
    }

    /**
     * HOOK methods
     */
    public abstract void hook_onSensorChanged(float[] values);
    public abstract void hook_sensorPersistence(float[] values);


}
