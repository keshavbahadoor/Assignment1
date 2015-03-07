package assignment1.keshav.com.assignment1.services;

import android.util.Log;

/**
 * Created by Keshav on 3/1/2015.
 */
public class LogService
{
    public final static String LOG_TAG = "My Log";
    public final static Boolean DEBUG = true;

    public static void log(String message)
    {
        if (DEBUG)
            Log.v(LOG_TAG, message);
    }
}
