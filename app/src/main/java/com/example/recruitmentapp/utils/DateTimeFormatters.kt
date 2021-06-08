package com.example.recruitmentapp.utils

import com.example.recruitmentapp.extensions.localize
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.*

object DateTimeFormatters {
    private const val FULL_DATE_FORMAT = "yyyy-MM-dd"

    private val defaultFormatter by lazy {
        DateTimeFormat.forPattern(FULL_DATE_FORMAT).localize()
    }

    private val shortDateFormatter by lazy {
        DateTimeFormat.shortDate().localize()
    }

    fun parseByLocalize(date: String): DateTime? {
        return defaultFormatter.parseDateTime(date)
    }

    fun shortDate(dateTime: DateTime): String {
        return shortDateFormatter.print(dateTime)
    }
}