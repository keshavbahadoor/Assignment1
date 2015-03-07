package assignment1.keshav.com.assignment1.listeners;

import android.view.View;
import android.widget.Toast;

import assignment1.keshav.com.assignment1.services.LogService;

/**
 * Created by Keshav on 3/1/2015.
 */
public class CardOnClickListener implements View.OnClickListener
{
    public CardOnClickListener()
    {

    }

    @Override
    public void onClick(View v)
    {
        LogService.log("Card Clicked! " + v.getId());
    }
}
