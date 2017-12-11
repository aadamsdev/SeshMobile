package com.aadamsdev.seshmobile;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andrewadams on 2017-11-08.
 */

public class ProductGalleryAdapter extends RecyclerView.Adapter<ProductGalleryAdapter.ProductGalleryViewHolder> {
    private ArrayList<Product> products;
    private Context context;
    private int screenWidth;
    private int screenHeight;
    private int columns;

    public ProductGalleryAdapter(Context context, ArrayList<Product> products, int columns) {
        this.context = context;
        this.products = products;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        this.columns = columns;
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
    public void onBindViewHolder(final ProductGalleryAdapter.ProductGalleryViewHolder holder, int position) {
        final Product product = products.get(position);

        final ImageView productImage = holder.productImage;
        final ViewGroup.LayoutParams layoutParams = productImage.getLayoutParams();
        layoutParams.height = screenHeight / columns;
        layoutParams.width = screenWidth / columns;

        final TextView productText = holder.productText;
        productText.setText(product.getProductName());

        final ProgressBar progressBar = holder.progressBar;

        if (!product.isSoldOut()) {
            holder.setInStockView();
        }

        Glide.with(context)
                .load(product.getImageUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(productImage)
                .getSize(new SizeReadyCallback() {
                    @Override
                    public void onSizeReady(int width, int height) {
                        layoutParams.height = layoutParams.width;
                    }
                });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductGalleryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.product_image)
        ImageView productImage;

        @BindView(R.id.progress)
        ProgressBar progressBar;

        @BindView(R.id.product_text)
        TextView productText;

        @BindView(R.id.sold_out_text)
        TextView soldOutText;

        ProductGalleryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        public void setInStockView() {
            productImage.setAlpha(1.0f);
            soldOutText.setVisibility(View.GONE);
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
