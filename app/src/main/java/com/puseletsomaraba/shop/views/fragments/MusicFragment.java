package com.puseletsomaraba.shop.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.puseletsomaraba.shop.adapters.ProductsAdapter;
import com.puseletsomaraba.shop.listeners.ProductCartListener;
import com.puseletsomaraba.shop.views.main.MainActivity;
import com.puseletsomaraba.shop.R;
import com.puseletsomaraba.shop.listeners.OnProductClickListener;
import com.puseletsomaraba.shop.models.Product;
import com.puseletsomaraba.shop.utils.Globals;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment implements OnProductClickListener, ProductCartListener {

    View musicView;
    private RecyclerView recyclerView;
    private ProductsAdapter productsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private OnProductClickListener clickListener;
    private ProductCartListener productCartListener;

    public MusicFragment() {
        // Required empty public constructor
        clickListener = this;
        productCartListener = this;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        musicView = inflater.inflate(R.layout.fragment_music, container, false);
        //initialise recycler
        recyclerView = musicView.findViewById(R.id.products_RecyclerView_music);

        //call recycler view
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);


        getMusicProducts();

        return musicView;
    }

    public void getMusicProducts() {
        Call<List<Product>> response = Globals.apiInterface
                .getMusicList();
        response.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if(response.isSuccessful()) {
                    List<Product> products = response.body();
                    productsAdapter= new ProductsAdapter(products,getActivity(), clickListener, productCartListener);
                    recyclerView.setAdapter(productsAdapter);
                }



            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("maraba", t.getMessage());

            }
        });
    }

    @Override
    public void onProductClicked(Product product) {
        ((MainActivity)getActivity()).addItemToCart(product);
    }

    @Override
    public int getProductQtyFromCart(int productId) {
        return ((MainActivity)getActivity()).getProductNoFromCart(productId);
    }
}

