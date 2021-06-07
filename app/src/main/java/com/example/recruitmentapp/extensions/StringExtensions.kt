package com.example.recruitmentapp.extensions

import com.example.recruitmentapp.utils.HtmlUtils

fun String.findHtmlLinks(): List<String> {
    val urlList = mutableListOf<String>()
    val pattern = HtmlUtils.getUrlPattern()
    val urlMatcher = pattern.matcher(this)
    var matchStart: Int
    var matchEnd: Int
    while (urlMatcher.find()) {
        matchStart = urlMatcher.start(1)
        matchEnd = urlMatcher.end()
        val url = this.substring(matchStart, matchEnd)
        urlList.add(url)
        this.replace(url, "")
    }
    return urlList
}