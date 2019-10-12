package com.careerinfoway.sqlitedatabasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayData extends AppCompatActivity {

    ListView lv;
    DBmain objdb;
    SQLiteDatabase db;
    String[] fname,lname,mobileno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        lv = findViewById(R.id.lv);
        objdb = new DBmain(DisplayData.this);

        db = objdb.getReadableDatabase();

        displaydata();
    }

    private void displaydata() {

        Cursor cursor = db.rawQuery("select * from student",null);

        if(cursor.getCount()>0){
            fname = new String[cursor.getCount()];
            lname = new String[cursor.getCount()];
            mobileno = new String[cursor.getCount()];

            int i=0;
            while(cursor.moveToNext()){
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
        public View getView(int i, View v, ViewGroup viewGroup) {
            v = LayoutInflater.from(DisplayData.this).inflate(R.layout.single_data,viewGroup,false);

            TextView tvfname = v.findViewById(R.id.fname);
            TextView tvlname = v.findViewById(R.id.lname);
            TextView tvmobileno = v.findViewById(R.id.mobileno);

            tvfname.setText(fname[i]);
            tvlname.setText(lname[i]);
            tvmobileno.setText(mobileno[i]);

            return v;
        }
    }
}
