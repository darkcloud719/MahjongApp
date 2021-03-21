package com.example.mahjonggamev1.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "GameLibrary.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "gameRule";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "gameRule_title";
    private static final String COLUMN_DI = "di";
    private static final String COLUMN_TAI = "tai";

    public MyDatabaseHelper(@NonNull Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " + COLUMN_DI + " INTEGER, " + COLUMN_TAI + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int li){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addGameRule(String title, int di, int tai){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DI, di);
        cv.put(COLUMN_TAI, tai);
        long result = db.insert(TABLE_NAME, null, cv);
        /*if(result == -1){
            Toast.makeText(context, "Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Added Successfully!",Toast.LENGTH_SHORT).show();
        }*/
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if(db!=null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void deleteGameRule(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?",new String[]{row_id});
        /*if(result == -1){
            Toast.makeText(context,"Failed to Delete.",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Successfully Delete!",Toast.LENGTH_SHORT).show();
        }*/
    }
}
