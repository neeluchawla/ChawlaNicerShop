package com.application.chawlanicershop;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FloatingActionActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MenuActivity.class.getSimpleName();

    //a list to store all the products
    List<Salad> saladList;

    //the recyclerview
    RecyclerView recyclerView;
    public double total,subtotal,tax,shipping_cost;
    SaladAdapter saladAdapter;
    public static TextView tv_total,tv_subtotal,tv_tax;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double subtotal = calculateTotal();
                if (subtotal <= 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.no_product_selected_message),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(LOG_TAG, "function to call checkoutActivity ");
                    AlertDialog.Builder myAlertBuilder = new
                            AlertDialog.Builder(FloatingActionActivity.this);
                    myAlertBuilder.setTitle(getString(R.string.shipping_alert));
                    String[] delivery_options = {getString(R.string.delivery1), getString(R.string.delivery2), getString(R.string.delivery3)};
                    int checkedItem = 2; //
                    myAlertBuilder.setSingleChoiceItems(delivery_options, checkedItem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            switch (item) {
                                case 0:
                                    shipping_cost = 50;
                                    break;
                                case 1:
                                    shipping_cost = 10;
                                    break;
                                case 3:
                                    shipping_cost = 0;
                                    break;
                                default:
                                    break;
                            }
                            Log.d(LOG_TAG, "Item selected from drop down is" + item);
                        }
                    });
                    myAlertBuilder.setPositiveButton(getString(R.string.place_order), new
                            DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int item) {
                                    // User clicked OK button.
                                    Log.d(LOG_TAG, "function to call checkoutActivity ");
                                    Intent intent = new Intent(FloatingActionActivity.this, CheckOutActivity.class);
                                    double subtotal = calculateTotal();
                                    Map<String, Double> tax = taxCalculation(subtotal + shipping_cost);
                                    Log.d(LOG_TAG, "shopping cost is " + shipping_cost);
                                    intent.putExtra(getString(R.string.extra_SubTotal), df2.format(subtotal));
                                    intent.putExtra(getString(R.string.extra_Shipping_cost), df2.format(shipping_cost));
                                    intent.putExtra(getString(R.string.extra_Tax_GST), df2.format(tax.get(getString(R.string.gst))));
                                    intent.putExtra(getString(R.string.extra_Tax_QST), df2.format(tax.get(getString(R.string.qst))));
                                    intent.putExtra(getString(R.string.extra_Total), df2.format(subtotal + tax.get(tax.get(getString(R.string.gst))) + tax.get(tax.get(getString(R.string.qst))) + shipping_cost));
                                    startActivity(intent);
                                }
                            });
                    myAlertBuilder.setNegativeButton(getString(R.string.cancel_btn), new
                            DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // User cancelled the dialog.
                                    Toast.makeText(getApplicationContext(), getString(R.string.return_to_main),
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    myAlertBuilder.show();
                }
            }
        });
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
      FloatingActionButton btn = findViewById(R.id.fab);
//        //setting the value
//        tv_subtotal.setText("Price Before Tax :"+df2.format(subtotal)+" "+"CAD");
//        tv_tax.setText("Tax : GST: "+df2.format(tax.get("GST"))+" "+"CAD, QST: "+df2.format(tax.get("QST"))+" "+"CAD");
//        tv_total.setText("Price After Tax :"+df2.format(subtotal+tax.get("GST")+tax.get("QST"))+" "+"CAD");

        //enable the button and disable when
        //no product is added for order
        Log.d(LOG_TAG,"set property to enable/disable place order button");
        return subtotal;
    }

    private Map<String,Double> taxCalculation(double total){
        Log.d(LOG_TAG,"function to calculate tax");
        //gst 5% and qst 9.975%
        double gst=total*0.05;
        double qst=total*0.09975;
        HashMap<String,Double> tax=new HashMap<String,Double>();
        tax.put(getString(R.string.gst),gst);
        tax.put(getString(R.string.qst),qst);
        return tax;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handles app bar item clicks.
     *
     * @param item Item clicked.
     * @return True if one of the defined items was clicked.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder myAlertBuilder = new
                AlertDialog.Builder(FloatingActionActivity.this);
        switch (item.getItemId()) {
            case R.id.action_contact_phone:
                Log.d(LOG_TAG,"selected contacts by phone");
                myAlertBuilder.setTitle(getString(R.string.contact_alert));
                myAlertBuilder.setMessage(getString(R.string.contact_message_phone));
                myAlertBuilder.show();
                break;
            case R.id.action_contact_email:
                Log.d(LOG_TAG,"selected contacts by email");
                myAlertBuilder.setTitle(getString(R.string.contact_alert));
                myAlertBuilder.setMessage(getString(R.string.contact_message_email));
                myAlertBuilder.show();
                break;
            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
    }

//    public void insertOrder(View view) {
//        Log.d(LOG_TAG,"function to call checkoutActivity ");
//        Intent intent = new Intent(this, CheckOutActivity.class);
//        double subtotal=calculateTotal();
//        Map<String,Double> tax=taxCalculation(subtotal);
//        intent.putExtra("SubTotal",df2.format(subtotal));
//        intent.putExtra("Tax_GST",df2.format(tax.get("GST")));
//        intent.putExtra("Tax_QST",df2.format(tax.get("QST")));
//        intent.putExtra("Total",df2.format(subtotal+tax.get("GST")+tax.get("QST")));
//        startActivity(intent);
//    }
}
