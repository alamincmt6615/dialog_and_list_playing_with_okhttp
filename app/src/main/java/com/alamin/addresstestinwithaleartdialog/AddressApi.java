package com.alamin.addresstestinwithaleartdialog;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddressApi extends AsyncTask<String, Void, ArrayList<AddressHelperModel>> {
    ArrayList<AddressHelperModel> houseList = new ArrayList<AddressHelperModel>();
    ZipCallBack callBack;
    String str;

    public AddressApi(String str, ZipCallBack callBack){
        this.str = str;
        this.callBack = callBack;
    }
    @Override
    protected ArrayList<AddressHelperModel> doInBackground(String... strings) {
        return getHouseByZip(str);
    }
    public  ArrayList<AddressHelperModel> getHouseByZip(String str){
        Log.d("aaaaaa", "zip: "+str);
        OkHttpClient client = new OkHttpClient();
        ArrayList<AddressHelperModel> houseList  = new ArrayList<>();
        Request request = new Request.Builder()
                .url("https://labapi.yuma-technology.co.uk:8443/delivery/connector/postal_data/"+str)
                .addHeader("Content-Type","application/json")
                .addHeader("AdminSession", "03683bdc-00a1-4d6d-b344-c70d937f7168")
                .build();
        Response response = null;
        try{
            response = client.newCall(request).execute();
            Log.d("aaaaaaaaa", "zip: "+response.code());
            if(response.code()==200){
                String jsonData = response.body().string();
                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i= 0;i<jsonArray.length();i++){
                    AddressHelperModel obj = AddressHelperModel.fromJSON(jsonArray.optJSONObject(i));
                    houseList.add(obj);
                }
            }
        }catch (Exception e){
            Log.e("aaaaaa", "getZipSuggestion: "+e );
            e.printStackTrace();
        }
        this.houseList = houseList;
        return houseList;
    }

    @Override
    protected void onPostExecute(ArrayList<AddressHelperModel> arrayList) {
        callBack.onAddressResponse(houseList);
        super.onPostExecute(arrayList);
    }
}
