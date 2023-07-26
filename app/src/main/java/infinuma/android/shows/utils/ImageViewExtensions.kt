package infinuma.android.shows.utils

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadWithGlide(bitmap: Bitmap?) {
    Glide.with(context)
        .load(bitmap)
        .into(this)
}
