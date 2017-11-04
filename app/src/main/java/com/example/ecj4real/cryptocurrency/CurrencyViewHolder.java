package com.example.ecj4real.cryptocurrency;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 *
 * Created by ECJ4REAL on 10/28/2017.
 */

class CurrencyViewHolder extends RecyclerView.ViewHolder {
    TextView firstCurrency;
    TextView secondCurrency;

    CurrencyViewHolder(View v){
        super(v);
        firstCurrency = (TextView) v.findViewById(R.id.first_currency_text);
        secondCurrency = (TextView) v.findViewById(R.id.second_currency_text);

    }
}
