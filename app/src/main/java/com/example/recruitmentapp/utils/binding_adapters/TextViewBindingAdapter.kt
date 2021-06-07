package com.example.recruitmentapp.utils.binding_adapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.recruitmentapp.extensions.shortDate
import com.example.recruitmentapp.source.Result
import org.joda.time.DateTime

@BindingAdapter("showOnError")
fun TextView.showError(result: Result<Any>?) {
    visibility = if (result is Result.Error) {
        text = result.exception.message
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("date")
fun TextView.showDate(date: DateTime?) {
    with(date) {
        if (this == null) {
            visibility = View.GONE
        } else {
            text = shortDate()
        }
    }
}