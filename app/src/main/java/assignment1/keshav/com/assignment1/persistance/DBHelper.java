package assignment1.keshav.com.assignment1.persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import assignment1.keshav.com.assignment1.persistance.modelmanagers.AccelerometerModelManager;
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
public class DBHelper  extends SQLiteOpenHelper
{
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "sensors.db";

    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(AccelerometerModelManager.CREATE_TABLE);
        db.execSQL(GravityModelManager.CREATE_TABLE);
        db.execSQL(GyroscopeModelManager.CREATE_TABLE);
        db.execSQL(LinearAccelModelManager.CREATE_TABLE);
        db.execSQL(MagneticFieldModelManager.CREATE_TABLE);
        db.execSQL(RotationModelManager.CREATE_TABLE);
        db.execSQL(ProximityModelManager.CREATE_TABLE);
        db.execSQL(PressureModelManager.CREATE_TABLE);
        db.execSQL(TemperatureModelManager.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //Handle Update Logic
        //Modify tables, add data etc
    }
}
