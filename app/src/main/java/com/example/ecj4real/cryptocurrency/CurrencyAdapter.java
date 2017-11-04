package com.example.ecj4real.cryptocurrency;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 *
 * Created by ECJ4REAL on 10/28/2017.
 */

class CurrencyAdapter extends RecyclerView.Adapter<CurrencyViewHolder> {
    private List<Currency> currencyList;

    CurrencyAdapter(List<Currency> currencyList){
        this.currencyList = currencyList;
    }

    @Override
    public int getItemCount(){
        return currencyList.size();
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder currencyViewHolder, int i){
        Currency ci = currencyList.get(i);
        currencyViewHolder.firstCurrency.setText(ci.firstCurrency);
        currencyViewHolder.secondCurrency.setText(ci.secondCurrency);
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_list_view, viewGroup, false);

        return new CurrencyViewHolder(itemView);
    }
}
