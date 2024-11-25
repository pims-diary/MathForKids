package com.example.math_for_kids.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.math_for_kids.databaselayer.model.Episode

@Composable
fun EpisodeList(episodes: List<Episode>) {
    LazyColumn {
        items(episodes) { episode ->
            EpisodeItem(episode)
        }
    }
}

@Composable
fun EpisodeItem(episode: Episode) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = episode.title, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = episode.description)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Published on: ${episode.pubDate}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Read more at: ${episode.link}", color = Color.Blue, modifier = Modifier.clickable {
            // Handle link click
        })
    }
}
