package com.example.math_for_kids.middlelayer.rss

import com.example.math_for_kids.databaselayer.model.Episode
import org.jsoup.Jsoup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun fetchRssFeed(url: String): List<Episode> = withContext(Dispatchers.IO) {
    val doc = Jsoup.connect(url).get()
    val items = doc.select("item")
    items.map { item ->
        Episode(
            title = item.select("title").text(),
            description = item.select("description").text().cleanHtml(),
            pubDate = item.select("pubDate").text(),
            link = item.select("link").text()
        )
    }
}

// Utility to clean and format HTML
fun String.cleanHtml(): String {
    return Jsoup.parse(this).text()
}
