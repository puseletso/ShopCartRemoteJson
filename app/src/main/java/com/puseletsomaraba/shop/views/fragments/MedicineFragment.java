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
public class MedicineFragment extends Fragment implements OnProductClickListener, ProductCartListener {


    View medicineView;
    private RecyclerView recyclerView;
    private ProductsAdapter productsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private OnProductClickListener clickListener;
    private ProductCartListener productCartListener;


    public MedicineFragment() {
        // Required empty public constructor
        clickListener = this;
        productCartListener = this;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        medicineView = inflater.inflate(R.layout.fragment_medicine, container, false);
        //initialise recycler
        recyclerView = medicineView.findViewById(R.id.products_RecyclerView_medicine);

        //call recycler view
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);


        getMedicineProducts();

        return medicineView;
    }

    public void getMedicineProducts() {
        Call<List<Product>> response = Globals.apiInterface
                .getMedicineList();
        response.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> products = response.body();
                productsAdapter= new ProductsAdapter(products,getActivity(), clickListener, productCartListener);
                recyclerView.setAdapter(productsAdapter);



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




