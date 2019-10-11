package com.application.chawlanicershop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by Neelu on 18/09/2019
 */


public class SaladAdapter extends RecyclerView.Adapter<SaladAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    public List<Salad> saladList;
    public double total=0;
    SaladAdapter saladAdapter;
    public static TextView tv_total;


    //getting the context and product list with constructor
    public SaladAdapter(Context mCtx, List<Salad> productList) {
        this.mCtx = mCtx;
        this.saladList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.saladcardview, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        //getting the product of the specified position
        final Salad product = saladList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getTitle());
        holder.textViewDesc.setText(product.getDescription());
        holder.add_quantity.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                product.addToQuantity();
                notifyDataSetChanged();


            }
        });
        holder.reduce_quantity.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                product.subQuantity();
                notifyDataSetChanged();


            }
        });

        holder.textViewQuantity.setText(String.valueOf(product.getQuantity()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice())+" "+"CAD");
        holder.textViewSubTotal.setText(String.valueOf(product.getSubtotal()) + " " + "CAD");
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getThumbnail()));

    }



    @Override
    public int getItemCount() {
        return saladList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {


        TextView textViewTitle, textViewDesc, textViewQuantity, textViewPrice,textViewSubTotal;
        ImageView imageView,reduce_quantity,add_quantity;

        public ProductViewHolder(View itemView) {
            super(itemView);
            reduce_quantity=(ImageView) itemView.findViewById(R.id.reduceQuantity);
            add_quantity=(ImageView) itemView.findViewById(R.id.addQuantity);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
            textViewSubTotal=itemView.findViewById(R.id.textViewSubTotal);
        }
    }
}