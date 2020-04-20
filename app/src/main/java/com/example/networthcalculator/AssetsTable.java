/*
    1. class to display the asset table
    2. This class will display the assets and allow the user to select the assets they have and add their values
    3. CheckBoxes are used to select the assets
    4. Also, the other button allows the user to add any additional assets which are not in the list*/


package com.example.networthcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;


public class AssetsTable extends AppCompatActivity implements Serializable {

    CheckBox checkedAsset;

    /* 1. Object of the class that calculates the total networth
    *  2. This object will be used to call the methods in the class that create an ArrayList for assets name and assets value*/
   // CalculateNetWorth assigningAsset = new CalculateNetWorth();
    TextView chequingAccountTV, savingsAndTaxesTV, rainyDayFunTV, travelSavingsTv, personalDevelopmentSavingsTV,
    investmentsTV, primaryHomeAmountTV, secondaryHomeAmountTV;

    EditText chequingAccount, savingsAndTaxesAmount, rainyDayFund, travelSavings, personalDevelopmentSavings;
    EditText investments, primaryHomeAmount, secondaryHomeAmount;


    //Strings that store the edittext value
    String chequingAccountAsset, savingsAndTaxesAsset, rainyDayFundAsset, travelSavingsAsset, personalDevelopmentSavingsAsset,
            investmentsAsset, primaryHomeAsset, secondaryHomeAsset;

