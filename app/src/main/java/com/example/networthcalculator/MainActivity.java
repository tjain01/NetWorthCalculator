package com.example.networthcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText userName;
    Button nextAcitivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userName);
        nextAcitivity = findViewById(R.id.nextActivity);

        //going to AddAssetsLiabilities

        nextAcitivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uName = userName.getText().toString();
                if(checkIfEmpty_or_hasSpecialCharacters(uName) == true){
                    Intent next = new Intent(MainActivity.this, AddAssetsLiabilities.class);
                    next.putExtra("userName", uName);
                    startActivity(next);

                }
            }
        });
    }

    //checking if the string has special characters or numbers

    public boolean checkIfEmpty_or_hasSpecialCharacters(String name){
        Pattern p = Pattern.compile("[^a-z A-Z]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(name);
        boolean b = m.find();

        if (name.length() == 0){
            Toast.makeText(this, "Please Enter The Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            if (b){
                Toast.makeText(this, "Enter a valid name", Toast.LENGTH_SHORT).show();
                return false;
            }
            else {
                return true;
            }
        }
    }


}
