package by.itacademy.pvt.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import by.itacademy.pvt.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

fun loadCircleImage(context: Context, url: String, imageView: ImageView) {
    Picasso.get()
        .load(Uri.parse(url))
        .error(R.drawable.error_image)
        .transform(CropCircleTransformation())
        .into(imageView)
}

fun loadImage(context: Context, url: String, imageView: ImageView) {
    Picasso.get()
        .load(Uri.parse(url))
        .error(R.drawable.error_image)
        .into(imageView)
}