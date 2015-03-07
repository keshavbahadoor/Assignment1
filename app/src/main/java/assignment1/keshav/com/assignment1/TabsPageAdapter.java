package assignment1.keshav.com.assignment1;

import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import assignment1.keshav.com.assignment1.fragments.MainFragment;
import assignment1.keshav.com.assignment1.fragments.MapFragment;

/**
 * Created by Keshav on 3/1/2015.
 */
public class TabsPageAdapter extends FragmentPagerAdapter
{
    private Context context;
    private String [] tabTitles;

    /**
     * Constructor
     * @param fm
     */
    public TabsPageAdapter(FragmentManager fm, Context context)
    {
        super(fm);

        this.context = context;

        // Create title from resource
        tabTitles = context.getResources().getStringArray(R.array.tab_items);
    }

    /**
     * Returns fragment item by position
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                return new MainFragment();
            case 1:
                return new MapFragment();
        }
        return null;
    }

    /**
     * Returns page tile from array
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position)
    {
        return this.tabTitles[position];
    }

    @Override
    public int getCount()
    {
        return 2;
    }
}
