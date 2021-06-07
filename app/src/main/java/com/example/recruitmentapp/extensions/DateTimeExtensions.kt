package com.example.recruitmentapp.extensions

import com.example.recruitmentapp.utils.DateTimeFormatters
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter

fun DateTimeFormatter.localize(): DateTimeFormatter {
    return this.withLocale(locale)
}

fun DateTime.shortDate(): String {
    return DateTimeFormatters.shortDate(this)
}