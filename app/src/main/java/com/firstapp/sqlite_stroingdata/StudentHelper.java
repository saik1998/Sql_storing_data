package com.firstapp.sqlite_stroingdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class StudentHelper extends SQLiteOpenHelper {
    Context context;

    public StudentHelper(@Nullable Context context) {
        super(context,"ONDEVICEDATABASE.db",null,1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create Table OndeviceTable(rollno Integer primary key,Name text,Mail text)");
        Toast.makeText(context, "Table created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int i,int i1){



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
