package com.aadamsdev.seshmobile.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.aadamsdev.seshmobile.Product;
import com.aadamsdev.seshmobile.R;
import com.aadamsdev.seshmobile.fragments.ProductFragment;

/**
 * Created by andrewadams on 2017-12-19.
 */

public class ProductActivity extends AppCompatActivity {

    private static final String EXTRA_PRODUCT = "extra_product";

    private Product product;

    private ProductFragment productFragment;

    private FragmentTransaction fragmentTransaction;

    public static Intent newIntent(Context context, Product product) {
        Intent intent = new Intent(context, ProductGalleryActivity.class);
        intent.putExtra(EXTRA_PRODUCT, product);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        product = getIntent().getParcelableExtra(EXTRA_PRODUCT);

        productFragment = ProductFragment.newFragment(product);
        launchFragment(productFragment);
    }

    private void launchFragment(ProductFragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.activity_main, fragment);
        fragmentTransaction.commit();
    }
}
