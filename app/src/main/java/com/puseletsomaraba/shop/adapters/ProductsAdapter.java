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
import com.puseletsomaraba.shop.models.Product;
import com.puseletsomaraba.shop.views.main.ProductDetailsActivity;

import java.util.List;

public class ProductsAdapter extends
        RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Product> productList;
    private Context mContext;
    private OnProductClickListener clickListener;
    private ProductCartListener productCartListener;



    public ProductsAdapter(List<Product> productList, Context context,
                           OnProductClickListener clickListener, ProductCartListener productCartListener) {

        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.productList = productList;
        this.clickListener = clickListener;
        this.productCartListener = productCartListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewtype) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_item, parent, false);
        return new ViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //assignment operator
        final Product product = productList.get(position);

        Glide.with(mContext).
                load(product.getImgURL())
                .thumbnail(0.5f)
                .apply((new RequestOptions()).
                        diskCacheStrategy(DiskCacheStrategy.ALL)) //V4 Added Diskcache in RequestOptions
                .into(holder.imgImageView);

        holder.nameTextView.setText(productList.get(position).getName());
        holder.priceTextView.setText("R"+productList.get(position).getPrice());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                Intent productIntent = new Intent(mContext, ProductDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", product);
                bundle.putInt("qty", getCartItemNo(product.getId()));
                productIntent.putExtras(bundle);
                mContext.startActivity(productIntent);
            }
        });

        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onProductClicked(product);
            }
        });

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
        public ImageView imgImageView;
        public TextView nameTextView;
        public TextView priceTextView;
        public CardView cardView;
        public LinearLayout addToCartButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //initialise variables
            imgImageView = itemView.findViewById(R.id.imgImageview);
            cardView = itemView.findViewById(R.id.cardView);
            nameTextView = itemView.findViewById(R.id.productNametextView);
            priceTextView = itemView.findViewById(R.id.productPricetextView);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);


        }
    }
}
