package com.example.ecj4real.cryptocurrency;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONObject;
import java.math.BigDecimal;
import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

/**
 * ConverterActivity.java
 * Created by ECJ4REAL on 11/4/2017.
 */

public class ConverterActivity extends AppCompatActivity {
    private TextView converterCryptoCurrency;
    private EditText amountToConvert;
    private TextView resultOfConversion;
    private TextView resultValue;
    private CryptoPrice crypto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion_screen);

        converterCryptoCurrency = (TextView) findViewById(R.id.converter_cryto_currency);
        amountToConvert = (EditText) findViewById(R.id.amount_to_convert);
        resultOfConversion = (TextView) findViewById(R.id.converted_result);
        resultValue = (TextView) findViewById(R.id.result_value);

        Currency currentCurrency = (Currency) getIntent().getSerializableExtra("card");
        loadCurrency(currentCurrency);
    }

    public void loadCurrency(Currency myCurrency){
        converterCryptoCurrency.setText(myCurrency.firstCurrency);
        resultOfConversion.setText(myCurrency.secondCurrency);
    }

    public void convertAmount(View view){
        if(amountToConvert.getText().toString().equals(""))
        {
            Toast.makeText(this, "Please Enter an Amount", Toast.LENGTH_SHORT).show();
        }
        else
        {
            CryptoPriceClient client = new CryptoPriceClient();
            final String crytoText = "" + converterCryptoCurrency.getText();
            final String worldText = "" + resultOfConversion.getText();
            final double amount = Double.parseDouble("" + amountToConvert.getText());
            client.getCrypto(crytoText, worldText, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    if(response != null) {
                        crypto = CryptoPrice.fromJson(response, worldText);
                        String cAmount = "";
                        try{
                            assert crypto != null;
                            cAmount = crypto.getCrptoPrice();
                        }
                        catch(NullPointerException e)
                        {
                            e.printStackTrace();
                        }
                        double convertedAmount = 0.00;
                        try {
                            convertedAmount = Double.parseDouble(cAmount);
                        } catch(NumberFormatException nfe) {
                            Log.d("error", "a  number was not entered");
                        }
                        convertedAmount = convertedAmount *  amount;
                        String resultAmount = String.valueOf(convertedAmount);
                        BigDecimal b = new BigDecimal(resultAmount);
                        BigDecimal roundOff = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                        resultAmount = "" + roundOff;
                        resultValue.setText(resultAmount);
                        Log.i("response", crypto.getCrptoPrice());
                        Log.i("response", resultAmount + "");

                    }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.d("Failed: ", ""+statusCode);
                    Log.d("Error : ", "" + throwable);
                    log.d("failure:", "" + responseString);
                }
            });
        }
    }

}
