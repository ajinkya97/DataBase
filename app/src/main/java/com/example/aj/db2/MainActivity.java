package com.example.aj.db2;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DbHelper mydb;

    EditText fname,lname,marks;
    Button insert,show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DbHelper(this);

        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        marks=(EditText)findViewById(R.id.marks);
        insert=(Button)findViewById(R.id.insert);
        show=(Button)findViewById(R.id.selectdata);

        Data();
        showAllData();
    }
    public void Data()
    {
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean i= mydb.InserData(fname.getText().toString(),lname.getText().toString(),marks.getText().toString());
                if(i=true)
                {
                    Toast.makeText(MainActivity.this, "Inserted Succesfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showAllData()
    {
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur=mydb.showData();
                if(cur.getCount()==0)
                {
                    message("ERROR","No Data Found");
                    return;
                }
                else
                {
                    StringBuffer buffer=new StringBuffer();
                    while(cur.moveToNext())
                    {
                        buffer.append("ID"+cur.getString(0)+"\n");
                        buffer.append("FNAME"+cur.getString(1)+"\n");
                        buffer.append("LNAME"+cur.getString(2)+"\n");
                        buffer.append("MARKS"+cur.getString(3)+"\n");
                    }
                    message("DATA",buffer.toString());
                }
            }
        });
    }

    public void message(String title,String message)
    {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setCancelable(true);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.show();
    }
}
