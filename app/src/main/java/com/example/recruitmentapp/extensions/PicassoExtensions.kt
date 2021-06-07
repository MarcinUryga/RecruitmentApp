package com.example.recruitmentapp.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

fun ImageView.load(
    url: String?,
    placeholder: Drawable? = null,
    error: Drawable? = null,
    transformers: List<Transformation>? = emptyList(),
    callback: Callback? = null
) {
    val request = Picasso.with(this.context)
        .load(url)
        .transform(transformers)
        .placeholder(placeholder)
    if (error != null) {
        request.error(error)
    }
    request.into(this, callback)
}
