package com.example.networthcalculator;

/*
  1. Class where assets and liabilitieds selected by the user will be displayed in the list
  2. The user would be able to manage the assets even after selecting*/

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class AddAssetsLiabilities extends AppCompatActivity implements Serializable {

    BottomNavigationView assetOrLiabilityView; // asset and liabilities tab selection button

//    TextView selectCurrency;
//    static ChooseCurrency createCurrencyDialog = new ChooseCurrency(); // instantiating the choose currency class

    TextView userName;
    Button calculateNetworth;// selecting the currency
    RecyclerView assetsAndLiabilitiesTable; // the view that will display the selected assets

    // Objects of class DatabaseAsset and DatabaseLiability to access the database methods

    DatabaseAsset getAsset = new DatabaseAsset(this,"Assets");
    DatabaseLiability getLiability = new DatabaseLiability(this);


    FloatingActionButton addAssetOrLiabilityButton;
    Button openAssetTable, openLiabilityTable;

    // arraylist created to store the asset name and values

    ArrayList<String> assetNameList = new ArrayList<>();
    ArrayList<String> assetValueList = new ArrayList<>();

    // arraylist created to store the liability name and value

    ArrayList<String> liabilityNameList = new ArrayList<>();
    ArrayList<String> liabilityValueList = new ArrayList<>();

    static boolean isAssetChecked = true;
    static boolean isLiabilityChecked = false;
    static String yourName; //userName is declared as static to maintain the lifeTime

    AlertDialog alertDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assets_liabilities);
        getSupportActionBar().setTitle("Assets and Liabilities");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setting the views ids

        calculateNetworth = findViewById(R.id.calculateNetworth);
        assetsAndLiabilitiesTable = findViewById(R.id.selectedAssetOrLiabilityTable);
        assetOrLiabilityView = findViewById(R.id.assetsAndLiabilitiesButtons);
        addAssetOrLiabilityButton = findViewById(R.id.floating_action_button);
        userName = findViewById(R.id.userName);

        // setting the listener on the textview to select the currency

