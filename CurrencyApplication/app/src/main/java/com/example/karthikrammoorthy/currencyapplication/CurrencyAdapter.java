package com.example.karthikrammoorthy.currencyapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by karthikrammoorthy on 08/03/18.
 */

public class CurrencyAdapter extends ArrayAdapter<CurrencyItem> {

private ArrayList<CurrencyItem> currencyList;

public CurrencyAdapter(Context context, int textViewResourceID, ArrayList<CurrencyItem> currencyList) {
    super(context, textViewResourceID, currencyList);

    this.currencyList = currencyList;
}


public View getView(int position, View convertView, ViewGroup parent){

    View v = convertView;

    if(v == null) {
        LayoutInflater inflator = (LayoutInflater) getContext()
                                  .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflator.inflate(R.layout.list_item, null);

    }

    CurrencyItem i = currencyList.get(position);

    if(i != null) {

        TextView tvBase = v.findViewById(R.id.tvBase);
        TextView tvValue = v.findViewById(R.id.tvValue);

        tvBase.setText(i.getBase());
        tvValue.setText(String.valueOf(i.getValue()));

    }

    return v;

}

}
