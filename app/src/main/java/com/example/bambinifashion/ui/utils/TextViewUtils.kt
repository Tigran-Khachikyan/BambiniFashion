package com.example.bambinifashion.ui.utils

import android.os.Build
import android.text.Html
import android.widget.TextView

fun TextView.displayHtml(html: String) {
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(html)
    }
}