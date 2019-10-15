package com.careerinfoway.sqlitedatabasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayData extends AppCompatActivity {

    ListView lv;
    DBmain objdb;
    SQLiteDatabase db;
    int[] id;
    String[] fname,lname,mobileno;
    Button btnedit,btndelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        lv = findViewById(R.id.lv);
        objdb = new DBmain(DisplayData.this);

        displaydata();
    }

    private void displaydata() {

        db = objdb.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from student",null);

        if(cursor.getCount()>0){
            id = new int[cursor.getCount()];
            fname = new String[cursor.getCount()];
            lname = new String[cursor.getCount()];
            mobileno = new String[cursor.getCount()];

            int i=0;
            while(cursor.moveToNext()){
                id[i] = cursor.getInt(0);
                fname[i] = cursor.getString(1);
                lname[i] = cursor.getString(2);
                mobileno[i] = cursor.getString(3);

                i++;
            }

            Custom adapter = new Custom();
            lv.setAdapter(adapter);
        }
    }

    class Custom extends BaseAdapter{

        @Override
        public int getCount() {
            return fname.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View v, ViewGroup viewGroup) {
            v = LayoutInflater.from(DisplayData.this).inflate(R.layout.single_data,viewGroup,false);

            TextView tvfname = v.findViewById(R.id.fname);
            TextView tvlname = v.findViewById(R.id.lname);
            TextView tvmobileno = v.findViewById(R.id.mobileno);
            btnedit = v.findViewById(R.id.btnedit);
            btndelete = v.findViewById(R.id.btndelete);

            tvfname.setText(fname[i]);
            tvlname.setText(lname[i]);
            tvmobileno.setText(mobileno[i]);

            btnedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String msg = "";
                    msg += "Firstname = "+fname[i];
                    msg += "Lastname = "+lname[i];
                    msg += "MobileNo = "+mobileno[i];

                    Bundle data = new Bundle();
                    data.putInt("id",id[i]);
                    data.putString("fname",fname[i]);
                    data.putString("lname",lname[i]);
                    data.putString("mobileno",mobileno[i]);
                    Intent i = new Intent(DisplayData.this,MainActivity.class);
                    i.putExtra("userdata",data);
                    startActivity(i);
                }
            });

            btndelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db = objdb.getWritableDatabase();

                    int recordid = db.delete("student","id="+id[i],null);

                    if(recordid!=-1){
                        Toast.makeText(DisplayData.this,"Record Deleted Successfully",Toast.LENGTH_SHORT).show();
                        displaydata();
                    }
                }
            });

            return v;
        }
    }
}
