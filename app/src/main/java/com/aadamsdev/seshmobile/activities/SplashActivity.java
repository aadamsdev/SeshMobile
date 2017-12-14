package com.aadamsdev.seshmobile.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aadamsdev.seshmobile.R;
import com.aadamsdev.seshmobile.fragments.SplashFragment;

public class SplashActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;
    private SplashFragment splashFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        splashFragment = SplashFragment.newFragment();
        launchFragment(splashFragment);
    }

    private void launchFragment(SplashFragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.activity_main, fragment);
        fragmentTransaction.commit();
    }
}
