package assignment1.keshav.com.assignment1.sensors.sensorimpli;

import android.hardware.Sensor;

import assignment1.keshav.com.assignment1.sensors.SensorEnum;
import assignment1.keshav.com.assignment1.sensors.SensorTemplateFragment;

/**
 * Created by Keshav on 3/2/2015.
 */
public class MagneticFieldSensor   extends SensorTemplateFragment
{
    public MagneticFieldSensor()
    {
        super();
        this.sensorName = "Magnetic Field";
        this.sensorEnum = SensorEnum.MAGNETIC_FIELD;
        this.sensorType = Sensor.TYPE_MAGNETIC_FIELD;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        // Set chart to accelerometer data
        this.lineChart.setData(this.dbService.getMagneticDB().retrieveChartData());
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
