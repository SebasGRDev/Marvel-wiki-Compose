package com.sebasgrdev.marvelwiki.ui.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.gson.Gson
import com.sebasgrdev.marvelwiki.model.domain.character.Character
import com.sebasgrdev.marvelwiki.ui.CharacterList.CharacterListState
import com.sebasgrdev.marvelwiki.viewmodel.CharactersViewModel
import java.net.URLEncoder

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by viewModel.characterValue.collectAsState()
    var isLoanding by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        viewModel.getAllCharacters(0)
    }

    when (state.isLoanding) {
        true -> {
            isLoanding = true
            IsShimmerEffect(modifier)
        }
        false -> {
            isLoanding = false
            CharactersCards(modifier, state, navController)
        }
    }
}

@Composable
fun IsShimmerEffect(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(8.dp)
    ) {
        items(16) {
            Card(
                modifier = Modifier
                    .width(200.dp)
                    .height(300.dp)
                    .shimmerEffect()
            ) {
                Box(Modifier.fillMaxSize().height(200.dp).shimmerEffect())
            }
        }
    }
}

@Composable
fun CharactersCards(
    modifier: Modifier,
    state: CharacterListState,
    navController: NavHostController
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)
    ) {
        items(state.characterList) { hero ->
            ItemHero(hero = hero, navController = navController)
        }
    }
}

@Composable
fun ItemHero(hero: Character, navController: NavHostController) {
    Card(
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary), modifier = Modifier
            .width(200.dp)
            .height(300.dp)
    ) {
        Column {
            val imageUrl = "${hero.thumbnail}.${hero.thumbnailExt}"
            AsyncImage(
                model = imageUrl,
                contentDescription = hero.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop,
                onSuccess = {
                    Log.d("ItemHero", "Image loaded successfully: $imageUrl")
                },
                onError = {
                    Log.d("ItemHero", "Image loading failed: $imageUrl")
                }
            )
            Text(
                text = hero.name,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            TextButton(
                onClick = {
                    val encodedName = URLEncoder.encode(hero.name, "UTF-8")
                    val encodedThumbnail = URLEncoder.encode(hero.thumbnail, "UTF-8")
                    val encodedThumbnailExt = URLEncoder.encode(hero.thumbnailExt, "UTF-8")
                    val comicsJson = Gson().toJson(hero.comics)
                    val urlsJson = Gson().toJson(hero.urls)
                    val encodedComics = URLEncoder.encode(comicsJson, "UTF-8")
                    val encodedUrls = URLEncoder.encode(urlsJson, "UTF-8")

                    navController.navigate(
                        "detail/${hero.id}/$encodedName/$encodedThumbnail/$encodedThumbnailExt/$encodedComics/$encodedUrls"
                    )
                },
                modifier = Modifier.align(Alignment.End),
            ) {
                Text(text = "Ver mas ", color = MaterialTheme.colorScheme.primary)
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
