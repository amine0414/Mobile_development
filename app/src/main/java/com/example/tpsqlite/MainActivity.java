package com.example.tpsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   RecyclerView recyclerView;
   FloatingActionButton AddButton;
   Database db;
   ArrayList  id,Fname,Lname,Age,Situation;
   CustomAddapter custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        AddButton=findViewById(R.id.AddButton);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
               startActivity(intent);
            }
        });
        db= new Database(MainActivity.this);
        id=new ArrayList<>();
        Fname=new ArrayList<>();
        Lname=new ArrayList<>();
        Age=new ArrayList<>();
        Situation=new ArrayList<>();
        this.stockPersonsIntoList();
        custom=new CustomAddapter(MainActivity.this,id,Fname,Lname,Age,Situation);
        recyclerView.setAdapter(custom );
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }
    void stockPersonsIntoList(){
        Cursor cursor=db.SelectPersons();
        if(cursor.getCount()==0){
            Toast.makeText(MainActivity.this,"Empty List",Toast.LENGTH_SHORT);
        }
        else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                Fname.add(cursor.getString(1));
                Lname.add(cursor.getString(2));
                Age.add(cursor.getString(3));
                Situation.add(cursor.getString(4));
            }
        }
    }

}