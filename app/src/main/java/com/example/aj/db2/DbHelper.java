package com.example.aj.db2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aj on 15-Oct-17.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="students.db";
    public static final String TABLE_NAME="stu";
    public static final String ID="ID";
    public static final String FNAME="FNAME";
    public static final String LNAME="LNAME";
    public static final String MARKS="MARKS";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, FNAME TEXT, LNAME TEXT, MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean InserData(String fname,String lname,String marks)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("FNAME",fname);
        c.put("LNAME",lname);
        c.put("MARKS",marks);
        long result=sqLiteDatabase.insert(TABLE_NAME,null,c);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor showData()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cur=sqLiteDatabase.rawQuery("select * from"+TABLE_NAME,null);
        return cur;
    }
}
