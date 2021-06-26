package com.alamin.addresstestinwithaleartdialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ZipCodeHelper extends AsyncTask<String, Void, ArrayList> {
    Context context;
    static ArrayList zipCodeList = new ArrayList();
    ZipCallBack callBack;
    String str;
    public ZipCodeHelper(Context context, String str, ZipCallBack callBack){
        this.context = context;
        this.str = str;
        this.callBack = callBack;
    }

    @Override
    protected ArrayList doInBackground(String... strings) {
        return getZipSuggestion(context,str);
    }
    public static  ArrayList getZipSuggestion(Context context, String str){
        Log.d("mmmm", "getZipSuggestion: "+str);
        OkHttpClient client = new OkHttpClient();
        ArrayList<String> zipList  = new ArrayList<>();
        Request request = new Request.Builder()
                .url("https://labapi.yuma-technology.co.uk:8443/delivery/connector/postal_code/"+str)
                .addHeader("Content-Type","application/json")
                .addHeader("AdminSession","03683bdc-00a1-4d6d-b344-c70d937f7168")
                .build();
        Response response = null;
        try{
            response = client.newCall(request).execute();
            Log.d("mmmm", "getZipSuggestion: "+response.code());
            if(response.code()==200){
                String jsonData = response.body().string();
                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i= 0;i<jsonArray.length();i++){
                    zipList.add(jsonArray.optString(i));
                }
            }
        }catch (Exception e){
            Log.e("mmmmm", "getZipSuggestion: "+e );
            e.printStackTrace();
        }
        zipCodeList = zipList;
        return zipCodeList;
    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        callBack.onResponseFromServer(zipCodeList);
        super.onPostExecute(arrayList);
    }
}
