package com.example.tpsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText Fname,Lname,Age,Situation;
    Button update_btn;
    String id,fName,lName,age,situation;
    Button deleteBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        this.Fname=findViewById(R.id.Fname_txt_update);
        this.Lname=findViewById(R.id.Lname_txt_update);
        this.Age=findViewById(R.id.Age_txt_update);
        this.Situation=findViewById(R.id.Situation_txt_update);
        update_btn=findViewById(R.id.update_button);

        getIntentData();

        deleteBtn= findViewById(R.id.delete_button);
        deleteBtn.setOnClickListener(this::deletePerson);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db=new Database(UpdateActivity.this);
                fName = Fname.getText().toString().trim();
                lName = Lname.getText().toString().trim();
                age = Age.getText().toString().trim();
                situation=Situation.getText().toString().trim();
                db.updateData(id,fName,lName,age,situation);
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });


    }

    private void deletePerson(View view) {
        Database db=new Database(UpdateActivity.this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Suppression de "+Lname.getText().toString()+" "+Fname.getText().toString());
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage("Vous confirmez la suppression ?");
        builder.setPositiveButton(android.R.string.ok,
                (dialog, idbtn) -> {
                    db.DeletePerson(Integer.parseInt(id));
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                });
        builder.setNegativeButton(android.R.string.cancel, null);
        Dialog dialog= builder.create();
        dialog.show();
    }

    void getIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("FirstName") &&
                getIntent().hasExtra("LastName") && getIntent().hasExtra("Age") && getIntent().hasExtra("Situation")){
            //get data
            id=getIntent().getStringExtra("id");
            fName=getIntent().getStringExtra("FirstName");
            lName=getIntent().getStringExtra("LastName");
            age=getIntent().getStringExtra("Age");
             situation=getIntent().getStringExtra("Situation");
             //set data
            Fname.setText(fName);
            Lname.setText(lName);
            Age.setText(age);
            Situation.setText(situation);
        }else{
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }

}