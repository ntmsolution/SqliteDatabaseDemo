package com.careerinfoway.sqlitedatabasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtfname,edtlname,edtmobileno;
    Button btnsubmit,btndisplay;
    DBmain objdb;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtfname = findViewById(R.id.edtfname);
        edtlname = findViewById(R.id.edtlname);
        edtmobileno = findViewById(R.id.edtmobileno);
        btnsubmit = findViewById(R.id.btnsubmit);
        btndisplay = findViewById(R.id.btndisplay);


        objdb = new DBmain(MainActivity.this);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put("fname",edtfname.getText().toString());
                cv.put("lname",edtlname.getText().toString());
                cv.put("mobileno",edtmobileno.getText().toString());

                db = objdb.getWritableDatabase();

                long id = db.insert("student",null,cv);

                if(id!=-1){
                    Toast.makeText(MainActivity.this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();

                    edtfname.setText("");
                    edtlname.setText("");
                    edtmobileno.setText("");
                }else{
                    Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btndisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,DisplayData.class));
            }
        });
    }
}
