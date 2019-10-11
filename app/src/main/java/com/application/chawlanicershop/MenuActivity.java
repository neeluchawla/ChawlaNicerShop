package com.application.chawlanicershop;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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


        //adding some items to our list
        saladList.add(
                new Salad(
                        1,
                        "SEARED TUNA SALAD",
                        "mixed greens, arugula, kale, avocado, cucumber, edamame, super slaw, apples, tempura onions, toasted sesame seeds, spicy togarashi seared tuna",
                        20.99,
                        R.drawable.sesame_salad));

        saladList.add(
                new Salad(
                        2,
                        "ASIAN SALAD",
                        "romaine lettuce, mixed greens, avocado, mandarin oranges, cherry tomatoes, shredded carrots, crunchy noodles, toasted sesame seeds",
                        11.49,
                        R.drawable.asian_salad));

        saladList.add(
                new Salad(
                        3,
                        "CAESAR SALAD",
                        "kale, romaine lettuce, baked pita chips, mozzarella, shaved parmesan, bacon, free run hard boiled egg",
                        14.49,
                        R.drawable.caesar_salad));
        saladList.add(
                new Salad(
                        4,
                        "HARVEST SALAD",
                        "romaine lettuce, mixed greens, cherry tomatoes, roasted sweet potato, dried cranberries, pecans, pumpkin seeds, goat cheese",
                        13.99,
                        R.drawable.harvest_salad));

        //creating recyclerview adapter
        SaladAdapter adapter = new SaladAdapter(this, saladList);

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


    public void calculateTotal() {
        int i = 0;
        total=0;
        tax=0;
        subtotal=0;
        while(i < saladList.size()){
            if(saladList.get(i).getQuantity()>0) {
                subtotal = subtotal + (saladList.get(i).getPrice() * Integer.valueOf(saladList.get(i).getQuantity()));
            }
            i++;

        }
        Map<String,Double> tax=taxCalculation(subtotal);

        //find the view
        tv_subtotal =(TextView) findViewById(R.id.non_taxTotal);
        tv_tax=(TextView) findViewById(R.id.tax);
        tv_total =(TextView) findViewById(R.id.tv_total);
        Button btn = (Button) findViewById(R.id.btn_placeorder);
        //setting the value
        tv_subtotal.setText("Price Before Tax :"+df2.format(subtotal)+" "+"CAD");
        tv_tax.setText("Tax : GST: "+df2.format(tax.get("GST"))+" "+"CAD, QST: "+df2.format(tax.get("QST"))+" "+"CAD");
        tv_total.setText("Price After Tax :"+df2.format(subtotal+tax.get("GST")+tax.get("QST"))+" "+"CAD");

        //enable the button and disable when
        //no product is added for order
        if(subtotal>0) {
            btn.setEnabled(true);
            btn.setBackgroundColor(Color.GREEN);
        }
        else{
            btn.setEnabled(false);
            btn.setBackgroundColor(Color.GRAY);
        }
    }

    private Map<String,Double> taxCalculation(double total){
        //gst 5% and qst 9.975%
        double gst=total*0.05;
        double qst=total*0.09975;
        HashMap<String,Double> tax=new HashMap<String,Double>();
        tax.put("GST",gst);
        tax.put("QST",qst);
        return tax;
    }
    public void insertOrder(View view) {

        Intent intent = new Intent(this, CheckOutActivity.class);
        startActivity(intent);
    }
}
