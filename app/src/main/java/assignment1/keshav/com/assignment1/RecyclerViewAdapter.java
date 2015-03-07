package assignment1.keshav.com.assignment1;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import assignment1.keshav.com.assignment1.listeners.CardOnClickListener;
import assignment1.keshav.com.assignment1.sensors.SensorEnum;
import assignment1.keshav.com.assignment1.services.LogService;

/**
 * Created by Keshav on 3/1/2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<CardViewHolder> implements View.OnClickListener
{
    private ArrayList<SensorEnum> data;

    /**
     * Constructor
     * @param data
     */
    public RecyclerViewAdapter( )
    {
        data = new ArrayList<SensorEnum>();
    }

    /**
     * Creates new views
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new CardViewHolder(v);
    }


    /**
     * Replaces the contents of a view (invoked by the layout manager)
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(CardViewHolder holder, int position)
    {
        holder.setData(this.data.get(position));
        holder.moreButton.setOnClickListener(this);

    }

    /**
     * Returns item count
     * @return
     */
    @Override
    public int getItemCount()
    {
        return data.size();
    }

    /**
     * Adds an item to the colletion
     * @param item
     */
    public void addItem(SensorEnum item)
    {
        this.data.add(item);
        this.notifyDataSetChanged();
    }

    /**
     * Removes item from the collection
     * @param item
     */
    public void removeItem(SensorEnum item)
    {
        this.data.remove(item);
        this.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v)
    {
        try
        {
            TextView tv = (TextView) ((LinearLayout) v.getParent().getParent()).getChildAt(0);
            LogService.log("Clicked! : " + tv.getText());
            Intent i = new Intent(v.getContext(), SensorDetailActivity.class);
            i.putExtra("sensortype", tv.getText());
            v.getContext().startActivity(i);

        }
        catch (Exception e)
        {
            LogService.log("Error getting child view click : " + e.getMessage());
        }


    }


}
