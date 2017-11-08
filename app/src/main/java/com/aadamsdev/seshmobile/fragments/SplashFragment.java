package com.aadamsdev.seshmobile.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aadamsdev.seshmobile.Product;
import com.aadamsdev.seshmobile.R;
import com.aadamsdev.seshmobile.utils.HttpUtils;
import com.aadamsdev.seshmobile.utils.PreferenceManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.SharedPreferences.*;

/**
 * Created by Andrew Adams on 6/10/2017.
 */
public class SplashFragment extends Fragment {

    private View view;
    private Context context;

    @BindView(R.id.sesh_logo) ImageView seshLogo;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

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

        HttpUtils.getAllProducts(context, new HttpUtils.HttpCallback() {
            @Override
            public void onProductsReceived(List<Product> products) {
                Log.i("SplashFragment", "onProductsReceived");
                for (Product product : products) {
                    Log.i("SplashFragment", product.getProductName() + " " + product.getPrice() + " " + product.getProductUrl() + " " + product.getImageUrl() );
                }


            }
        });
    }
}