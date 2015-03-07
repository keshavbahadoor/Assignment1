package assignment1.keshav.com.assignment1.sensors.sensorimpli;

import android.hardware.Sensor;

import assignment1.keshav.com.assignment1.sensors.SensorEnum;
import assignment1.keshav.com.assignment1.sensors.SensorTemplateFragment;

/**
 * Created by Keshav on 3/1/2015.
 */
public class AccelerometerSensor extends SensorTemplateFragment
{
    public AccelerometerSensor()
    {
        super();
        this.sensorName = "Accelerometer";
        this.sensorType = Sensor.TYPE_ACCELEROMETER;
        this.sensorEnum = SensorEnum.ACCELEROMETER;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        // Set chart to accelerometer data
        this.lineChart.setData(this.dbService.getAmm().retrieveChartData());
        this.lineChart.invalidate();
    }

    @Override
    public void hook_onSensorChanged(float[] values)
    {

    }

    @Override
    public void hook_sensorPersistence(float[] values)
    {
        this.dbWorkerThread.addDataToProcess(values);
        this.dbWorkerThread.run();
    }
}
