package com.aadamsdev.seshmobile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andrewadams on 2017-11-08.
 */

public class ProductGalleryAdapter extends RecyclerView.Adapter<ProductGalleryAdapter.ProductGalleryViewHolder> {
    private ArrayList<Product> products;
    private Context context;

    public ProductGalleryAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public ProductGalleryAdapter.ProductGalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.product, parent, false);

        ProductGalleryViewHolder viewHolder = new ProductGalleryViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductGalleryAdapter.ProductGalleryViewHolder holder, int position) {

        Product product = products.get(position);
        ImageView imageView = holder.productImage;

        Glide.with(context)
                .load(product.getImageUrl())
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductGalleryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.product_image) ImageView productImage;

        ProductGalleryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

//            int position = getAdapterPosition();
//            if(position != RecyclerView.NO_POSITION) {
//
//                SpacePhoto spacePhoto = mSpacePhotos[position];
//                Intent intent = new Intent(mContext, SpacePhotoActivity.class);
//                intent.putExtra(SpacePhotoActivity.EXTRA_SPACE_PHOTO, spacePhoto);
//                startActivity(intent);
//            }
        }
    }




}
