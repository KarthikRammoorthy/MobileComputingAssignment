package com.example.karthikrammoorthy.currencyapplication;

/**
 * Created by karthikrammoorthy on 08/03/18.
 */

public class CurrencyItem {

    private String base;
    private Double value;

    public CurrencyItem(String base, Double value){

        this.base = base;
        this.value = value;
    }


    public String getBase() { return  this.base; }
    public Double getValue() { return  this.value; }


}
