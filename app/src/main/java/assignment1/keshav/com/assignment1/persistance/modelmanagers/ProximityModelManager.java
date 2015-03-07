package assignment1.keshav.com.assignment1.persistance.modelmanagers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import assignment1.keshav.com.assignment1.persistance.SensorModelManager;

/**
 * Created by Keshav on 3/2/2015.
 */
public class ProximityModelManager extends SensorModelManager
{
    public static abstract class ProximityEntry implements BaseColumns {
        public static final String TABLE_NAME = "proximity";
        public static final String AXIS_X = "xaxis";
        public static final String TIME_TAKEN = "time_taken";
    }

    public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + ProximityEntry.TABLE_NAME;

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + ProximityEntry.TABLE_NAME + " (" +
                    ProximityEntry._ID + INT_TYPE + " PRIMARY KEY, " +
                    ProximityEntry.AXIS_X + REAL_TYPE + COMMA_SEP +
                    ProximityEntry.TIME_TAKEN + TIMESTAMP + ") ;";

    public ProximityModelManager(SQLiteDatabase db) {
        super(db);
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public boolean saveEntry(float[] values) {
        ContentValues cv = new ContentValues();
        cv.put(ProximityEntry.AXIS_X, values[0]);
        cv.put(ProximityEntry.TIME_TAKEN, dFormat.format(new Date()));

        long res = this.db.insert(ProximityEntry.TABLE_NAME, null, cv);
        return (res != -1);
    }

    @Override
    public LineData retrieveChartData() {
        int count = 0;
        ArrayList<Entry> xEntries = new ArrayList<Entry>();
        ArrayList<LineDataSet> dataset = new ArrayList<LineDataSet>();
        ArrayList<String> xLabels = new ArrayList<String>();


        StringBuilder stb = new StringBuilder();
        stb.append("SELECT * FROM ")
                .append(ProximityEntry.TABLE_NAME)
                .append(" ORDER BY " + ProximityEntry.TIME_TAKEN + " DESC ")
                .append(LIMIT);

        Cursor res = db.rawQuery(stb.toString(), null);
        while(res.moveToNext())
        {
            // Add X axis labels
            //xLabels.add(res.getString(res.getColumnIndex(AccelerometerEntry.TABLE_NAME)));

            xLabels.add("X: " + count);

            // Add entries
            xEntries.add(new Entry(res.getFloat(res.getColumnIndex(ProximityEntry.AXIS_X)), count));

            count++;
        }
        LineDataSet setX = new LineDataSet(xEntries, "X Values");

        setX.setColor(ColorTemplate.JOYFUL_COLORS[0]);

        dataset.add(setX);

        return new LineData(xLabels, dataset);
    }

    @Override
    public List retrieveData() {
        return null;
    }

    @Override
    public boolean deleteEntry(int id) {
        return false;
    }
}
