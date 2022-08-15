package com.hemant.cvssampleapp

import android.util.Log
import org.jsoup.Jsoup

object HtmlParserUtil {

    fun parseDescriptionData(html: String): List<String> {
        val result = mutableListOf<String>()

        val doc = Jsoup.parse(html)
        val width = doc.select("p").select("img").attr("width")
        val height = doc.select("p").select("img").attr("height")
        val description = if (doc.select("p").size > 2) {
            doc.select("p")[2].wholeText()
        } else {
            "No description available"
        }
        Log.d("UTIL", width)
        Log.d("UTIL", height)
        Log.d("UTIL", description)

        result.add(0, width)
        result.add(1, height)
        result.add(2, description)
        return result
    }
}