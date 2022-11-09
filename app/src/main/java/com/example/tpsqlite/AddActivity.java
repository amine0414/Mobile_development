package com.example.tpsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
     EditText FirstNameInput,LastNameInput,AgeInput,SituationInput;
     Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        FirstNameInput=findViewById(R.id.FirstNameInput);
        LastNameInput=findViewById(R.id.LastNameInput);
        AgeInput=findViewById(R.id.AgeInput);
        SituationInput=findViewById(R.id.SituationInput);
        add_button=findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(AddActivity.this);
                db.addPerson(FirstNameInput.getText().toString().trim(),LastNameInput.getText().toString().trim()
                        ,Integer.valueOf(AgeInput.getText().toString().trim()),SituationInput.getText().toString().trim());
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}