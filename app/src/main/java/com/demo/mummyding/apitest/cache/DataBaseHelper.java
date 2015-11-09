package com.demo.mummyding.apitest.cache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.demo.mummyding.apitest.cache.tables.PolicyTable;

/**
 * Created by mummyding on 15-11-9.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private  static final String DB_NAME= "apitest";
    private static  DataBaseHelper instance = null;
    private static final int DB_VERSION = 1;

    private DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PolicyTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public static synchronized DataBaseHelper instance(Context context){
        if(instance == null){
            instance = new DataBaseHelper(context,DB_NAME,null,DB_VERSION);
        }
        return instance;
    }
}
