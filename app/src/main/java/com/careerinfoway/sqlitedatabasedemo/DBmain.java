package com.careerinfoway.sqlitedatabasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {
    String sql;

    public DBmain(@Nullable Context context) {
        super(context, "studentdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sql = "create table student (id integer primary key, fname text, lname text, mobileno text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        sql = "drop table student";
        db.execSQL(sql);

        onCreate(db);
    }
}
