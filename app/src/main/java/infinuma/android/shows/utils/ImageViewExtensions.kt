package infinuma.android.shows.utils

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import infinuma.android.shows.R

fun ImageView.loadWithGlide(bitmap: Bitmap?) {
    Glide.with(context)
        .load(bitmap)
        .into(this)
}

fun ImageView.loadWithGlide(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .error(R.drawable.ic_image_loading_error)
        .into(this)
}
