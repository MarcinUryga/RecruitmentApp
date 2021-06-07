package com.example.recruitmentapp.utils.binding_adapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.recruitmentapp.extensions.load
import com.example.recruitmentapp.utils.CircleTransform

@BindingAdapter(
    value = ["loadOvalUrl", "placeholder", "error"],
    requireAll = false
)
fun ImageView.loadUrl(
    url: String,
    placeholder: Drawable?,
    error: Drawable?,
) {
    load(
        url = url,
        placeholder = placeholder,
        error = error,
        transformers = listOf(CircleTransform())
    )
}