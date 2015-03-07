package assignment1.keshav.com.assignment1.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.opengl.Visibility;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;

import java.util.ArrayList;

import assignment1.keshav.com.assignment1.R;
import assignment1.keshav.com.assignment1.RecyclerViewAdapter;
import assignment1.keshav.com.assignment1.sensors.SensorEnum;

/**
 * Created by Keshav on 3/1/2015.
 */
public class MainFragment extends Fragment implements View.OnClickListener, DialogInterface.OnClickListener
{
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AlertDialog sensorSelect;
    private TextView msg;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // inflate view
        View fragment = inflater.inflate(R.layout.fragment_main, container,false);

        recyclerView = (RecyclerView) fragment.findViewById(R.id.my_recycler_view);
        msg = (TextView) fragment.findViewById(R.id.no_sensors_added);

        // Increases performances if size is not supposed to change
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(fragment.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // set adapter
        adapter = new RecyclerViewAdapter( );
        recyclerView.setAdapter(adapter);

        // Set button click listener
        ( (ButtonFloat) fragment.findViewById(R.id.Btn_add_card)).setOnClickListener(this);

        // Build dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getContext());
        builder.setTitle("Select one of the following sensors.");
        builder.setSingleChoiceItems(R.array.sensor_types, -1, this);
        this.sensorSelect = builder.create();

        return fragment;
    }



    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.Btn_add_card)
        {
            //addCard();
            sensorSelect.show();
        }
    }


    @Override
    public void onClick(DialogInterface dialog, int selected)
    {
        switch (selected)
        {
            case 0:  this.adapter.addItem(SensorEnum.ACCELEROMETER);
                break;
            case 1:  this.adapter.addItem(SensorEnum.MAGNETIC_FIELD);
                break;
            case 2:  this.adapter.addItem(SensorEnum.GRAVITY);
                break;
            case 3:  this.adapter.addItem(SensorEnum.PROXIMITY);
                break;
            case 4:  this.adapter.addItem(SensorEnum.PRESSURE);
                break;
            case 5:  this.adapter.addItem(SensorEnum.TEMPERATURE);
                break;
            case 6:  this.adapter.addItem(SensorEnum.LINEAR_ACCELERATION);
                break;
            case 7:  this.adapter.addItem(SensorEnum.ROTATION);
                break;
            case 8:  this.adapter.addItem(SensorEnum.GYROSCOPE);
                break;


        }
        this.msg.setVisibility(View.GONE);
        sensorSelect.hide();
    }
}