//        selectCurrency.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                createCurrencyDialog.createDialogBoxForCurrency(AddAssetsLiabilities.this, selectCurrency);
//
//            }
//        });


        //coming back from the AssetsTable, LiabilityTable and MainActivity class
        //inflating the assets on the recyclerView


        Intent comeBackFromAssetOrLiabilityOrMain= getIntent();

        //checking if the user has come from AssetsTable or LiabilityTable or MainActivity
        if (comeBackFromAssetOrLiabilityOrMain.hasExtra("saved"))
        {
            if(comeBackFromAssetOrLiabilityOrMain.getStringExtra("saved").equals("Assets")){
                showAssets();
            }
            else {
                showLiabilities();
            }
        }
        else if(comeBackFromAssetOrLiabilityOrMain.hasExtra("userName"))
        {
            yourName = comeBackFromAssetOrLiabilityOrMain.getStringExtra("userName");
        }

        userName.setText(yourName); //Setting the UserName after coming from any activity


        //setting the listener on the BottomNavigatinView to switch between the assets and liabilities table


        assetOrLiabilityView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.assetsFragments:
                        showAssets(); // calling method to display assets in recyclerview

                        break;


                    case  R.id.liabilitiesFragments:
                        showLiabilities(); //calling method to display liabilities in recyclerview
                        break;

                }
                return false;
            }
        });
    }

    //method to view assets in recyclerview
    public void showAssets(){

        //Handling the NullPointerException

        try{

            if(assetNameList.size() == 0)
            {
                if (getAssetDataFromDB() == true) //checking if the table is empty or not
                {
                    //String save = savedMapAsset.getStringExtra("savedMap");

                    //Setting the asset name and list in recyclerview

                    RecyclerViewAdapter createAssetTable = new RecyclerViewAdapter(AddAssetsLiabilities.this, assetNameList, assetValueList);
                    assetsAndLiabilitiesTable.setAdapter(createAssetTable);
                    assetsAndLiabilitiesTable.setLayoutManager(new LinearLayoutManager(AddAssetsLiabilities.this));
                }
                else{
                    Toast.makeText(AddAssetsLiabilities.this, "No Data or app started", Toast.LENGTH_SHORT).show();
                }
            }
            else {

                //creating the object for RecyclerViewAdapter class  and inflating the view
                RecyclerViewAdapter createAssetTable = new RecyclerViewAdapter(AddAssetsLiabilities.this, assetNameList, assetValueList);
                assetsAndLiabilitiesTable.setAdapter(createAssetTable);
                assetsAndLiabilitiesTable.setLayoutManager(new LinearLayoutManager(AddAssetsLiabilities.this));

            }
            //Intent savedMapAsset = getIntent();

        }
        catch (NullPointerException e) {
            System.err.println("Null pointer exception");
        }

    }

    //method to view the liabilities on recyclerView

    public void showLiabilities(){
        try{
            //Intent savedMapAsset = getIntent();
            if(liabilityNameList.size() == 0){
                if (getLiabilityDataFromDB() == true) //checking if the table is empty or not
                {
                    //String save = savedMapAsset.getStringExtra("savedMap");

                    //creating the object for RecyclerViewAdapter class  and inflating the view

                    RecyclerViewAdapter createAssetTable = new RecyclerViewAdapter(AddAssetsLiabilities.this, liabilityNameList, liabilityValueList);
                    assetsAndLiabilitiesTable.setAdapter(createAssetTable);
                    assetsAndLiabilitiesTable.setLayoutManager(new LinearLayoutManager(AddAssetsLiabilities.this));
                }
                else{
                    Toast.makeText(AddAssetsLiabilities.this, "No Data or app started", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                RecyclerViewAdapter createAssetTable = new RecyclerViewAdapter(AddAssetsLiabilities.this, liabilityNameList, liabilityValueList);
                assetsAndLiabilitiesTable.setAdapter(createAssetTable);
                assetsAndLiabilitiesTable.setLayoutManager(new LinearLayoutManager(AddAssetsLiabilities.this));


            }
        }
        catch (NullPointerException e) {
            System.err.println("Null pointer exception");
        }
    }

    //getting all the rows of the Assettable and storing them in the list

    public boolean getAssetDataFromDB(){
        Cursor cursor = getAsset.getAssets();
        if (cursor.getCount() == 0){
            //Toast.makeText(AddAssetsLiabilities.this, "No Data or App Started", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{

            while (cursor.moveToNext()){
                assetNameList.add(cursor.getString(0));
                //Toast.makeText(AddAssetsLiabilities.this, cursor.getString(0), Toast.LENGTH_SHORT).show();
                assetValueList.add(cursor.getString(1));
                //Toast.makeText(AddAssetsLiabilities.this, cursor.getString(1), Toast.LENGTH_SHORT).show();
            }

        }
        return true;
    }

    //getting all the rows from liabilities table and storing them in the list
    public boolean getLiabilityDataFromDB(){
        Cursor cursor = getLiability.getLiabilities();
        if (cursor.getCount() == 0){
            //Toast.makeText(AddAssetsLiabilities.this, "No Data", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{

            while (cursor.moveToNext()){
                liabilityNameList.add(cursor.getString(0));
                //Toast.makeText(AddAssetsLiabilities.this, cursor.getString(0), Toast.LENGTH_SHORT).show();
                liabilityValueList.add(cursor.getString(1));
                //Toast.makeText(AddAssetsLiabilities.this, cursor.getString(1), Toast.LENGTH_SHORT).show();
            }

        }
        return true;
    }

    //showing  dialogbox with two options of assets and liabilities on the click of floatingaction button
    public void onFloatingActionButtonClicked(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(AddAssetsLiabilities.this);
        View mView = getLayoutInflater().inflate(R.layout.asset_liability_option_dialog, null);
        alert.setView(mView);
        alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        openAssetTable = mView.findViewById(R.id.openAssetTable);
        openLiabilityTable = mView.findViewById(R.id.openLiabilityTable);
    }

    //handling event on openliability button which will go to LiabilityTableClass

    public void openLiability(View view) {
        alertDialog.dismiss();
        Intent gotToLiabilitiesTable = new Intent(AddAssetsLiabilities.this, LiabilitiesTable.class);
        startActivity(gotToLiabilitiesTable);
        isAssetChecked = false;
        isLiabilityChecked = true;

    }

    //handling event on openAsset button which will go to AssetTableClass


    public void openAsset(View view) {
        alertDialog.dismiss();
        Intent gotToAssetsTable = new Intent(AddAssetsLiabilities.this, AssetsTable.class);
        //gotToAssetsTable.putExtra("referenceAddAssets", AddAssetsLiabilities.this);
        startActivity(gotToAssetsTable);
        isAssetChecked = true;
        isLiabilityChecked = false;


    }

    //disabling the back key in phone
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {


            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //calculating the total networth
    //handling event when calculate button is pressed
    public void calculateTotalNetWorth(View view) {
        if (getAsset.assetCount() == 0 || getLiability.liabilityCount() == 0){
            Toast.makeText(this, "No Assets or Liabilities Added", Toast.LENGTH_SHORT).show();
        }
        else {

            //creating object for CalculateNetworth and passing name and  lists of assets and liabilities
            CalculateNetWorth calculate = new CalculateNetWorth(assetValueList, liabilityValueList, yourName);
            calculate = calculate.calculateNetWorth(); // storing the value in object

            //passing the object through intent to open showNetworthclass
            Intent goToShowNetWorth = new Intent(AddAssetsLiabilities.this, ShowNetworth.class);
            goToShowNetWorth.putExtra("total", calculate);
            startActivity(goToShowNetWorth);


        }

    }

    //deleting the tabledata when app or activity destroys

    @Override
    protected void onDestroy() {
        boolean result;
        result = getAsset.deleteAllAsset();
        result = getLiability.deleteAllLiability();
        super.onDestroy();
    }
}