   // AddAssetsLiabilities addingAssets;
    RecyclerView assetsTable;
    DatabaseAsset assetDB = new DatabaseAsset(this,"Assets");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_table);
        getSupportActionBar().setTitle("Select Assets");

        setIDsforViews();



    }

    //method to link the java and xml views

    public void setIDsforViews(){

        chequingAccountTV = findViewById(R.id.chequingAccountTV);
        savingsAndTaxesTV = findViewById(R.id.savingsAndTaxesTV);
        rainyDayFunTV = findViewById(R.id.rainyDayFunTV);
        travelSavingsTv = findViewById(R.id.travelSavingsTv);
        personalDevelopmentSavingsTV = findViewById(R.id.personalDevelopmentSavingsTV);
        investmentsTV = findViewById(R.id.investmentsTV);
        primaryHomeAmountTV = findViewById(R.id.primaryHomeAmountTV);
        secondaryHomeAmountTV = findViewById(R.id.secondaryHomeAmountTV);

        chequingAccount = findViewById(R.id.chequingAccount);
        savingsAndTaxesAmount = findViewById(R.id.savingsAndTaxesAmount);
        rainyDayFund = findViewById(R.id.rainyDayFund);
        travelSavings = findViewById(R.id.travelSavings);
        personalDevelopmentSavings = findViewById(R.id.personalDevelopmentSavings);
        investments = findViewById(R.id.investments);
        primaryHomeAmount = findViewById(R.id.primaryHomeAmount);
        secondaryHomeAmount = findViewById(R.id.secondaryHomeAmount);
        //assetsTable = findViewById(R.id.selectedAssetOrLiabilityTable);

    }

    //method to perform an action when a checkbox is checked or unchecked
    //passing the values from textviews and EditTexts

    public void onAssetCheckboxClicked(View view) {

        boolean assetBoxChecked = ((CheckBox) view).isChecked(); // variable to check if the checkbox is checked or not

        switch (view.getId()){

            case R.id.checkbox_chequing:
                checkedAsset = findViewById(R.id.checkbox_chequing);
                chequingAccountAsset = chequingAccount.getText().toString();
                addOrRemoveAssetInMaps(checkedAsset, assetBoxChecked,chequingAccountTV.getText().toString(), chequingAccountAsset);
               break;

            case R.id.checkbox_savings:
                checkedAsset = findViewById(R.id.checkbox_savings);
                savingsAndTaxesAsset = savingsAndTaxesAmount.getText().toString();
                addOrRemoveAssetInMaps(checkedAsset,assetBoxChecked,savingsAndTaxesTV.getText().toString(), savingsAndTaxesAsset);
                break;

            case R.id.checkbox_rainydayFund:
                 checkedAsset = findViewById(R.id.checkbox_rainydayFund);
                 rainyDayFundAsset = rainyDayFund.getText().toString();
                 addOrRemoveAssetInMaps(checkedAsset,assetBoxChecked,rainyDayFunTV.getText().toString(), rainyDayFundAsset);
                 break;

            case R.id.checkbox_pdSavings:
                checkedAsset = findViewById(R.id.checkbox_pdSavings);
                personalDevelopmentSavingsAsset = personalDevelopmentSavings.getText().toString();
                addOrRemoveAssetInMaps(checkedAsset,assetBoxChecked,personalDevelopmentSavingsTV.getText().toString(), personalDevelopmentSavingsAsset);
                break;

            case R.id.checkbox_travelSavings:
                checkedAsset = findViewById(R.id.checkbox_travelSavings);
                travelSavingsAsset = travelSavings.getText().toString();
                addOrRemoveAssetInMaps(checkedAsset,assetBoxChecked,travelSavingsTv.getText().toString(), travelSavingsAsset);
                break;

            case R.id.checkbox_investments:
                checkedAsset = findViewById(R.id.checkbox_investments);
                investmentsAsset = investments.getText().toString();
                addOrRemoveAssetInMaps(checkedAsset,assetBoxChecked,investmentsTV.getText().toString(), investmentsAsset);
                break;

            case R.id.checkbox_primeHome:
                checkedAsset = findViewById(R.id.checkbox_primeHome);
                primaryHomeAsset = primaryHomeAmount.getText().toString();
                addOrRemoveAssetInMaps(checkedAsset,assetBoxChecked,primaryHomeAmountTV.getText().toString(), primaryHomeAsset);
                break;

            case R.id.checkbox_secHome:
                checkedAsset = findViewById(R.id.checkbox_secHome);
                secondaryHomeAsset = secondaryHomeAmount.getText().toString();
                addOrRemoveAssetInMaps(checkedAsset,assetBoxChecked,secondaryHomeAmountTV.getText().toString(), secondaryHomeAsset);
                break;


        }
    }

    //function to add all the assets that user wants to add in the final list of asset

    public void addOrRemoveAssetInMaps(final CheckBox assetBox, boolean assetBoxChecked, String assetNameTV, String assetValueET){

        if (assetBoxChecked){

            if(!assetValueET.isEmpty() && countDecimals(assetValueET)){
               // assigningAsset.addAssets(assetNameTV, assetValueET);
                assetDB.addAsset(assetNameTV, assetValueET); //storing in db
//                int size1 = assigningAsset.assetNameList.size();
//                String size2 = size1.toString();
                //Toast.makeText(AssetsTable.this,String.valueOf(assigningAsset.assetNameList.size()) , Toast.LENGTH_SHORT).show();

            }

            else{
                assetBox.setChecked(false);
                Toast.makeText(this, "Enter the correct value", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "CheckBox is unchecked", Toast.LENGTH_SHORT).show();
        }


    }

    //function to check if there is more than one decimal point

    public boolean countDecimals(String assetValue){

        int count = 0;
        for(int i = 0; i < assetValue.length(); i++){

            if(assetValue.charAt(i) == '.'){
                count++;
                if(count > 1)
                    return false;

            }

        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actionbar_save_menu,menu);
        return true;
    }

    /*
        1. to handle the click event on the menu that is on the action bar
        2. By clicking on the save button the user will go back to the AddAssetsLiabilities class that will display the selected assets*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.save:

                if (assetDB.assetCount() == 0)
                {
                    Toast.makeText(this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent back = new Intent(this, AddAssetsLiabilities.class);
                    String save = "Assets";
                    back.putExtra("saved", save);
                    startActivity(back);
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //disabling the back button

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if(assetDB.assetCount() == 0)
            {
                Toast.makeText(this, "Fill the fields", Toast.LENGTH_SHORT).show();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
