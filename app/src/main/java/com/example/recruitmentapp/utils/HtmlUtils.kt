package com.example.recruitmentapp.utils

import java.util.regex.Pattern

object HtmlUtils {
    private const val linkRegex =
        "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)"

    fun getUrlPattern(): Pattern =
        Pattern.compile(linkRegex, Pattern.CASE_INSENSITIVE or Pattern.MULTILINE or Pattern.DOTALL)
}