package com.example.recruitmentapp.utils.binding_adapters

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.example.recruitmentapp.source.Result

@BindingAdapter("showOnLoading")
fun ProgressBar.showOnLoading(result: Result<Any>?) {
    visibility = if (result is Result.Loading)
        View.VISIBLE
    else
        View.GONE
}