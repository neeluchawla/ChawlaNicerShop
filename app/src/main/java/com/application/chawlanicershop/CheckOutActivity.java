package com.application.chawlanicershop;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheckOutActivity extends AppCompatActivity {
    public static TextView tv_total,tv_subtotal,tv_tax_gst,tv_tax_qst,tv_shipping_cost;
    String subtotal,tax_gst,tax_qst,total,shipping_cost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            shipping_cost=extras.getString("Shipping_cost");
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
        tv_shipping_cost =(TextView) findViewById(R.id.shipping_price);
        //setting the value

       tv_subtotal.setText(getString(R.string.general_text_price_before_tax)+subtotal+" "+getString(R.string.currency));
        tv_shipping_cost.setText(getString(R.string.general_shipping_cost)+shipping_cost+" "+getString(R.string.currency));
        tv_tax_gst.setText(getString(R.string.general_text_tax_gst)+tax_gst+" "+getString(R.string.currency));
        tv_tax_qst.setText(getString(R.string.general_text_tax_qst)+tax_qst+" "+getString(R.string.currency));
      tv_total.setText(getString(R.string.general_text_price_after_tax)+total+" "+getString(R.string.currency));
    }
}
