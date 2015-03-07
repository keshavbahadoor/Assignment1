package assignment1.keshav.com.assignment1.persistance;


import java.util.concurrent.LinkedBlockingQueue;

import assignment1.keshav.com.assignment1.sensors.SensorEnum;
import assignment1.keshav.com.assignment1.services.DBService;
import assignment1.keshav.com.assignment1.services.LogService;

/**
 * Created by Keshav on 3/2/2015.
 */
public class DBWorkerThread implements Runnable
{
    private  SensorEnum sensorType;
    private LinkedBlockingQueue<float[]> sensorData;
    private DBService dbService;

    public DBWorkerThread(SensorEnum type, DBService dbService)
    {
        this.sensorType = type;
        this.dbService = dbService;
        this.sensorData = new LinkedBlockingQueue<float[]>();
    }

    /**
     * Add data to queue
     * @param values
     */
    public void addDataToProcess(float[] values)
    {
        if (sensorData.size() < sensorData.remainingCapacity())
        {
            try
            {
                this.sensorData.put(values);
            }
            catch (Exception ex)
            {
                LogService.log("Error adding queue: " + ex.getMessage());
            }
            LogService.log("Added to queue. Size: " + sensorData.size());
        }
    }

    /**
     * Processes queue of data
     */
    @Override
    public void run()
    {
        // Moves the current Thread into the background
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

        while (sensorData.size() > 0)
        {
            try
            {
                float[] values = sensorData.take();
                storeData(values);
            }
            catch (Exception ex)
            {
                LogService.log("Error in queue: " + ex.getMessage());
            }
        }
    }

    /**
     * Store data to database
     * @param values
     */
    private void storeData(float[] values)
    {
        switch (this.sensorType)
        {
            case ACCELEROMETER: dbService.getAmm().saveEntry(values);
                break;

            case MAGNETIC_FIELD: dbService.getMagneticDB().saveEntry(values);
                break;

            case GRAVITY: dbService.getGravityDB().saveEntry(values);
                break;

            case PROXIMITY: dbService.getProximityDB().saveEntry(values);
                break;

            case PRESSURE: dbService.getPressureDB().saveEntry(values);
                break;

            case TEMPERATURE: dbService.getTempDB().saveEntry(values);
                break;

            case LINEAR_ACCELERATION: dbService.getLinearAccDB().saveEntry(values);
                break;

            case ROTATION: dbService.getRotationDB().saveEntry(values);
                break;

            case GYROSCOPE: dbService.getGyroDB().saveEntry(values);
                break;
        }
        LogService.log("Added to db!");
    }
}
