package com.alamin.addresstestinwithaleartdialog;

import org.json.JSONObject;

public class AddressHelperModel {
    public String postcode,town,buildingNumber,buildingName,streetName1,streetName2;
    public AddressHelperModel(String postcode, String town, String buildingNumber, String buildingName, String streetName1,String streetName2) {
        this.postcode = postcode;
        this.town = town;
        this.buildingNumber = buildingNumber;
        this.buildingName = buildingName;
        this.streetName1 = streetName1;
        this.streetName2 = streetName2;
    }

    public static AddressHelperModel fromJSON(JSONObject jsonObject){
        return new AddressHelperModel(
                jsonObject.optString("postcode",""),
                jsonObject.optString("town",""),
                jsonObject.optString("buildingNumber",""),
                jsonObject.optString("buildingName",""),
                jsonObject.optString("thoroughfare",""),
                jsonObject.optString("dependentThoroughfare","")

        );
    }
}
