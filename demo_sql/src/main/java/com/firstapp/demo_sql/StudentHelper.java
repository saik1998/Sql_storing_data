package com.firstapp.demo_sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class StudentHelper extends SQLiteOpenHelper {
    Context context;

    public StudentHelper(@Nullable Context context) {
        super(context, "ODSDATABASE.db", null, 2);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create Table ODSTable(Sno Interger Primary key ,Name text,Mail text)");
        Toast.makeText(context, "SQl Started", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        if(i<i1)
        {
            db.execSQL("ALTER TABLE ODSTable ADD Mobile TEXT");
            db.execSQL("ALTER TABLE ODSTable ADD Address TEXT");
        }

    }

    public long createMethod(SQLiteDatabase db,int sno,String name,String mail)
    {
        ContentValues cv=new ContentValues();
        cv.put("Sno",sno);
        cv.put("Name",name);
        cv.put("Mail",mail);

        long set=db.insert("ODSTable",null,cv);
        return set;
    }

    public long updateMethod(SQLiteDatabase db,int sno,String name,String mail)
    {
        ContentValues cv=new ContentValues();
        cv.put("Sno",sno);
        cv.put("Name",name);
        cv.put("Mail",mail);

        long set=db.update("ODSTable",cv,"Sno="+sno,null);
        return set;
    }

    public long delete(SQLiteDatabase db,int sno)
    {
        ContentValues cv=new ContentValues();
        cv.put("Sno",sno);

        long set=db.delete("ODSTable","Sno="+sno,null);
        return set;
    }
}
