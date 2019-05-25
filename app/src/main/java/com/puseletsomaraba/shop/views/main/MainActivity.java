package com.puseletsomaraba.shop.views.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.puseletsomaraba.shop.R;
import com.puseletsomaraba.shop.controller.ShopPagerAdapter;
import com.puseletsomaraba.shop.models.CartProduct;
import com.puseletsomaraba.shop.models.Product;
import com.puseletsomaraba.shop.views.cart.CartDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;

    private List<CartProduct> cartList;
    private TextView tvCartNo;

    private LinearLayout linearOpenCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ShopPagerAdapter(getSupportFragmentManager()));
        TabLayout tabs = findViewById(R.id.tabLayoutId);
        tabs.setupWithViewPager(viewPager);

        linearOpenCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("cart", (Serializable)cartList);
                CartDialog dialog = new CartDialog();
                dialog.setArguments(bundle);
                FragmentManager manager = getSupportFragmentManager();
                dialog.show(manager,"cart_view");
            }
        });

        cartList = new ArrayList<>();
        updateUiCart();
    }

    public void addItemToCart(Product product){

        if(cartList.size() > 0){
            int currentQty = -1;
            for (int i = 0; i < cartList.size(); i++) {
                if(cartList.get(i).getProduct().getId() == product.getId()){
                    currentQty = cartList.get(i).getQuantity();
                    cartList.get(i).setQuantity(currentQty + 1);
                    break;
                }
            }

            if(currentQty == -1){
                cartList.add(new CartProduct(1, product));
            }
        }else{
            cartList.add(new CartProduct(1, product));
        }
        updateUiCart();
    }

    public void removeProductFromCart(int index){
        cartList.remove(index);
        updateUiCart();
    }

    public int getProductNoFromCart(int productId){
        int cartItemNo = 0;

        for (CartProduct product: cartList
             ) {
            if(productId == product.getProduct().getId())
            {
                cartItemNo = product.getQuantity();
            }
        }
        return cartItemNo;
    }

    public void clearCart(){
        cartList.clear();
        updateUiCart();
    }

    private void updateUiCart(){
        int itemNo = 0;
        if(cartList != null){
            for (CartProduct cartItem: cartList
                 ) {
                itemNo += cartItem.getQuantity();
            }
        }
        tvCartNo.setText(""+itemNo);
    }

    private void init(){
        tvCartNo = findViewById(R.id.tvCartNo);
        linearOpenCart = findViewById(R.id.linearOpenCart);
    }
}
