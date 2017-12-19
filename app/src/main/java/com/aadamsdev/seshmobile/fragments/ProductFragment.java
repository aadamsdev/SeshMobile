package com.aadamsdev.seshmobile.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.aadamsdev.seshmobile.Product;

import java.util.ArrayList;

/**
 * Created by andrewadams on 2017-12-19.
 */

public class ProductFragment extends Fragment {

    private final static String ARG_PRODUCT = "product";

    public static ProductFragment newFragment(Product product) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_PRODUCT, product);

        ProductFragment productFragment = new ProductFragment();
        productFragment.setArguments(bundle);

        return productFragment;
    }
}
