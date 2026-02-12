package com.example.yumplanner.utiles.imageHelper;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageHelper {

        private ImageHelper() {
            // prevent creating instances
        }

        public static void loadImage(View view, String imageUrl, ImageView imageView) {
            Glide.with(view)
                    .load(imageUrl)
                    .into(imageView);
        }


}
