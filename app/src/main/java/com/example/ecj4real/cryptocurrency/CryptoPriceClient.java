package com.example.ecj4real.cryptocurrency;

/**
 *
 * Created by ECJ4REAL on 11/4/2017.
 */
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

class CryptoPriceClient {
    private static final String API_BASE_URL = "https://min-api.cryptocompare.com/data/price?";
    private AsyncHttpClient client;
//    public static String currency;

    CryptoPriceClient() {
        this.client = new AsyncHttpClient();
//        this.currency = currency;
    }
    private String getUrl(String crypto, String currency, String url){
        return  url + "fsym=" + crypto + "&tsyms=" +  currency;
    }

    // Method for accessing the CrytoCurrency API
    void getCrypto(final String bitCurrency, final String regCurrency, JsonHttpResponseHandler handler) {
        try {
            String url = API_BASE_URL;
            url = getUrl(bitCurrency, regCurrency, url);
            client.setUserAgent("UBrowser/7.0.6.1042");
            client.get(url, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
