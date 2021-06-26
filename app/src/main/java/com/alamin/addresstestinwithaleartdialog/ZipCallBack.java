package com.alamin.addresstestinwithaleartdialog;
import java.util.ArrayList;
public interface ZipCallBack {
    void onResponseFromServer(ArrayList<String> str);
    void onAddressResponse(ArrayList<AddressHelperModel> data);
}
