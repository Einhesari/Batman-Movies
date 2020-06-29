package com.einhesari.batmanmovies

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class BindingAdapter {

    companion object {

        val movieImagePlaceHolder = R.drawable.ic_movie_poster_placeholder

        @BindingAdapter("android:imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String?) {
            imageUrl?.let {
                Glide.with(view)
                    .setDefaultRequestOptions(
                        RequestOptions().circleCrop()
                    )
                    .load(imageUrl)
                    .placeholder(movieImagePlaceHolder)
                    .error(movieImagePlaceHolder)
                    .into(view)
            } ?: also {
                view.setImageResource(movieImagePlaceHolder)
            }
        }
    }


}