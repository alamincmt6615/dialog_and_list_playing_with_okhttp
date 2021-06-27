package com.alamin.addresstestinwithaleartdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.interfaces.JsCallback;

import java.util.ArrayList;

import static com.alamin.addresstestinwithaleartdialog.ZipCodeHelper.isDataLoad;

public class MainActivity extends AppCompatActivity implements ZipCallBack, AdapterView.OnItemClickListener{
    public EditText city,houseNumber;
    public Button saveBtn,addressSearch;
    JsEvaluator jsEvaluator;
    ArrayAdapter arrayAdapter,houseAdapter;
    ArrayList<String> zipList = new ArrayList<String>();
    ArrayList<AddressHelperModel> houseList = new ArrayList<AddressHelperModel>();
    String preStr="";
    public AutoCompleteTextView zip,street;
    public Spinner addressSelect;
    TextView customer_info;
    private PostalCodeListAdapter postalCodeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsEvaluator = new JsEvaluator(MainActivity.this);
        city = findViewById(R.id.customerInputCity);
        zip = findViewById(R.id.customerInputZip);
        street = findViewById(R.id.customerInputStreet);
        houseNumber = findViewById(R.id.customerInputCountry);
        addressSelect = findViewById(R.id.addressSelect);
        addressSearch = findViewById(R.id.addressSearch);
        customer_info = findViewById(R.id.customer_info);
        customer_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog();

            }
        });

        arrayAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line, zipList);
        zip.setAdapter(arrayAdapter);
        zip.setOnItemClickListener(this);
        zip.setThreshold(1);

       /* addressSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadHouse();
            }
        });*/

        /*zip.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()==3 && !preStr.equals(s.toString())){
                    preStr = s.toString();
                    callFormatPostalCode(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("mmmmm", "afterTextChanged: called");

            }
        });

        */
        /*zip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadHouse();
                Log.d(" selected",String.valueOf(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
Log.d("nothid select","vaia");
            }
        });*/



    }

    public void OpenDialog()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView= inflater.inflate(R.layout.customer_dialog_info, null);
        dialogBuilder.setView(dialogView);

        AutoCompleteTextView d_zip =(AutoCompleteTextView)dialogView.findViewById(R.id.d_customerInputZip);
        Button button = (Button)dialogView.findViewById(R.id.customerSave);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "alamin", Toast.LENGTH_SHORT).show();

            }
        });
        arrayAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line, zipList);
        d_zip.setAdapter(postalCodeListAdapter);
        d_zip.setOnItemClickListener(this);
        d_zip.setThreshold(1);
        d_zip.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()==3 && !preStr.equals(s.toString())){
                    preStr = s.toString();
                    loadData(s.toString());
                    //callFormatPostalCode(s.toString());
                    if (isDataLoad) {
                       // arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, zipList);
                       // d_zip.setAdapter(arrayAdapter);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("aaaaaaaaaa", "afterTextChanged: called");
            }
        });

        if (isDataLoad) {
            d_zip.setAdapter(postalCodeListAdapter);
            //arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, zipList);
            //d_zip.setAdapter(arrayAdapter);
        }

        dialogBuilder.create().show();

      /*
        final Dialog dialog = new Dialog(getApplicationContext());
        dialog.setContentView(R.layout.customer_dialog_info);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        JsEvaluator jsEvaluator;
        ArrayAdapter arrayAdapter,houseAdapter;
        ArrayList<String> zipList = new ArrayList<String>();
        ArrayList<AddressHelperModel> houseList = new ArrayList<AddressHelperModel>();
        String preStr="";

        jsEvaluator = new JsEvaluator(MainActivity.this);


        addressSelect = findViewById(R.id.addressSelect);
        addressSearch = findViewById(R.id.addressSearch);


        final EditText city  = (EditText) dialog.findViewById(R.id.customerInputCity);
        final EditText houseNumber = (EditText) dialog.findViewById(R.id.customerInputCountry);
        final Button saveBtn  = (Button) dialog.findViewById(R.id.dialog_success_check);
        final Button addressSearch  = (Button) dialog.findViewById(R.id.dialog_success_check);
        final AutoCompleteTextView zip  = (AutoCompleteTextView) dialog.findViewById(R.id.customerInputZip);
        final AutoCompleteTextView street  = (AutoCompleteTextView) dialog.findViewById(R.id.customerInputStreet);
        final Spinner addressSelectSpinner  = (Spinner) dialog.findViewById(R.id.dialog_success_check);

        final ImageView successCheckAnimation = (ImageView) dialog.findViewById(R.id.dialog_success_check);
        final TextView dialog_success_text = (TextView) dialog.findViewById(R.id.dialog_success_text);
        final Button dialog_success_btn = (Button) dialog.findViewById(R.id.dialog_success_btn);

        ((Animatable) successCheckAnimation.getDrawable()).start();
        dialog_success_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
        dialog.getWindow().setAttributes(lp);


        new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.animated_vector_check)
                .setTitle("£ "+amount+" has been added")
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
        */
    }

    private void loadHouse(){
        Log.d("ppppp", "loadHouse: calling");
        new AddressApi(zip.getText().toString(),this).execute();

    }

    void callFormatPostalCode(String str){ jsEvaluator.callFunction("function myFunction(a) { return a.replace(/(\\S*)\\s*(\\d)/, \"$1 $2\"); }",
                new JsCallback() {
                    @Override
                    public void onResult(String s) {
                        String abc = s;
                        if(s.replaceAll(" ","").length() >3){
                            loadData(s);
                        }
                        else{
                            //loadData(zip.getText().toString());
                            loadData(abc);

                        }


                    }
                    @Override
                    public void onError(String s) {

                    }
                },
                "myFunction", str
        );
    }

    void loadData(String str){
        new ZipCodeHelper(MainActivity.this,str, (ZipCallBack) this).execute();;
    }
    @Override
    public void onResponseFromServer(ArrayList<String> str) {
        if(str.size() != 0){
            zipList = str;
        }
        postalCodeListAdapter = new PostalCodeListAdapter(MainActivity.this,R.layout.postalcode_item,zipList);

       /* arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, zipList);
        zip.setAdapter(arrayAdapter);*/
    }
    @Override
    public void onAddressResponse(ArrayList<AddressHelperModel> data) {
        houseList = data;
        try{
            houseAdapter = new AddressListAdapter(MainActivity.this, android.R.layout.simple_dropdown_item_1line, houseList);
            addressSelect.setAdapter(houseAdapter);
            addressSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    AddressHelperModel model =  houseList.get(position);
                    if (model !=null) {
                        if (model.streetName1 != null && model.streetName2 != null)
                            street.setText(model.streetName1 + " " + model.streetName2);
                        city.setText(model.town);
                        houseNumber.setText(model.buildingNumber);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        loadHouse();
        //Toast.makeText(this, "alamin "+position, Toast.LENGTH_SHORT).show();
    }
}