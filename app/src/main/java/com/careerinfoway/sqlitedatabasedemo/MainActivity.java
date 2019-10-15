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
    Button btnsubmit,btndisplay,btnedit;
    DBmain objdb;
    SQLiteDatabase db;
    int id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtfname = findViewById(R.id.edtfname);
        edtlname = findViewById(R.id.edtlname);
        edtmobileno = findViewById(R.id.edtmobileno);
        btnsubmit = findViewById(R.id.btnsubmit);
        btndisplay = findViewById(R.id.btndisplay);
        btnedit = findViewById(R.id.btnedit);

        if(getIntent().getBundleExtra("userdata")!=null){

            Bundle bundle = getIntent().getBundleExtra("userdata");


            id = bundle.getInt("id");
            edtfname.setText(bundle.getString("fname"));
            edtlname.setText(bundle.getString("lname"));
            edtmobileno.setText(bundle.getString("mobileno"));


            btnedit.setVisibility(View.VISIBLE);
            btnsubmit.setVisibility(View.GONE);
        }


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

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put("fname",edtfname.getText().toString());
                cv.put("lname",edtlname.getText().toString());
                cv.put("mobileno",edtmobileno.getText().toString());

                db = objdb.getWritableDatabase();

                long recid = db.update("student",cv,"id="+id,null);

                if(recid !=-1){
                    Toast.makeText(MainActivity.this,"Data Updated Successfully",Toast.LENGTH_SHORT).show();
                    btnsubmit.setVisibility(View.VISIBLE);
                    btnedit.setVisibility(View.GONE);

                    edtfname.setText("");
                    edtlname.setText("");
                    edtmobileno.setText("");
                }else{
                    Toast.makeText(MainActivity.this,"Data Not Updated",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
