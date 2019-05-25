package com.puseletsomaraba.shop.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.puseletsomaraba.shop.R;
import com.puseletsomaraba.shop.listeners.OnProductClickListener;
import com.puseletsomaraba.shop.listeners.ProductCartListener;
import com.puseletsomaraba.shop.models.CartProduct;
import com.puseletsomaraba.shop.models.Product;
import com.puseletsomaraba.shop.views.main.ProductDetailsActivity;

import java.util.List;

public class CartAdapter extends
        RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<CartProduct> productList;
    private Context mContext;
    private OnProductClickListener clickListener;
    private ProductCartListener productCartListener;



    public CartAdapter(List<CartProduct> productList, Context context) {

        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.productList = productList;
        this.clickListener = clickListener;
        this.productCartListener = productCartListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewtype) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_row, parent, false);
        return new ViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //assignment operator
        final CartProduct product = productList.get(position);

        Glide.with(mContext).
                load(product.getProduct().getImgURL())
                .thumbnail(0.5f)
                .apply((new RequestOptions()).
                        diskCacheStrategy(DiskCacheStrategy.ALL)) //V4 Added Diskcache in RequestOptions
                .into(holder.imgProduct);

        holder.tvName.setText(productList.get(position).getProduct().getName());
        holder.tvPrice.setText(""+productList.get(position).getProduct().getPrice());
        holder.tvQTY.setText("Qty: "+productList.get(position).getQuantity());

    }

    private int getCartItemNo(int productId){
        return  productCartListener.getProductQtyFromCart(productId);
    }

    @Override
    public int getItemCount() {
        //specifies number of items we have
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        //access modifieres
        public ImageView imgProduct;
        public TextView tvName;
        public TextView tvPrice;
        public TextView tvQTY;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //initialise variables
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQTY = itemView.findViewById(R.id.tvQTY);

        }
    }
}
