package com.example.recruitmentapp.utils.binding_adapters

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.recruitmentapp.source.Result

@BindingAdapter("hideOnLoading")
fun View.hideOnLoading(result: Result<Any>?) {
    visibility = if (result is Result.Loading)
        View.GONE
    else
        View.VISIBLE
}

@BindingAdapter("android:visibility")
fun View.visibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}
