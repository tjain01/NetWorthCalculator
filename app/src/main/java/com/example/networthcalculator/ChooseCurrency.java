package com.example.networthcalculator;

// class for creating a currency dialog box that will allow the user to select a currency

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ChooseCurrency extends AppCompatActivity {

    String currencyName;
    static String currencyFromList;

    void createDialogBoxForCurrency(Context context, final TextView currencyReference){

        final ListView currency = new ListView(context);

        //creating an arraylist for currencies
        List<String> currencyList = new ArrayList<>(); // currency arrayList
        currencyList.add("CAD");
        currencyList.add("USA");
        currencyList.add("INR");
        currencyList.add("EUR");
        currencyList.add("AUD");
        currencyList.add("YEN");
        currencyList.add("BRL");
        currencyList.add("CUP");
        currencyList.add("CNY");
        currencyList.add("NZD");
        currencyList.add("PLN");

        //adding array adapter

        final ArrayAdapter<String> currencyListAdapter = new ArrayAdapter<String>(context, R.layout.currency_dialog, currencyList);
        currency.setAdapter(currencyListAdapter);

        //appending the listview to the dialog box

        final AlertDialog.Builder currencyDialogBox = new AlertDialog.Builder(context);
        currencyDialogBox.setCancelable(true);
        currencyDialogBox.setView(currency);
        final AlertDialog currencyAlertDialog = currencyDialogBox.create();
        currencyAlertDialog.setCanceledOnTouchOutside(true);
        currencyAlertDialog.show();

        // setting the event on the selected currency

        currency.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                currencyFromList = currencyListAdapter.getItem(position);

                currencyReference.setText(currencyFromList);
                currencyAlertDialog.dismiss();

            }
        });



    }
}
