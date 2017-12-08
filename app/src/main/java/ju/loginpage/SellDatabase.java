package ju.loginpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.content.ContentValues.TAG;

/**
 * Created by jumegol on 12/8/2017.
 */

class SellDatabase {
    static final String ROWID = "id";
    static final String SMONEYUNIT1 = "smoneyUnit1";
    static final String SMONEYUNIT2 = "smoneyUnit2";
    static final String SCHANGERATE = "schangeRate";
    static final String SCURRENTDATE = "scurrentDate";
    static final String SCURRENTTIME = "scurrentTime";
    static final String STAG = "DBSpinner";

    static final String DBName = "myDB";
    static final String STBNAME = "smyTb";
    static final int DBVERSION = '1';

    static final String SCREATETB = "CREATE TABLE smyTb(id INTEGER PRIMARY KEY AUTOINCREMENT, smoneyUnit1 TEXT NOT NULL, smoneyUnit2 TEXT NOT NULL," +
            " schangeRate TEXT NOT NULL, scurrentDate TEXT NOT NULL, scurrentTime TEXT NOT NULL);";

    final Context sc2;
    SQLiteDatabase sdb2;
    SellDatabase.DBHelper shelper2;
    public SellDatabase(Context sctx2){
        this.sc2 = sctx2;
        shelper2 = new SellDatabase.DBHelper(sc2);
    }

    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context){
            super(context, DBName, null, DBVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sdb2) {
            try {
                sdb2.execSQL(SCREATETB);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sdb2, int soldVersion2, int snewVersion2) {
            Log.w(TAG, "Upgrading DB");
            sdb2.execSQL("DROP TABLE IF EXISTS smyTB");
            onCreate(sdb2);
        }
    }
    public SellDatabase sopenDB2(){
        try {
            sdb2 = shelper2.getWritableDatabase();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return this;
    }
    public void sclose2(){
        shelper2.close();
    }
    public long sadd2(String newMoneyUnit1, String newMoneyUnit2, String newChangeRate){
        try {
            ContentValues scv2 = new ContentValues();
            scv2.put(SMONEYUNIT1, newMoneyUnit1);
            scv2.put(SMONEYUNIT2, newMoneyUnit2);
            scv2.put(SCHANGERATE, newChangeRate);
            //-----------------------------------------------------
            Calendar cal = Calendar.getInstance();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MMMM/d", Locale.US);
            String strDate = sdf.format(cal.getTime());
            DateFormat df = new java.text.SimpleDateFormat("h:mm a");
            String strTime = df.format(Calendar.getInstance().getTime());
            //-------------------------------------------------------
            scv2.put(SCURRENTDATE, strDate);
            scv2.put(SCURRENTTIME, strTime);
            return sdb2.insert(STBNAME, ROWID, scv2);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public Cursor sgetAllValues2(){
        String[] scolumns2 = {ROWID, SMONEYUNIT1, SMONEYUNIT2, SCHANGERATE, SCURRENTDATE, SCURRENTTIME};
        return sdb2.query(STBNAME, scolumns2,null,null,null,null,null);

    }
    /*public long deleteUnit2(String theUnit){
        try {
            String[] condition2 = {theUnit};
            return db2.delete(TBNAME2,"moneyUnit = ?", condition2);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }*/

}
