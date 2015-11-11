package com.demo.mummyding.apitest.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.webkit.WebView;

import com.demo.mummyding.apitest.cache.tables.PolicyTable;
import com.demo.mummyding.apitest.model.PolicyBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.interfaces.PBEKey;

/**
 * Created by mummyding on 15-11-9.
 */
public class PolicyCache {
    private Context mContext;
    private SQLiteOpenHelper mHelper;
    private List<PolicyBean> list = new ArrayList<PolicyBean>();
    public PolicyCache(Context context){
        mContext = context;
        mHelper = DataBaseHelper.instance(context);
    }
    public  void cache(List<PolicyBean> list) {
        WebView wb = new WebView(mContext);
        wb.clearCache(true);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.beginTransaction();
        db.execSQL(CONSTANT.DROP_TABLE + PolicyTable.NAME);
        db.execSQL(PolicyTable.CREATE_TABLE);
        ContentValues values = new ContentValues();
        for(PolicyBean policyBean:list){
            values.put(PolicyTable.DATE,policyBean.getPubTime());
            values.put(PolicyTable.DESCRIPTION,policyBean.getDescription());
            values.put(PolicyTable.LINK,policyBean.getLink());
            values.put(PolicyTable.TITLE,policyBean.getTitle());
            db.insert(PolicyTable.NAME,null,values);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    public List<PolicyBean> loadFromCache(){
        Cursor cursor = query();
        while (cursor.moveToNext()){
            PolicyBean policyBean = new PolicyBean();
            policyBean.setTitle(cursor.getString(1));
            policyBean.setDescription(cursor.getString(2));
            policyBean.setPubTime(cursor.getString(3),true);
            policyBean.setLink(cursor.getString(4));
            list.add(policyBean);
        }
        return list;
    }
    private Cursor query(){
        return mHelper.getReadableDatabase().query(PolicyTable.NAME,null,null,null,null,null,null);
    }
}
