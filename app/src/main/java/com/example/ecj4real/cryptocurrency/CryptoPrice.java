package com.example.ecj4real.cryptocurrency;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 *
 * Created by ECJ4REAL on 11/4/2017.
 */

class CryptoPrice implements Serializable {
    private String price;
    private static String currency;

    String getCrptoPrice() {
        return price;
    }
    private CryptoPrice(String currency){
        CryptoPrice.currency = currency;
    }


    static CryptoPrice fromJson(JSONObject jsonObject, String curr) {
        CryptoPrice cryptoPrice = new CryptoPrice(curr);
        try {
            cryptoPrice.price = jsonObject.getString(currency);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return cryptoPrice;
    }
}
