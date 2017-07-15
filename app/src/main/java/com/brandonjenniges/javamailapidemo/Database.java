package com.brandonjenniges.javamailapidemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by TC on 7/14/2017.
 */

public class Database extends SQLiteOpenHelper {
    String DATABASE_NAME="userdb";
    String TABLE_NAME="user_info";
    String COL1="id";
    String COL2="id_password";
    String COL3="full_name";
    String COL4="user_name";
    String COL5="password";

    public Database(Context context) {
        super(context, "userdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql="CREATE TABLE "+TABLE_NAME+" ("+COL1+" TEXT PRIMARY KEY, "+COL2+" TEXT, "+COL3+" TEXT, "+COL4+" TEXT, "+COL5+" TEXT)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql="DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);

    }

    public boolean insertAll(String email,String epassword,String name,String userName,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL1,email);
        values.put(COL2,epassword);
        values.put(COL3,name);
        values.put(COL4,userName);
        values.put(COL5,password);
        try
        {
            db.insert(TABLE_NAME,null,values);
            return true;
        }catch (Exception e)
        {
            return false;
        }


    }

    public Cursor findData(String username, String password)
    {

        SQLiteDatabase db=this.getReadableDatabase();
        String sql="SELECT * FROM "+TABLE_NAME+" WHERE "+COL4+" = '"+username+"' AND "+COL5+" = '"+password+"'";
//        String sql="SELECT * FROM "+TABLE_NAME;
        Cursor result=db.rawQuery(sql,null);
        return result;
    }
}
