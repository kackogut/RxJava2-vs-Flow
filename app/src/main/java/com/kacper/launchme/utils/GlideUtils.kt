package com.kacper.launchme.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kacper.launchme.R

class GlideUtils {
    companion object {
        fun loadImage(imageView: ImageView, imageURL: String?) {
            if (imageURL == null) {
                imageView.setImageResource(R.drawable.ic_error)
                return
            }

            Glide.with(imageView)
                .load(imageURL)
                .error(R.drawable.ic_error)
                .into(imageView)
        }
    }
}