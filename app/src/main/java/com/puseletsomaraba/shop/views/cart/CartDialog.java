package com.puseletsomaraba.shop.views.cart;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.puseletsomaraba.shop.R;
import com.puseletsomaraba.shop.adapters.CartAdapter;
import com.puseletsomaraba.shop.models.CartProduct;
import com.puseletsomaraba.shop.models.Product;

import java.util.List;


public class CartDialog extends DialogFragment {

    private Context mContext;
    private List<CartProduct> cartProducts;

    private RecyclerView recyclerCart;
    private RecyclerView.LayoutManager layoutManager;

    private CartAdapter cartAdapter;

    private double mCartTotal = 0;

    private final double TAXPERCENT = 10;
    private final double IMPORTPERCENT = 5;

    private double mTotalTax = 0;
    private double mImportedPrice = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            if(bundle.getSerializable("cart") != null) {
                cartProducts = (List<CartProduct>) bundle.getSerializable("cart");
                computeCartTotal();
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    private int getItemCount(){
        int count = 0;

        for (CartProduct product: cartProducts
             ) {
            count += product.getQuantity();
        }
        return count;
    }

    private double roundToNearest(double price){

        return   (double) Math.round(price * 100) / 100;
    }

    private double taxPrice( double price){

        double priceWithTax = ((TAXPERCENT / 100) * price);
        return roundToNearest(priceWithTax);
    }

    private double importedPrice( double price){
        double importedPrice = ((IMPORTPERCENT / 100) * price);
        Log.d("maraba", "IMPORT: "+importedPrice + " Price: "+price);
        return importedPrice;
    }

    private void computeCartTotal(){

        for (CartProduct product: cartProducts
        ) {
            double price = Double.valueOf(product.getProduct().getPrice());
            double finalPrice = price;

            double taxedPrice = 0;
            if(product.getProduct().isTaxApllicable())

                mTotalTax += taxPrice(price);
                finalPrice = price + taxedPrice;

            if(product.getProduct().getImported()){
                mImportedPrice = mImportedPrice + importedPrice(price);
            }


            mCartTotal += (finalPrice * product.getQuantity());
            //Log.d("maraba", "PriceWithTax: "+priceWithTax + " Price: "+price);
        }
        mCartTotal = roundToNearest(mCartTotal);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =  LayoutInflater.from(mContext);
        final View dialogView = inflater.inflate(R.layout.cart_activity, null);
        recyclerCart = dialogView.findViewById(R.id.recyclerCart);
        TextView tvNumItem = dialogView.findViewById(R.id.tvNumItem);
        TextView tvCartTotal = dialogView.findViewById(R.id.tvCartTotal);
        TextView tvTotalTax = dialogView.findViewById(R.id.tvTotalTax);
        ImageView imgViewClose = dialogView.findViewById(R.id.imgViewClose);

        tvNumItem.setText(""+getItemCount());
        tvCartTotal.setText("R "+mCartTotal);
        tvTotalTax.setText("R "+(mTotalTax + mTotalTax));

        imgViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        recyclerCart.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerCart.setLayoutManager(layoutManager);

        cartAdapter = new CartAdapter(cartProducts ,getActivity());
        recyclerCart.setAdapter(cartAdapter);

        builder.setCancelable(true);
        builder.setView(dialogView);

        return  builder.create();
    }
}
