package dev.chaitan.gitusersearch.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import dev.chaitan.gitusersearch.R

class ImageBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("android:src")
        fun setImage(imageView: ImageView, url: String?) {
            if (url != null) {
                Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_place_holder)
                    .into(imageView)
            }
        }
    }
}