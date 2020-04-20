package com.example.networthcalculator;

/*
    *Class to display the data on recyclerview */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.AssetLiabilityViewHolder> implements Serializable {

    Context assetOrLiabilityContext; //getting the context of AddAssetsLiabilities
    CalculateNetWorth createAssetOrLiabilityTable ;

    //getting the values of list from AddAssetsLiabilitiesClass
    List<String> assetOrLiabilityNameList = new ArrayList<>();
    List<String> assetOrLiabilityValueList = new ArrayList<>();

    //Constructor to assign values

    public RecyclerViewAdapter(Context context, ArrayList<String> assetOrLiabilityName, ArrayList<String> assetOrLiabilityValue)
    {
        assetOrLiabilityContext = context;
        assetOrLiabilityNameList = assetOrLiabilityName;
        assetOrLiabilityValueList = assetOrLiabilityValue;
      //  createAssetOrLiabilityTable = assignedAsset;
    }

    //Inflating the Views

    @NonNull
    @Override
    public AssetLiabilityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(assetOrLiabilityContext);
        View view = inflater.inflate(R.layout.selected_asset_liability_row, parent, false);
        return new AssetLiabilityViewHolder(view);
    }

    //Setting values and names of assets and liabilities on the recyclerview
    @Override
    public void onBindViewHolder(@NonNull AssetLiabilityViewHolder holder, int position) {


            holder.assetOrLiabilityname.setText(assetOrLiabilityNameList.get(position));
            holder.assetOrLiabilityValue.setText(assetOrLiabilityValueList.get(position));


    }

    //returning the size of the list

    @Override
    public int getItemCount() {
                return assetOrLiabilityNameList.size();

    }

    //setting the ids for views used to create a recyclerview

    public class AssetLiabilityViewHolder extends RecyclerView.ViewHolder implements Serializable {

        TextView assetOrLiabilityname, assetOrLiabilityValue;
        public AssetLiabilityViewHolder(@NonNull View itemView) {

            super(itemView);

            assetOrLiabilityname = itemView.findViewById(R.id.assetLiabilityName);
            assetOrLiabilityValue = itemView.findViewById(R.id.assetLiabilityValue);

        }
    }
}
