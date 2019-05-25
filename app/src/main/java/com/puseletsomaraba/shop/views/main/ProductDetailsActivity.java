package com.puseletsomaraba.shop.views.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.puseletsomaraba.shop.R;
import com.puseletsomaraba.shop.models.Product;

import java.io.Serializable;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvPrice;
    private TextView tvItemNumber;
    private ImageView imgProduct;

    private Product mProduct;

    private int mCartQty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);
        init();
        if(getIntent() == null)
            return;

        Bundle bundle = getIntent().getExtras();

        mProduct = (Product) bundle.get("product");
        mCartQty =  bundle.getInt("qty");

        if(mProduct != null){
            displayProduct();
        }
    }

    private void init() {
        tvName = findViewById(R.id.tvName);
        tvPrice = findViewById(R.id.tvPrice);
        tvItemNumber = findViewById(R.id.tvItemNumber);
        imgProduct = findViewById(R.id.imgProduct);
    }

    private void displayProduct() {
        tvName.setText(mProduct.getName());
        tvPrice.setText(mProduct.getPrice());
        tvItemNumber.setText(""+mCartQty);
        Glide.with(getBaseContext()).
                load(mProduct.getImgURL())
                .thumbnail(0.5f)
                .apply((new RequestOptions()).
                        diskCacheStrategy(DiskCacheStrategy.ALL)) //V4 Added Diskcache in RequestOptions
                .into(imgProduct);
    }
}
