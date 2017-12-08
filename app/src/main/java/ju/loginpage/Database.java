package ju.loginpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.logging.Handler;

/**
 * Created by jumegol on 12/2/2017.
 */

class Database {

    static final String ROWID = "id";
    static final String MONEYUNIT = "moneyUnit";
    static final String TAG = "DBSpinner";

    static final String DBName = "myDB";
    static final String TBNAME = "myTb";
    static final int DBVERSION = '1';

    static final String CREATETB = "CREATE TABLE myTb(id INTEGER PRIMARY KEY AUTOINCREMENT, moneyUnit TEXT NOT NULL);";

    final Context c;
    SQLiteDatabase db;
    DBHelper helper;
    public Database(Context ctx){
        this.c = ctx;
        helper = new DBHelper(c);
    }

    private static class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context){
            super(context, DBName, null, DBVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATETB);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading DB");
            db.execSQL("DROP TABLE IF EXISTS myTB");
            onCreate(db);
        }
    }
    public Database openDB(){
        try {
            db = helper.getWritableDatabase();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return this;
    }
    public void close(){
        helper.close();
    }
    public long add(String moneyUnits){
        try {
            ContentValues cv = new ContentValues();
            cv.put(MONEYUNIT, moneyUnits);

            return db.insert(TBNAME, ROWID, cv);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public Cursor getAllValues(){
        String[] columns = {ROWID, MONEYUNIT};
        return db.query(TBNAME, columns,null,null,null,null,null);

    }
    public long deleteUnit(String theUnit){
        try {
            String[] condition = {theUnit};
            return db.delete(TBNAME,"moneyUnit = ?", condition);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }



}
