package com.application.chawlanicershop;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MenuActivity.class.getSimpleName();

    //a list to store all the products
    List<Salad> saladList;

    //the recyclerview
    RecyclerView recyclerView;
    public double total,subtotal,tax;
    SaladAdapter saladAdapter;
    public static TextView tv_total,tv_subtotal,tv_tax;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        saladList = new ArrayList<>();


        Log.d(LOG_TAG,"define the salad list");
        //adding some items to our list
        saladList.add(
                new Salad(
                        1,
                        getString(R.string.salad1_title),
                        getString(R.string.salad1_description),
                        20.99,
                        R.drawable.sesame_salad));

        saladList.add(
                new Salad(
                        2,
                        getString(R.string.salad2_title),
                        getString(R.string.salad2_description),
                        11.49,
                        R.drawable.asian_salad));

        saladList.add(
                new Salad(
                        3,
                        getString(R.string.salad3_title),
                        getString(R.string.salad3_description),
                        14.49,
                        R.drawable.caesar_salad));

        saladList.add(
                new Salad(
                        4,
                        getString(R.string.salad4_title),
                        getString(R.string.salad4_description),
                        13.99,
                        R.drawable.harvest_salad));

        saladList.add(
                new Salad(
                        5,
                        getString(R.string.salad5_title),
                        getString(R.string.salad5_description),
                        13.99,
                        R.drawable.tokyo_salad));

        saladList.add(
                new Salad(
                        6,
                        getString(R.string.salad6_title),
                        getString(R.string.salad6_description),
                        15.49,
                        R.drawable.cobb_salad));

        saladList.add(
                new Salad(
                        7,
                        getString(R.string.salad7_title),
                        getString(R.string.salad7_description),
                        15.99,
                        R.drawable.endless_summer_salad));

        saladList.add(
                new Salad(
                        8,
                        getString(R.string.salad8_title),
                        getString(R.string.salad8_description),
                        13.99,
                        R.drawable.habibi_salad));

        saladList.add(
                new Salad(
                        9,
                        getString(R.string.salad9_title),
                        getString(R.string.salad9_description),
                        14.49,
                        R.drawable.the_wolfe_salad));

        saladList.add(
                new Salad(
                        10,
                        getString(R.string.salad10_title),
                        getString(R.string.salad10_description),
                        11.49,
                        R.drawable.the_clean_green_salad));

        //creating recyclerview adapter
        SaladAdapter adapter = new SaladAdapter(this, saladList);
        Log.d(LOG_TAG,"Setting adapter to recyclerview ");
        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
        adapter.registerAdapterDataObserver(observer);

    }
    RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {

        @Override
        public void onChanged() {
            super.onChanged();
            calculateTotal();
        }
    };


    public double calculateTotal() {
        Log.d(LOG_TAG,"calculate subtotal");
        int i = 0;
        total=0;
        tax=0;
        subtotal=0;
        while(i < saladList.size()){
            if(saladList.get(i).getQuantity()>0) {
                subtotal = subtotal + (saladList.get(i).getPrice() * Integer.valueOf(saladList.get(i).getQuantity()));
                Log.d(LOG_TAG,"now subtotal is:"+subtotal);
            }
            i++;

        }


//        //find the view
//        tv_subtotal =(TextView) findViewById(R.id.non_taxTotal);
//        tv_tax=(TextView) findViewById(R.id.tax);
//        tv_total =(TextView) findViewById(R.id.tv_total);
       Button btn = (Button) findViewById(R.id.btn_placeorder);
//        //setting the value
//        tv_subtotal.setText("Price Before Tax :"+df2.format(subtotal)+" "+"CAD");
//        tv_tax.setText("Tax : GST: "+df2.format(tax.get("GST"))+" "+"CAD, QST: "+df2.format(tax.get("QST"))+" "+"CAD");
//        tv_total.setText("Price After Tax :"+df2.format(subtotal+tax.get("GST")+tax.get("QST"))+" "+"CAD");

        //enable the button and disable when
        //no product is added for order
        Log.d(LOG_TAG,"set property to enable/disable place order button");
        if(subtotal>0) {
            btn.setEnabled(true);
            btn.setBackgroundColor(Color.GREEN);
        }
        else{
            btn.setEnabled(false);
            btn.setBackgroundColor(Color.GRAY);
        }
        return subtotal;
    }

    private Map<String,Double> taxCalculation(double total){
        Log.d(LOG_TAG,"function to calculate tax");
        //gst 5% and qst 9.975%
        double gst=total*0.05;
        double qst=total*0.09975;
        HashMap<String,Double> tax=new HashMap<String,Double>();
        tax.put("GST",gst);
        tax.put("QST",qst);
        return tax;
    }
    public void insertOrder(View view) {
        Log.d(LOG_TAG,"function to call checkoutActivity ");
        Intent intent = new Intent(this, CheckOutActivity.class);
        double subtotal=calculateTotal();
        Map<String,Double> tax=taxCalculation(subtotal);
        intent.putExtra("SubTotal",df2.format(subtotal));
        intent.putExtra("Tax_GST",df2.format(tax.get("GST")));
        intent.putExtra("Tax_QST",df2.format(tax.get("QST")));
        intent.putExtra("Total",df2.format(subtotal+tax.get("GST")+tax.get("QST")));
        startActivity(intent);
    }
}
