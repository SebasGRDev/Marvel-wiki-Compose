package com.sebasgrdev.marvelwiki.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebasgrdev.marvelwiki.R

@Preview(showBackground = true)
@Composable
fun DetailScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        //ImageCharacter(R.drawable.flash)
        Spacer(Modifier.height(16.dp))
        ComicText("UNCANNY X-MEN (2024) #2")
        Spacer(Modifier.height(8.dp))
        DateText("September 25, 2024")
        Spacer(Modifier.height(8.dp))
        LinkText("https://www.marvel.com/comics/")
        Spacer(Modifier.height(16.dp))
        SuggestionsText()
        HorizontalDivider(modifier = Modifier.height(8.dp))
        RelatedComics()
    }

}

@Composable
fun RelatedComics() {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {

    }
}

@Composable
fun SuggestionsText() {
    Text(text = "SUGERENCIAS", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
}

@Composable
fun LinkText(link: String) {
    Row {
        Icon(imageVector = Icons.Filled.Link, contentDescription = "Link", modifier = Modifier.align(Alignment.CenterVertically).padding(end = 8.dp))
        Text(text = link, fontSize = 16.sp, modifier = Modifier.align(Alignment.CenterVertically))
    }
}

@Composable
fun DateText(datePublish: String) {
    Row {
        Text(text = "PUBLISHED: ", fontSize = 16.sp)
        Text(text = datePublish, fontSize = 16.sp)
    }

}

@Composable
fun ComicText(comic: String) {
    Text(text = comic, fontSize = 24.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun ImageCharacter(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "Imagen de personaje",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(130.dp)
            .clip(
                CircleShape
            )
    )
}
