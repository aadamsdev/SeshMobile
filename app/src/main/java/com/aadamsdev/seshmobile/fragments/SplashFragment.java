package com.aadamsdev.seshmobile.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.aadamsdev.seshmobile.Product;
import com.aadamsdev.seshmobile.R;
import com.aadamsdev.seshmobile.utils.HttpUtils;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andrew Adams on 6/10/2017.
 */
public class SplashFragment extends Fragment {

    private View view;
    private Context context;

    @BindView(R.id.sesh_logo)
    ImageView seshLogo;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        context = getContext();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.splash_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        HttpUtils.getAllProducts(context, new HttpUtils.AllProductsCallback() {
            @Override
            public void onProductsReceived(final ArrayList<Product> products) {
                Log.i("SplashFragment", "onProductsReceived");
                for (Product product : products) {
                    Log.i("SplashFragment", product.getProductName() + " " + product.getPrice() + " " + product.getProductUrl() + " " + product.getImageUrl());
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("SplashFragment", "Next fragment...");
                                launchProductListFragment(products);
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void onError(VolleyError error) {
                error.printStackTrace();
            }
        });
    }

    public void launchProductListFragment(ArrayList<Product> products) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("products", products);

        ProductListFragment productListFragment = new ProductListFragment();
        productListFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_main, productListFragment);
        fragmentTransaction.commit();
    }
}