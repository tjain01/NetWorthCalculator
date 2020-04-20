package com.example.networthcalculator;

/*Class that will display the list of liabilies*/

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

public class LiabilitiesTable extends AppCompatActivity {

    CheckBox checkedLiability;
    boolean editBoxEmpty = true;

    /* 1. Object of the class that calculates the total networth
     *  2. This object will be used to call the methods in the class that create an ArrayList for assets name and assets value*/
    // CalculateNetWorth assigningAsset = new CalculateNetWorth();
    TextView mortgageOneAmountTV, mortgageTwoAmountTV, investmentLoanAmountTV, studentLoanAmountTV, carLoanAmountTV, lineOfCreditAmountTV, creditcardTwoAmountTV, creditcardOneAmountTV;


    EditText mortgageOneAmount, mortgageTwoAmount, investmentLoanAmount, studentLoanAmount, carLoanAmount, lineOfCreditAmount, creditcardTwoAmount, creditcardOneAmount;



    //Strings that store the edittext value
    String mortgageOne, mortgageTwo, lineOfCredit, investmentLoan, studentLoan,
            carLoan, creditcardTwo, creditcardOne;

    // AddAssetsLiabilities addingAssets;
    RecyclerView assetsTable;
    DatabaseLiability liabilitiesDB = new DatabaseLiability(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liabilities_table);
        getSupportActionBar().setTitle("Select Liabilities");


