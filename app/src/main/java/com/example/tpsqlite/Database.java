package com.example.tpsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
 private Context context;
 private static final String DATABASE_NAME="HumainRessources.db";
 private static final int DATABSE_VERSION=1;
 private  static final String TABLE_NAME="Persons";
 private static final String COLUMN_ID="_id";
 private static final String COLUMN_FIRST_NAME="First_Name";
 private static final String COLUMN_LAST_NAME="Last_Name";
 private static final String COLUMN_AGE="Age";
 private static final String COLUMN_SITUATION="Situation";


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String query="CREATE TABLE "+TABLE_NAME+" ( "+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COLUMN_FIRST_NAME+" TEXT, "+COLUMN_LAST_NAME+" TEXT, "+COLUMN_AGE+" INTEGER, "+
                    COLUMN_SITUATION+" TEXT "+");";
            db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
         db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
         onCreate(db);

    }
    void addPerson(String Fname,String Lname,int age,String Situation){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_FIRST_NAME,Fname);
        cv.put(COLUMN_LAST_NAME,Lname);
        cv.put(COLUMN_AGE,age);
        cv.put(COLUMN_SITUATION,Situation);
        long res=db.insert(TABLE_NAME,null,cv);
        if(res==-1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Added Successfully",Toast.LENGTH_SHORT).show();
        }
    }
    Cursor SelectPersons(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+TABLE_NAME;
        Cursor cursor=null;
        if(db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }
}
