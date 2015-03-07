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
public class GravityModelManager extends SensorModelManager
{
    public static abstract class GravityEntry implements BaseColumns {
        public static final String TABLE_NAME = "gravity";
        public static final String AXIS_X = "xaxis";
        public static final String AXIS_Y = "yaxis";
        public static final String AXIS_Z = "zaxis";
        public static final String TIME_TAKEN = "time_taken";
    }

    public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + GravityEntry.TABLE_NAME;

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + GravityEntry.TABLE_NAME + " (" +
                    GravityEntry._ID + INT_TYPE + " PRIMARY KEY, " +
                    GravityEntry.AXIS_X + REAL_TYPE + COMMA_SEP +
                    GravityEntry.AXIS_Y + REAL_TYPE + COMMA_SEP +
                    GravityEntry.AXIS_Z + REAL_TYPE + COMMA_SEP +
                    GravityEntry.TIME_TAKEN + TIMESTAMP + ") ;";


    public GravityModelManager(SQLiteDatabase db)
    {
        super(db);
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public boolean saveEntry(float[] values) {
        ContentValues cv = new ContentValues();
        cv.put(GravityEntry.AXIS_X, values[0]);
        cv.put(GravityEntry.AXIS_Y, values[1]);
        cv.put(GravityEntry.AXIS_Z, values[2]);
        cv.put(GravityEntry.TIME_TAKEN, dFormat.format(new Date()));

        long res = this.db.insert(GravityEntry.TABLE_NAME, null, cv);
        return (res != -1);
    }

    @Override
    public List retrieveData() {
        return null;
    }

    @Override
    public LineData retrieveChartData() {
        int count = 0;
        ArrayList<Entry> xEntries = new ArrayList<Entry>();
        ArrayList<Entry> yEntries = new ArrayList<Entry>();
        ArrayList<Entry> zEntries = new ArrayList<Entry>();
        ArrayList<LineDataSet> dataset = new ArrayList<LineDataSet>();
        ArrayList<String> xLabels = new ArrayList<String>();


        StringBuilder stb = new StringBuilder();
        stb.append("SELECT * FROM ")
                .append(GravityEntry.TABLE_NAME)
                .append(" ORDER BY " + GravityEntry.TIME_TAKEN + " DESC ")
                .append(LIMIT);

        Cursor res = db.rawQuery(stb.toString(), null);
        while(res.moveToNext())
        {
            // Add X axis labels
            //xLabels.add(res.getString(res.getColumnIndex(AccelerometerEntry.TABLE_NAME)));

            xLabels.add("X: " + count);

            // Add entries
            xEntries.add(new Entry(res.getFloat(res.getColumnIndex(GravityEntry.AXIS_X)), count));
            yEntries.add(new Entry(res.getFloat(res.getColumnIndex(GravityEntry.AXIS_Y)), count));
            zEntries.add(new Entry(res.getFloat(res.getColumnIndex(GravityEntry.AXIS_Z)), count));

            count++;
        }
        LineDataSet setX = new LineDataSet(xEntries, "X Values");
        LineDataSet setY = new LineDataSet(yEntries, "Y Values");
        LineDataSet setZ = new LineDataSet(zEntries, "Z Values");

        setX.setColor(ColorTemplate.JOYFUL_COLORS[0]);
        setY.setColor(ColorTemplate.JOYFUL_COLORS[1]);
        setZ.setColor(ColorTemplate.JOYFUL_COLORS[3]);

        dataset.add(setX);
        dataset.add(setY);
        dataset.add(setZ);

        return new LineData(xLabels, dataset);
    }

    @Override
    public boolean deleteEntry(int id) {
        return false;
    }
}
