package assignment1.keshav.com.assignment1;

import android.support.v4.app.Fragment;

import assignment1.keshav.com.assignment1.sensors.sensorimpli.*;

/**
 * Created by Keshav on 3/2/2015.
 */
public class SensorFragmentFactory
{
    private static SensorFragmentFactory instance = null;

    private SensorFragmentFactory(){

    }
    public static SensorFragmentFactory getInstance(){
        if (instance == null)instance = new SensorFragmentFactory();
        return instance;
    }

    public Fragment getSensorFragment(String sensorType){
        Fragment tempFrag = null;
        switch (sensorType){
            case "Accelerometer":
                tempFrag = new AccelerometerSensor();
                break;
            case "Rotation":
                tempFrag = new RotationSensor();
                break;
            case "Gravity":
                tempFrag = new GravitySensor();
                break;
            case "Gyroscope":
                tempFrag = new GyroscopeSensor();
                break;
            case "Linear Acceleration":
                tempFrag = new LinearAccelerationSensor();
                break;
            case "Magnetic Field":
                tempFrag = new MagneticFieldSensor();
                break;
            case "Pressure":
                tempFrag = new PressureSensor();
                break;
            case "Proximity":
                tempFrag = new ProximitySensor();
                break;
            case "Temperature":
                tempFrag = new TemperatureSensor();
                break;
        }
        return tempFrag;
    }

}
