package assignment1.keshav.com.assignment1.services;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Keshav on 3/1/2015.
 */
public class GoogleLocationService implements ConnectionCallbacks, OnConnectionFailedListener
{
    private static GoogleLocationService instance = null;

    /**
     * Provides the entry point to Google Play services.
     */
    protected GoogleApiClient googleApiClient;

    /**
     * Represents a geographical location.
     */
    protected Location lastLocation;

    protected Context context;

    /**
     * Constructor
     */
    private GoogleLocationService(Context context)
    {
        this.context = context;
    }

    /**
     * Singleton implementation
     * @param context
     * @return
     */
    public static GoogleLocationService getInstance(Context context)
    {
        if (instance == null)
            instance = new GoogleLocationService(context);
        return instance;
    }

    /**
     * Builds a GoogleApiClient. Uses the addApi() method to request the LocationServices API.
     */
    public synchronized void buildGoogleApiClient()
    {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    /**
     * connect services
     */
    public void connect()
    {
        googleApiClient.connect();
    }

    /**
     * Disconnect services
     */
    public void disconnect()
    {
        if (this.googleApiClient.isConnected())
            this.googleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle)
    {
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (lastLocation != null)
        {
            LogService.log("Google location service successful: " + lastLocation.getLongitude() + " " + lastLocation.getLatitude() );
        }
        else
        {
            LogService.log("Google Services: No connection detected");
        }
    }

    @Override
    public void onConnectionSuspended(int i)
    {
        LogService.log("Google services connection suspended");
        this.googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult)
    {
        LogService.log("Google services connection failed: " + connectionResult.getErrorCode());
    }

    public Location getLastLocation()
    {
        return lastLocation;
    }

    public double getLatitude()
    {
        if (lastLocation != null)
            return lastLocation.getLatitude();
        return 0.0;
    }

    public double getLongitude()
    {
        if (lastLocation != null)
            return lastLocation.getLongitude();
        return 0.0;
    }
}
