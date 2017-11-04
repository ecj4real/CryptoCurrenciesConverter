package com.example.ecj4real.cryptocurrency;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Currency> currenciesList = new ArrayList<>();
    private CurrencyAdapter ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        ca = new CurrencyAdapter(currenciesList);
        recList.setAdapter(ca);

        recList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recList ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        Intent intent =  new Intent(MainActivity.this, ConverterActivity.class);
                        intent.putExtra("card", currenciesList.get(position));
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int selectedMenuItem = item.getItemId();
        if(selectedMenuItem == R.id.action_add){
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.custom_dialog);
            dialog.setTitle("Add Card");

            Button dialogCancel = (Button) dialog.findViewById(R.id.cancel_button);

            dialogCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            Button dialogOk = (Button) dialog.findViewById(R.id.ok_button);
            final Spinner cryptoSpin = (Spinner) dialog.findViewById(R.id.cryto_currencies);
            final Spinner worldSpin = (Spinner) dialog.findViewById(R.id.world_currencies);

            dialogOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String cryptoText = cryptoSpin.getSelectedItem().toString();
                    String worldText = worldSpin.getSelectedItem().toString();
                    currenciesList.add(new Currency(cryptoText, worldText));
                    ca.notifyItemInserted(currenciesList.size() - 1);
                    dialog.dismiss();
                }
            });

            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
