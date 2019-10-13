package com.application.chawlanicershop;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Neelu on 18/09/2019
 */


public class SaladAdapter extends RecyclerView.Adapter<SaladAdapter.ProductViewHolder> {

    private static final String LOG_TAG =
            SaladAdapter.class.getSimpleName();
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
        Log.d(LOG_TAG,"inflating and returning our view holder");
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.saladcardview, null);
        return new ProductViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        //getting the product of the specified position
        Log.d(LOG_TAG,"getting the product of the specified position");
        final Salad product = saladList.get(position);
        Log.d(LOG_TAG,"binding the data with the viewholder views");
        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getTitle());
        holder.textViewDesc.setText(product.getDescription());
        holder.add_quantity.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                product.addToQuantity();
                Log.d(LOG_TAG,mCtx.getString(R.string.item_added_text) + product.getTitle()+mCtx.getString(R.string.product_price_text)+product.getSubtotal() );
                Log.d(LOG_TAG,"notifyChange");
                notifyDataSetChanged();


            }
        });
        holder.reduce_quantity.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                product.subQuantity();
                Log.d(LOG_TAG,mCtx.getString(R.string.item_removed_text) + product.getTitle()+mCtx.getString(R.string.product_price_text)+product.getSubtotal() );
                Log.d(LOG_TAG,"notifyChange");
                notifyDataSetChanged();


            }
        });

        holder.textViewQuantity.setText(String.valueOf(product.getQuantity()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice())+" "+mCtx.getString(R.string.currency));
        holder.textViewSubTotal.setText(String.valueOf(product.getSubtotal()) +" "+ mCtx.getString(R.string.currency));
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getThumbnail()));

    }



    @Override
    public int getItemCount() {
        return saladList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final String LOG_TAG =
                ProductViewHolder.class.getSimpleName();
        TextView textViewTitle, textViewDesc, textViewQuantity, textViewPrice,textViewSubTotal;
        ImageView imageView,reduce_quantity,add_quantity,additional_info;
        final SaladAdapter saladAdapter;

        public ProductViewHolder(View itemView,SaladAdapter adapter) {
            super(itemView);
            Log.d(LOG_TAG,"Initalizing the views that belong to items of recyclerview");
            reduce_quantity=(ImageView) itemView.findViewById(R.id.reduceQuantity);
            add_quantity=(ImageView) itemView.findViewById(R.id.addQuantity);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
            textViewSubTotal=itemView.findViewById(R.id.textViewSubTotal);
            additional_info=(ImageView) itemView.findViewById(R.id.additional_info);
            additional_info.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(additional_info.getContext(), v);

                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.share:
                                    Toast.makeText(additional_info.getContext(), R.string.share_with_friend,
                                            Toast.LENGTH_SHORT).show();
                                    return true;
                                case R.id.Mail:
                                    Toast.makeText(additional_info.getContext(), R.string.share_with_friend,
                                            Toast.LENGTH_SHORT).show();
                                    return true;
                                case R.id.MoreInfo:
                                    AlertDialog.Builder myAlertBuilder;
                                    myAlertBuilder = new
                                            AlertDialog.Builder(additional_info.getContext());
                                    myAlertBuilder.setTitle(R.string.more);
                                    int mPosition = getLayoutPosition();
                                    Log.d(LOG_TAG,String.valueOf(mPosition));
                                    myAlertBuilder.setMessage("Veg: "+(saladList.get(mPosition).getVeg()==true?"Yes":"No")+" ,Gluten Free"+(saladList.get(mPosition).getGluten_free()==true?"Yes":"No"));
                                    myAlertBuilder.show();
                                   return true;
                                default:
                                   return false;
                            }
                        }
                    });
                    // here you can inflate your menu
                    popup.inflate(R.menu.menu_pop_up);



                    popup.show();
                }
            });
            this.saladAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
// Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            Log.d(LOG_TAG,String.valueOf(mPosition));
            // Use that to access the affected item in mWordList.
            String element = String.valueOf(saladList.get(mPosition));
            // Change the word in the mWordList.

//            mWordList.set(mPosition, "Clicked! " + element);
            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
            saladAdapter.notifyDataSetChanged();
        }
    }
}