package com.example.networthcalculator;

//class that adds all the assets and liabilities and calculates the networth

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Double.parseDouble;

public class CalculateNetWorth implements Serializable{

//    Map assetMap = new HashMap<>();
//    Map liabilitiesMap = new HashMap<>();

    List<String> assets = new ArrayList<>();
    List<String> liabilities = new ArrayList<>();
    double totalAssets = 0.0;
    double totalLiabilities = 0.0;
    double totalNetWorth = 0.0;
    String name;

    //getting the values from AddAssetsLiabilities

    public CalculateNetWorth(List<String> assetsValues, List<String> liabilitiesValues, String userName){
        assets = assetsValues;
        liabilities = liabilitiesValues;
        name = userName;

    }

    //Adding all the assets


    private void addAssets(){
        for (int i = 0 ; i < assets.size(); i++){
            double a = parseDouble(assets.get(i));
            totalAssets = totalAssets + a;
        }

    }
    //adding all the liabilities

    private void addLiabilities(){

        for (int i = 0 ; i < liabilities.size(); i++){
            double a = parseDouble(liabilities.get(i));
            totalLiabilities = totalLiabilities + a;
        }
    }

//    public void removeAsset(String assetName){
//        assetMap.remove(assetName);
//
//    }
//
//    public void removeLiability(String liabilityName){
//        liabilitiesMap.remove(liabilityName);
//    }


    //calculating the networth

    public CalculateNetWorth calculateNetWorth(){

        addAssets();
        addLiabilities();

        totalNetWorth = totalAssets - totalLiabilities;
        return this; // passing the refernce of the object

    }
}


