package com.firstapp.sqlite_stroingdata;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name,mail,rollno;
    TextView setmail;
    StudentHelper studentHelper;
    SQLiteDatabase db;


    String nameStr,mailStr;
    int value;

    ListView SqlListView;
    Cursor cursor;

    ArrayList<String> stringArrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.student_name);
        mail = findViewById(R.id.student_mail);
        rollno = findViewById(R.id.student_rollno);
        setmail = findViewById(R.id.set_mail);
        SqlListView = findViewById(R.id.SQlList);


//        studentHelper=new StudentHelper(this);
//        db=studentHelper.getReadableDatabase();
//
//        arrayAdapter=new ArrayAdapter<>(this,R.layout.activity_main,stringArrayList);
//        SqlListView.setAdapter(arrayAdapter);


        studentHelper = new StudentHelper(this);
        db = studentHelper.getReadableDatabase();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.activity_list_item, stringArrayList);
        SqlListView.setAdapter(arrayAdapter);
    }





    public void update(View view) {

        nameStr=name.getText().toString();
        mailStr=mail.getText().toString();
        value=Integer.parseInt(rollno.getText().toString());

        long set=studentHelper.updateMethod(db,value,nameStr,mailStr);

        if(set==0)
        {
            Toast.makeText(this, "No Record is Exist", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, " Record Updated Successfully", Toast.LENGTH_SHORT).show();

        }
    }

    public void read(View view) {

        stringArrayList.clear();
        String[] Col={"Sno","Name","Mail"};
        cursor=db.query("ODSTable",Col,null,null,null,null,null);

        if(cursor.getCount()>0 &&cursor!=null)
        {
            while (cursor.moveToNext())
            {
                String name=cursor.getString(1);
                String mail=cursor.getString(2);

                stringArrayList.add(""+name+"\n"+mail);
                arrayAdapter.notifyDataSetChanged();
            }

        }
        else
        {
            Toast.makeText(this, "No Record Found", Toast.LENGTH_SHORT).show();
        }

    }

    public void insert(View view) {

        // setmail.setText(""+name.getText().toString());

        nameStr=name.getText().toString();
        mailStr=mail.getText().toString();
        value=Integer.parseInt(rollno.getText().toString());

        long set=studentHelper.createMethod(db,value,nameStr,mailStr);

        if(set==-1)
        {
            Toast.makeText(this, "Record Already Exist", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "New Record Created Successfully", Toast.LENGTH_SHORT).show();

        }




    }

    public void Delete(View view) {
        value=Integer.parseInt(rollno.getText().toString());

        long set=studentHelper.delete(db,value);

        if(set==0)
        {
            Toast.makeText(this, "No Record is Exist", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, " Record Delete Successfully", Toast.LENGTH_SHORT).show();

        }
    }
}
