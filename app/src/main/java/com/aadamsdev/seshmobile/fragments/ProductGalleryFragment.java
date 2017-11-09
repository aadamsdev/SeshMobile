package com.aadamsdev.seshmobile.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aadamsdev.seshmobile.Product;
import com.aadamsdev.seshmobile.ProductGalleryAdapter;
import com.aadamsdev.seshmobile.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andrewadams on 2017-11-08.
 */

public class ProductGalleryFragment extends Fragment {

    private Context context;
    private View view;

    @BindView(R.id.product_list) RecyclerView recyclerView;

    private int galleryColumns = 2;

    private ArrayList<Product> products;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();

        products = (ArrayList<Product>) getArguments().getSerializable("products");
        Log.i("ProductGalleryFragment", "ProductGalleryFragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.product_list, container, false);
        ButterKnife.bind(this, view);



        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, galleryColumns);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ProductGalleryAdapter adapter = new ProductGalleryAdapter(context, products, galleryColumns);
        recyclerView.setAdapter(adapter);


        return view;
    }
}