        //method to set the ids
        setIDsforViews();
    }

    public void setIDsforViews(){

        mortgageOneAmountTV = findViewById(R.id.mortgageOneAmountTV);
        mortgageTwoAmountTV = findViewById(R.id.mortgageTwoAmountTV);
        investmentLoanAmountTV = findViewById(R.id.investmentLoanAmountTV);
        studentLoanAmountTV = findViewById(R.id.studentLoanAmountTV);
        carLoanAmountTV = findViewById(R.id.carLoanAmountTV);
        lineOfCreditAmountTV = findViewById(R.id.lineOfCreditAmountTV);
        creditcardTwoAmountTV = findViewById(R.id.creditcardTwoAmountTV);
        creditcardOneAmountTV = findViewById(R.id.creditcardOneAmountTV);

        mortgageOneAmount = findViewById(R.id.mortgageOneAmount);
        mortgageTwoAmount= findViewById(R.id.mortgageTwoAmount);
        investmentLoanAmount = findViewById(R.id.investmentLoanAmount);
        studentLoanAmount = findViewById(R.id.studentLoanAmount);
        carLoanAmount = findViewById(R.id.carLoanAmount);
        lineOfCreditAmount = findViewById(R.id.lineOfCreditAmount);
        creditcardTwoAmount = findViewById(R.id.creditcardTwoAmount);
        creditcardOneAmount = findViewById(R.id.creditcardOneAmount);
//        assetsTable = findViewById(R.id.selectedAssetTable);

    }

    //function to check which checkbox is clicked
    //in this fuction value of textview and edittext is extracted and then sent to the function to store the values in database

    public void onLiabilityCheckboxClicked(View view) {

        boolean liabilityBoxChecked = ((CheckBox) view).isChecked(); // variable to check if the checkbox is checked or not

        switch (view.getId()){

            case R.id.checkbox_creditCard1:
                checkedLiability = findViewById(R.id.checkbox_creditCard1);
                creditcardOne = creditcardOneAmount.getText().toString();
                addOrRemoveLiabilityInMaps(checkedLiability, liabilityBoxChecked,creditcardOneAmountTV.getText().toString(), creditcardOne);
            break;

            case R.id.checkbox_creditCard2:
                checkedLiability = findViewById(R.id.checkbox_creditCard2);
                creditcardTwo = creditcardTwoAmount.getText().toString();
                addOrRemoveLiabilityInMaps(checkedLiability,liabilityBoxChecked,creditcardTwoAmountTV.getText().toString(), creditcardTwo);
                break;

            case R.id.checkbox_mortgageOne:
                checkedLiability = findViewById(R.id.checkbox_mortgageOne);
                mortgageOne = mortgageOneAmount.getText().toString();
                addOrRemoveLiabilityInMaps(checkedLiability,liabilityBoxChecked,mortgageOneAmountTV.getText().toString(), mortgageOne);
                break;

            case R.id.checkbox_mortgageTwo:
                checkedLiability = findViewById(R.id.checkbox_mortgageTwo);
                mortgageTwo = mortgageTwoAmount.getText().toString();
                addOrRemoveLiabilityInMaps(checkedLiability,liabilityBoxChecked,mortgageTwoAmountTV.getText().toString(), mortgageTwo);
                break;

            case R.id.checkbox_LOC:
                checkedLiability = findViewById(R.id.checkbox_LOC);
                lineOfCredit = lineOfCreditAmount.getText().toString();
                addOrRemoveLiabilityInMaps(checkedLiability,liabilityBoxChecked,lineOfCreditAmountTV.getText().toString(), lineOfCredit);
                break;

            case R.id.checkbox_InvestmentLoan:
                checkedLiability = findViewById(R.id.checkbox_InvestmentLoan);
                investmentLoan = investmentLoanAmount.getText().toString();
                addOrRemoveLiabilityInMaps(checkedLiability,liabilityBoxChecked,investmentLoanAmountTV.getText().toString(), investmentLoan);
                break;

            case R.id.checkbox_studentLoanAmount:
                checkedLiability = findViewById(R.id.checkbox_studentLoanAmount);
                studentLoan = studentLoanAmount.getText().toString();
                addOrRemoveLiabilityInMaps(checkedLiability,liabilityBoxChecked,studentLoanAmountTV.getText().toString(), studentLoan);
                break;

            case R.id.checkbox_carLoan:
                checkedLiability = findViewById(R.id.checkbox_carLoan);
                carLoan= carLoanAmount.getText().toString();
                addOrRemoveLiabilityInMaps(checkedLiability,liabilityBoxChecked,carLoanAmountTV.getText().toString(), carLoan);
                break;


        }


    }

    //function to add liability in database

    public void addOrRemoveLiabilityInMaps(final CheckBox liabilityBox, boolean liabilityBoxChecked, String liabilityNameTV, String liabilityValueET){

        if (liabilityBoxChecked){

            if(!liabilityValueET.isEmpty() && countDecimals(liabilityValueET)){
                // assigningAsset.addAssets(assetNameTV, assetValueET);
                liabilitiesDB.addLiability(liabilityNameTV, liabilityValueET);
                editBoxEmpty = false;
//                int size1 = assigningAsset.assetNameList.size();
//                String size2 = size1.toString();
                //Toast.makeText(AssetsTable.this,String.valueOf(assigningAsset.assetNameList.size()) , Toast.LENGTH_SHORT).show();

            }

            else{
                liabilityBox.setChecked(false);
                Toast.makeText(this, "Enter the correct value", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "CheckBox is unchecked", Toast.LENGTH_SHORT).show();
        }


    }

    //function to check if there is more than one decimal point

    public boolean countDecimals(String liabilityValue){

        int count = 0;
        for(int i = 0; i < liabilityValue.length(); i++){

            if(liabilityValue.charAt(i) == '.'){
                count++;
                if(count > 1)
                    return false;

            }

        }
        return true;
    }

    //to inflate the save button

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actionbar_save_menu,menu);
        return true;
    }

    /*
        1. to handle the click event on the menu that is on the action bar
        2. By clicking on the save button the user will go back to the AddAssetsLiabilities class that will display the selected assets*/

    //handling the event on pressing the save button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.save:
//                AddAssetsLiabilities assets = new AddAssetsLiabilities();
//                RecyclerViewAdapter createAssetTable = new RecyclerViewAdapter(assets);
//                assets.assetsTable.setAdapter(createAssetTable);
//                final LinearLayoutManager layoutManager = new LinearLayoutManager(assets);
//                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                assets.assetsTable.setLayoutManager(layoutManager);
//                RecyclerViewAdapter createAssetTable = new RecyclerViewAdapter(addingAssets, assigningAsset);
//                assetsTable.setAdapter(createAssetTable);
//                assetsTable.setLayoutManager(new LinearLayoutManager(this));
                    if(liabilitiesDB.liabilityCount() == 0) //checking if the db is empty or ot or checking if user has selected any value or not
                    {
                        Toast.makeText(this, "Fill the fields", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent back = new Intent(this, AddAssetsLiabilities.class); //going back to the AssetsLiabilitiesClass
                        String save = "Liabilities";
                        back.putExtra("saved", save);
                        startActivity(back);
                    }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //to disable back button

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if(liabilitiesDB.liabilityCount() == 0)
            {
                Toast.makeText(this, "Fill the fields", Toast.LENGTH_SHORT).show();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
