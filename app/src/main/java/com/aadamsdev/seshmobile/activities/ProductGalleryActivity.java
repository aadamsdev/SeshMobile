package com.aadamsdev.seshmobile.activities;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.aadamsdev.seshmobile.Product;
import com.aadamsdev.seshmobile.R;
import com.aadamsdev.seshmobile.fragments.ProductGalleryFragment;
import com.aadamsdev.seshmobile.fragments.SplashFragment;

import java.util.ArrayList;

/**
 * Created by andrewadams on 2017-12-11.
 */

public class ProductGalleryActivity extends AppCompatActivity {
    private static String EXTRA_PRODUCTS = "products_extra";

    private ArrayList<Product> products;

    private ProductGalleryFragment productGalleryFragment;
    private FragmentTransaction fragmentTransaction;

    public static Intent newIntent(Context context, ArrayList<Product> products) {
        Intent intent = new Intent(context, ProductGalleryActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_PRODUCTS, products);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        products = getIntent().getParcelableArrayListExtra(EXTRA_PRODUCTS);

        productGalleryFragment = ProductGalleryFragment.newFragment(products);
        launchFragment(productGalleryFragment);
    }

    private void launchFragment(ProductGalleryFragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.activity_main, fragment);
        fragmentTransaction.commit();
    }
}
