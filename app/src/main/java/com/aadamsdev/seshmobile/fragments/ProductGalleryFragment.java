package com.aadamsdev.seshmobile.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.aadamsdev.seshmobile.Product;
import com.aadamsdev.seshmobile.ProductGalleryAdapter;
import com.aadamsdev.seshmobile.dialogs.ProgressDialogFragment;
import com.aadamsdev.seshmobile.R;
import com.aadamsdev.seshmobile.utils.DialogUtils;
import com.aadamsdev.seshmobile.utils.HttpUtils;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andrewadams on 2017-11-08
 */
public class ProductGalleryFragment extends Fragment {

    private static final String DIALOG_REFRESH = "RefreshDialog";

    private Context context;
    private View view;

    private int galleryColumns = 2;
    private ArrayList<Product> products;

    @BindView(R.id.product_list)
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        setHasOptionsMenu(true);

        products = (ArrayList<Product>) getArguments().getSerializable("products");
        Log.i("ProductGalleryFragment", "ProductGalleryFragment");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.product_gallery_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                showRefreshDialog();
                HttpUtils.getAllProducts(context, new HttpUtils.AllProductsCallback() {
                    @Override
                    public void onProductsReceived(ArrayList<Product> products) {
                        ProductGalleryFragment.this.products = products;
                        recyclerView.setAdapter(new ProductGalleryAdapter(context, products, galleryColumns));
                        dismissRefreshDialog();
                    }

                    @Override
                    public void onError(VolleyError error) {

                    }
                });
        }
        return super.onOptionsItemSelected(item);
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

    private void showRefreshDialog() {
        ProgressDialogFragment dialog = ProgressDialogFragment.newInstance(R.string.loading_products);
        DialogUtils.show(this, dialog, DIALOG_REFRESH);
    }

    private void dismissRefreshDialog() {
        DialogUtils.dismiss(this, DIALOG_REFRESH);
    }
}
