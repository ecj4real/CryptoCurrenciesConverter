package com.example.ecj4real.cryptocurrency;

/**
 *
 * Created by ECJ4REAL on 10/28/2017.
 */
import java.io.Serializable;

class Currency implements Serializable{
    String firstCurrency;
    String secondCurrency;


    Currency(String first, String second){
        firstCurrency = first;
        secondCurrency = second;
    }


}
