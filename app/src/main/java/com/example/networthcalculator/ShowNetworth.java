package com.example.networthcalculator;

//Class which displas totals assets, liabilities and networth

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import java.io.Serializable;

public class ShowNetworth extends AppCompatActivity implements Serializable {

    TextView name, totalAssets1, totalLiabilities1, networth, message;
    DatabaseAsset getAsset = new DatabaseAsset(this,"Assets");
    DatabaseLiability getLiability = new DatabaseLiability(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_networth);
        getSupportActionBar().setTitle("NetWOrth");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.userName);
        totalAssets1 = findViewById(R.id.totalAssets);
        totalLiabilities1 = findViewById(R.id.totalLiabilities);
        networth = findViewById(R.id.networth);
        message = findViewById(R.id.message);

        //getting the values in the object fromADddAssetsAndLiabilities


        Intent fromAddAssetLiablities = getIntent();
        CalculateNetWorth calculateNetWorth = (CalculateNetWorth) fromAddAssetLiablities.getSerializableExtra("total");

        name.setText(calculateNetWorth.name);
        totalAssets1.setText(String.valueOf(calculateNetWorth.totalAssets));
        totalLiabilities1.setText(String.valueOf(calculateNetWorth.totalLiabilities));
        networth.setText(String.valueOf(calculateNetWorth.totalNetWorth));

        //comparing the total assets and liabilities

        if(calculateNetWorth.totalAssets < calculateNetWorth.totalLiabilities){
            message.setText("You Need to Take care of your debts properly");
        }
        else {
            message.setText("Great");
        }

    }

    //disabling the back button

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {


            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //deleting the table rows

    @Override
    protected void onDestroy() {
        boolean result;
        result = getAsset.deleteAllAsset();
        result = getLiability.deleteAllLiability();
        super.onDestroy();
    }
}
