package assignment1.keshav.com.assignment1.services;

import android.content.Context;

import assignment1.keshav.com.assignment1.persistance.modelmanagers.AccelerometerModelManager;
import assignment1.keshav.com.assignment1.persistance.DBHelper;
import assignment1.keshav.com.assignment1.persistance.modelmanagers.GravityModelManager;
import assignment1.keshav.com.assignment1.persistance.modelmanagers.GyroscopeModelManager;
import assignment1.keshav.com.assignment1.persistance.modelmanagers.LinearAccelModelManager;
import assignment1.keshav.com.assignment1.persistance.modelmanagers.MagneticFieldModelManager;
import assignment1.keshav.com.assignment1.persistance.modelmanagers.PressureModelManager;
import assignment1.keshav.com.assignment1.persistance.modelmanagers.ProximityModelManager;
import assignment1.keshav.com.assignment1.persistance.modelmanagers.RotationModelManager;
import assignment1.keshav.com.assignment1.persistance.modelmanagers.TemperatureModelManager;

/**
 * Created by Keshav on 3/1/2015.
 */
public class DBService
{
    private static DBService instance;
    private DBHelper dbHelper;
    private AccelerometerModelManager amm;
    private GravityModelManager gravityDB;
    private GyroscopeModelManager gyroDB;
    private LinearAccelModelManager linearAccDB;
    private RotationModelManager rotationDB;
    private MagneticFieldModelManager magneticDB;
    private ProximityModelManager proximityDB;
    private PressureModelManager pressureDB;
    private TemperatureModelManager tempDB;

    /**
     * local constructor
     */
    private DBService (Context context)
    {
        dbHelper = new DBHelper(context);
        amm = new AccelerometerModelManager(dbHelper.getWritableDatabase());
        gravityDB = new GravityModelManager(dbHelper.getWritableDatabase());
        gyroDB = new GyroscopeModelManager(dbHelper.getWritableDatabase());
        linearAccDB = new LinearAccelModelManager(dbHelper.getWritableDatabase());
        rotationDB = new RotationModelManager(dbHelper.getWritableDatabase()) ;
        magneticDB = new MagneticFieldModelManager(dbHelper.getWritableDatabase());
        proximityDB = new ProximityModelManager(dbHelper.getWritableDatabase());
        pressureDB = new PressureModelManager(dbHelper.getWritableDatabase());
        tempDB = new TemperatureModelManager( dbHelper.getWritableDatabase());
    }

    /**
     * Singleton implementation
     * @return
     */
    public static DBService getInstance(Context context)
    {
        if (instance == null)
            instance = new DBService(context);
        return instance;
    }

    public AccelerometerModelManager getAmm() {
        return amm;
    }

    public GravityModelManager getGravityDB() {
        return gravityDB;
    }

    public GyroscopeModelManager getGyroDB() {
        return gyroDB;
    }

    public LinearAccelModelManager getLinearAccDB() {
        return linearAccDB;
    }

    public RotationModelManager getRotationDB() {
        return rotationDB;
    }

    public MagneticFieldModelManager getMagneticDB() {
        return magneticDB;
    }

    public ProximityModelManager getProximityDB() {
        return proximityDB;
    }

    public PressureModelManager getPressureDB() {
        return pressureDB;
    }

    public TemperatureModelManager getTempDB() {
        return tempDB;
    }
}
