package assignment1.keshav.com.assignment1.sensors.sensorimpli;

import android.hardware.Sensor;

import assignment1.keshav.com.assignment1.sensors.SensorEnum;
import assignment1.keshav.com.assignment1.sensors.SensorTemplateFragment;

/**
 * Created by Keshav on 3/2/2015.
 */
public class ProximitySensor   extends SensorTemplateFragment
{
    public ProximitySensor()
    {
        super();
        this.sensorName = "Proximity";
        this.sensorEnum = SensorEnum.PROXIMITY;
        this.sensorType = Sensor.TYPE_PROXIMITY;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        // Set chart to accelerometer data
        this.lineChart.setData(this.dbService.getProximityDB().retrieveChartData());
        this.lineChart.invalidate();
    }

    @Override
    public void hook_onSensorChanged(float[] values) {

    }

    @Override
    public void hook_sensorPersistence(float[] values)
    {
        this.dbWorkerThread.addDataToProcess(values);
        this.dbWorkerThread.run();
    }
}
