package com.opsat7373.moviestars.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.opsat7373.moviestars.R

@BindingAdapter("android:imageUrl")
fun posterUrl(view : ImageView, posterUrl : String?) {
    Glide
        .with(view)
        .load(posterUrl)
        .fitCenter()
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .transition(DrawableTransitionOptions.withCrossFade(500))
        .placeholder(R.drawable.movie_title)
        .error(R.drawable.ic_launcher_foreground)
        .into(view)
}