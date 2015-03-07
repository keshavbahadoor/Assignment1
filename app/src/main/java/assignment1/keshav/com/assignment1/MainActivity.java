package assignment1.keshav.com.assignment1;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import assignment1.keshav.com.assignment1.services.DBService;
import assignment1.keshav.com.assignment1.services.LocationService;
import assignment1.keshav.com.assignment1.services.LogService;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener
{
    private ViewPager viewPager;
    private TabsPageAdapter adapter;
    private LocationService locationService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Service setup
        initializeServices();

        // Initialize views
        viewPager = (ViewPager) this.findViewById(R.id.pager);

        adapter = new TabsPageAdapter(this.getSupportFragmentManager(), this);

        viewPager.setAdapter(adapter);

        // Creates db service
        DBService.getInstance(this);

        LogService.log("Started");

    }

    /**
     * Initializes all services
     */
    private void initializeServices()
    {
        // init location service
        this.locationService = LocationService.getLocationManager(this);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft)
    {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft)
    {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft)
    {

    }
}
