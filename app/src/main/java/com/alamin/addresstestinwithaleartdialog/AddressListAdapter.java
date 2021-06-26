package com.alamin.addresstestinwithaleartdialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddressListAdapter extends ArrayAdapter<AddressHelperModel>{

    private Context context;
    private ArrayList<AddressHelperModel> addressHelperModelArrayList;
    LayoutInflater inflater;

    public AddressListAdapter(Context context, int textViewResourceId, ArrayList<AddressHelperModel> addressHelperModelArrayList) {
        super(context, textViewResourceId, addressHelperModelArrayList);
        this.context = context;
        this.addressHelperModelArrayList = addressHelperModelArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        TextView label = row.findViewById(android.R.id.text1);
        if (position == 0) {
            label.setText("Please select house number");
        } else {
            label.setText(addressHelperModelArrayList.get(position).buildingNumber + " " + addressHelperModelArrayList.get(position).buildingName + " " + addressHelperModelArrayList.get(position).streetName1 + " " + addressHelperModelArrayList.get(position).streetName2);
        }
        return row;
    }
}
