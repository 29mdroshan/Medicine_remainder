package com.example.medicine_app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicine_app.DataBaseConn;
import com.example.medicine_app.R;

public class MainActivity extends AppCompatActivity {

    EditText name,date;
    TextView txt;
    Button insert, fetch;
    Spinner spinner;
    Switch switch1;


    DataBaseConn dbConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbConnection=new DataBaseConn(this);

        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        txt=findViewById(R.id.txt);
        date=findViewById(R.id.date);
        insert=findViewById(R.id.insert);
        fetch=findViewById(R.id.fetch);
        fetch.setVisibility(View.INVISIBLE);
        spinner=findViewById(R.id.spinner);
        switch1=findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    fetch.setVisibility(View.INVISIBLE);
                    insert.setVisibility(View.VISIBLE);
                    name.setVisibility(View.VISIBLE);
                    txt.setVisibility(View.VISIBLE);
                }

                else {
                    name.setVisibility(View.INVISIBLE);
                    insert.setVisibility(View.INVISIBLE);
                    txt.setVisibility(View.INVISIBLE);
                    fetch.setVisibility(View.VISIBLE);
                }
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mname=name.getText().toString();
                String mdate=date.getText().toString();
                String mtime=spinner.getSelectedItem().toString();
                boolean insert=dbConnection.insertvalues(mname,mdate,mtime);
                if (insert==true){
                    Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_SHORT).show();
                    name.setText(null);
                    date.setText(null);
                }
                else
                    Toast.makeText(getApplicationContext(),"Data Not Inserted",Toast.LENGTH_SHORT).show();
            }
        });
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mdate=date.getText().toString();
                String mtime=spinner.getSelectedItem().toString();
                String med="";
                Cursor c= dbConnection.fetchdata(mdate,mtime);
                c.moveToFirst();
                do {
                    med=med+(String.valueOf(c.getString(c.getColumnIndex("medicineName"))));

                    med+="\n";
                }while (c.moveToNext());
                Toast.makeText(getApplicationContext(),med,Toast.LENGTH_LONG).show();

            }
        });

    }
}