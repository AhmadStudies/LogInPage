package ju.loginpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.util.Log;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by jumegol on 12/6/2017.
 */

class BuyDatabase {
    static final String ROWID = "id";
    static final String MONEYUNIT1 = "moneyUnit1";
    static final String MONEYUNIT2 = "moneyUnit2";
    static final String CHANGERATE = "changeRate";
    static final String CURRENTDATE = "currentDate";
    static final String CURRENTTIME = "currentTime";
    static final String TAG = "DBSpinner";

    static final String DBName = "myDB";
    static final String TBNAME2 = "myTb2";
    static final int DBVERSION = '1';

    static final String CREATETB2 = "CREATE TABLE myTb2(id INTEGER PRIMARY KEY AUTOINCREMENT, moneyUnit1 TEXT NOT NULL, moneyUnit2 TEXT NOT NULL," +
            " changeRate TEXT NOT NULL, currentDate TEXT NOT NULL, currentTime TEXT NOT NULL);";

    final Context c2;
    SQLiteDatabase db2;
    BuyDatabase.DBHelper helper2;
    public BuyDatabase(Context ctx2){
        this.c2 = ctx2;
        helper2 = new BuyDatabase.DBHelper(c2);
    }

    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context){
            super(context, DBName, null, DBVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db2) {
            try {
                db2.execSQL(CREATETB2);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db2, int oldVersion2, int newVersion2) {
            Log.w(TAG, "Upgrading DB");
            db2.execSQL("DROP TABLE IF EXISTS myTB2");
            onCreate(db2);
        }
    }
    public BuyDatabase openDB2(){
        try {
            db2 = helper2.getWritableDatabase();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return this;
    }
    public void close2(){
        helper2.close();
    }
    public long add2(String newMoneyUnit1, String newMoneyUnit2, String newChangeRate){
        try {
            ContentValues cv2 = new ContentValues();
            cv2.put(MONEYUNIT1, newMoneyUnit1);
            cv2.put(MONEYUNIT2, newMoneyUnit2);
            cv2.put(CHANGERATE, newChangeRate);
            //-----------------------------------------------------
            Calendar cal = Calendar.getInstance();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MMMM/d", Locale.US);
            String strDate = sdf.format(cal.getTime());
            DateFormat df = new java.text.SimpleDateFormat("h:mm a");
            String strTime = df.format(Calendar.getInstance().getTime());
            //-------------------------------------------------------
            cv2.put(CURRENTDATE, strDate);
            cv2.put(CURRENTTIME, strTime);
            return db2.insert(TBNAME2, ROWID, cv2);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public Cursor getAllValues2(){
        String[] columns2 = {ROWID, MONEYUNIT1, MONEYUNIT2, CHANGERATE, CURRENTDATE, CURRENTTIME};
        return db2.query(TBNAME2, columns2,null,null,null,null,null);

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
