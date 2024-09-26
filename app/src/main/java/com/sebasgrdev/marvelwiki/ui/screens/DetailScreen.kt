package com.sebasgrdev.marvelwiki.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sebasgrdev.marvelwiki.model.data.herodata.Url
import com.sebasgrdev.marvelwiki.model.domain.comic.Comic
import com.sebasgrdev.marvelwiki.viewmodel.CharactersViewModel

@Composable
fun DetailScreen(
    viewModel: CharactersViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    characterId: Int,
    name: String,
    thumbnail: String,
    thumbnailExt: String,
    comics: List<String>,
    urls: List<Url>
) {
    val state by viewModel._comicValue.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getDetailComic(characterId)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = "$thumbnail.$thumbnailExt",
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(130.dp)
                .clip(
                    CircleShape
                ),
        )

        Spacer(Modifier.height(16.dp))
        Text(
            text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        val comicTitle = state.comicList.firstOrNull()?.title ?: ""

        if (comicTitle.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Text(text = comicTitle)
        }

        Spacer(Modifier.height(8.dp))
        val comicDate = state.comicList.firstOrNull()?.date?.firstOrNull() ?: ""
        val date = if (comicDate.toString().isNotEmpty()) {
            val pattern = """date=(\d{4}-\d{2}-\d{2})""".toRegex()
            val matchResult = pattern.find(comicDate.toString())
            matchResult?.groupValues?.get(1) ?: ""
        } else {
            ""
        }
        DateText(date)


        val firstUrl = urls.firstOrNull()?.url ?: ""
        Spacer(Modifier.height(8.dp))
        LinkText(firstUrl)

        Spacer(Modifier.height(16.dp))
        SuggestionsText()
        HorizontalDivider(
            modifier = Modifier.height(8.dp),
            color = MaterialTheme.colorScheme.onSurface
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(state.comicList) { comic ->
                ComicItem(comic = comic)
            }
        }

    }
}

@Composable
fun ComicItem(comic: Comic) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
    ) {
        AsyncImage(
            model = "${comic.thumbnail?.path}.${comic.thumbnail?.extension}",
            contentDescription = comic.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun SuggestionsText() {
    Text(
        text = "SUGERENCIAS",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun LinkText(link: String) {
    Row {
        Icon(
            imageVector = Icons.Filled.Link,
            contentDescription = "Link",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 8.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = link,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterVertically),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun DateText(datePublish: Any) {
    Row {
        Text(text = "PUBLISHED: ", fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)
        Text(
            text = datePublish.toString(),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }

}

@Composable
fun ComicText(comic: String) {
    Text(text = comic, fontSize = 24.sp, fontWeight = FontWeight.Bold)
}

