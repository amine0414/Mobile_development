package com.example.tpsqlite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAddapter extends RecyclerView.Adapter<CustomAddapter.MyViewHolder> {
    Context context;
    ArrayList Person_id,Fname,Lname,Age,Situation;
    Activity activity;
    public CustomAddapter(Activity activity,Context cn,ArrayList id,ArrayList Fname,ArrayList Lname,ArrayList age,ArrayList sit){

        this.activity=activity;
    this.Person_id=id;
        this.context=cn;
        this.Fname=Fname;
        this.Lname=Lname;
        this.Age=age;
        this.Situation=sit;

    }
    @NonNull
    @Override
    public CustomAddapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomAddapter.MyViewHolder holder, int position) {
     holder.id_txt_output.setText(String.valueOf(Person_id.get(position)));
     holder.Fname_txt_output.setText(String.valueOf(Fname.get(position)));
     holder.Lname_txt_output.setText(String.valueOf(Lname.get(position)));
     holder.Age_txt_output.setText(String.valueOf(Age.get(position)));
     holder.Situation_txt_output.setText(String.valueOf(Situation.get(position)));

     holder.mainLayout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent=new Intent(context,UpdateActivity.class);
             intent.putExtra("id",String.valueOf(Person_id.get(position)));
             intent.putExtra("FirstName",String.valueOf(Fname.get(position)));
             intent.putExtra("LastName",String.valueOf(Lname.get(position)));
             intent.putExtra("Age",String.valueOf(Age.get(position)));
             intent.putExtra("Situation",String.valueOf(Situation.get(position)));
             activity.startActivityForResult(intent,1);
         }
     });

    }

    @Override
    public int getItemCount() {
        return Person_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
       TextView id_txt_output ,Fname_txt_output,Lname_txt_output,Age_txt_output,Situation_txt_output;
      ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt_output=itemView.findViewById(R.id.id_output);
            Fname_txt_output=itemView.findViewById(R.id.Fname_txt_output);
            Lname_txt_output=itemView.findViewById(R.id.Lname_txt_output);
            Age_txt_output=itemView.findViewById(R.id.Age_txt_output);
            Situation_txt_output=itemView.findViewById(R.id.Situation_txt_output);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
