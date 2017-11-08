package com.aadamsdev.seshmobile.fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aadamsdev.seshmobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andrewadams on 2017-11-08.
 */

public class ProductListFragment extends Fragment {

    private Context context;
    private View view;

    @BindView(R.id.product_list) RecyclerView productList;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        Log.i("ProductListFragment", "ProductListFragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.product_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
