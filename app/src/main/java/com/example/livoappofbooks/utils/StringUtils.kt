package com.example.livoappofbooks.utils

import androidx.core.text.HtmlCompat

fun parseHtmlToText(html: String?): String {
    if (html.isNullOrBlank()) return "Sinopse indisponível."; return HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT).toString().trim()
}