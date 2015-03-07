package assignment1.keshav.com.assignment1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import assignment1.keshav.com.assignment1.sensors.SensorEnum;
import assignment1.keshav.com.assignment1.services.DBService;
import assignment1.keshav.com.assignment1.services.LogService;

/**
 * Created by Keshav on 3/1/2015.
 */
public class CardViewHolder extends RecyclerView.ViewHolder
{
    protected View view;
    protected TextView textView;
    protected LineChart lineChart;
    protected String sensorType;
    protected ButtonRectangle moreButton;
    protected Button closeButton;
    protected Context context;


    public CardViewHolder(View v)
    {
        super(v);
        this.view = v;
        this.context = v.getContext();
        this.textView = (TextView) v.findViewById(R.id.tV_card);
        this.lineChart = (LineChart) v.findViewById(R.id.line_chart);
        this.moreButton = (ButtonRectangle) v.findViewById(R.id.card_more);
    }

    public void setData(SensorEnum sensor)
    {
        switch (sensor)
        {
            case ACCELEROMETER:
                this.textView.setText("Accelerometer");
                this.sensorType = "Accelerometer";
                this.lineChart.setData(DBService.getInstance(context).getAmm().retrieveChartData());
                break;

            case MAGNETIC_FIELD:
                this.textView.setText("Magnetic Field");
                this.sensorType = "Magnetic Field";
                this.lineChart.setData(DBService.getInstance(context).getMagneticDB().retrieveChartData());
                break;

            case GRAVITY:
                this.textView.setText("Gravity");
                this.sensorType = "Gravity";
                this.lineChart.setData(DBService.getInstance(context).getGravityDB().retrieveChartData());
                break;

            case PROXIMITY:
                this.textView.setText("Proximity");
                this.sensorType = "Proximity";
                this.lineChart.setData(DBService.getInstance(context).getProximityDB().retrieveChartData());
                break;

            case PRESSURE:
                this.textView.setText("Pressure");
                this.sensorType = "Pressure";
                this.lineChart.setData(DBService.getInstance(context).getPressureDB().retrieveChartData());
                break;

            case TEMPERATURE:
                this.textView.setText("Temperature");
                this.sensorType = "Temperature";
                this.lineChart.setData(DBService.getInstance(context).getTempDB().retrieveChartData());
                break;

            case LINEAR_ACCELERATION:
                this.textView.setText("Linear Acceleration");
                this.sensorType = "Linear Acceleration";
                this.lineChart.setData(DBService.getInstance(context).getLinearAccDB().retrieveChartData());
                break;

            case ROTATION:
                this.textView.setText("Rotation");
                this.sensorType = "Rotation";
                this.lineChart.setData(DBService.getInstance(context).getRotationDB().retrieveChartData());
                break;

            case GYROSCOPE:
                this.textView.setText("Gyroscope");
                this.sensorType = "Gyroscope";
                this.lineChart.setData(DBService.getInstance(context).getGyroDB().retrieveChartData());
                break;
        }


        // Refresh chart
        lineChart.invalidate();

    }

    public String getSensorType()
    {
        return sensorType;
    }


}
