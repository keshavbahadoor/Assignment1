package assignment1.keshav.com.assignment1.fragments;

import android.content.Context;
import android.media.browse.MediaBrowser;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

import assignment1.keshav.com.assignment1.R;
import assignment1.keshav.com.assignment1.services.GoogleLocationService;
import assignment1.keshav.com.assignment1.services.LocationService;

/**
 * Created by Keshav on 3/1/2015.
 */
public class MapFragment extends Fragment implements CompoundButton.OnCheckedChangeListener
{
    private LocationService locationService;
    private GoogleLocationService gLocationService;
    private Context context;
    private ToggleButton mapToggle;
    private TextView t;
    private GoogleMap gMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        this.context = v.getContext();

        // get location service
        this.locationService = LocationService.getLocationManager(context);

        // get google play location service
        this.gLocationService = GoogleLocationService.getInstance(context);

        // request google services API
        this.gLocationService.buildGoogleApiClient();


        t = (TextView) v.findViewById(R.id.map_text);

        // Set up toggle button
        this.mapToggle = (ToggleButton) v.findViewById(R.id.map_toggle);
        this.mapToggle.setOnCheckedChangeListener(this);

//        this.gMap = ((SupportMapFragment) this.getFragmentManager().findFragmentById(R.id.map)).getMap();
//        if (gMap == null){
//            Toast.makeText(v.getContext(), "Unable to find Map Object", Toast.LENGTH_SHORT).show();
//        }


        return v;
    }

    /**
     * google services needs to connect during on start event
     */
    @Override
    public void onStart()
    {
        super.onStart();
        this.gLocationService.connect();
    }

    /**
     * Toggle button listener
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        if (isChecked)
        {
            t.setText(
                    "\nGPS: " + locationService.isGPSEnabled() +
                            "\nNetwork: " + locationService.isNetworkEnabled() +
                            "\nService: " + locationService.isLocationServiceAvailable() +
                            "\n\nLongitude: " + locationService.getLongitude() +
                            "\nLatitude: " + locationService.getLatitude() +
                            "\nGoogle Longitude: " + gLocationService.getLongitude() +
                            "\nGoogle Latitude: " + gLocationService.getLatitude()

            );
        }
        else
        {
            t.setText("Off");
        }
    }
}
