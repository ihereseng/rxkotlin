package com.example.rxkotlin.util

import android.content.Context

object Converter {
    fun dpToPixel(dp: Int, context: Context): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }
}