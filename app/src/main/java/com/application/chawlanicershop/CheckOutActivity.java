package com.application.chawlanicershop;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheckOutActivity extends AppCompatActivity {
    public static TextView tv_total,tv_subtotal,tv_tax_gst,tv_tax_qst;
    String subtotal,tax_gst,tax_qst,total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            subtotal = extras.getString("SubTotal");
            tax_gst = extras.getString("Tax_GST");
            tax_qst = extras.getString("Tax_QST");
            total = extras.getString("Total");
        }
        setContentView(R.layout.activity_check_out);
        //find the view
             tv_subtotal =(TextView) findViewById(R.id.non_taxTotal);
             tv_tax_gst=(TextView) findViewById(R.id.tax_gst);
        tv_tax_qst=(TextView) findViewById(R.id.tax_qst);
          tv_total =(TextView) findViewById(R.id.tv_total);
        //setting the value
       tv_subtotal.setText("Price Before Tax :"+subtotal+" "+"CAD");
        tv_tax_gst.setText("Tax : GST: "+tax_gst+" "+"CAD");
        tv_tax_qst.setText("Tax : GST: "+tax_qst+" "+"CAD");
      tv_total.setText("Price After Tax :"+total+" "+"CAD");
    }
}
