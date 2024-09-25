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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.sebasgrdev.marvelwiki.model.api.HeroClient
import com.sebasgrdev.marvelwiki.model.api.HeroServiceAPI
import com.sebasgrdev.marvelwiki.model.data.herodata.Result
import com.sebasgrdev.marvelwiki.model.domain.Character
import com.sebasgrdev.marvelwiki.viewmodel.CharactersViewModel

@Preview
@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = hiltViewModel()
) {
    val state by viewModel._characterValue.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)
    ) {
        items(state.characterList) { hero ->
            ItemHero(hero = hero)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getAllCharacters(0) // Initial
    }
}

@Composable
fun ItemHero(hero: Character) {
    Card(border = BorderStroke(2.dp, Color.Red), modifier = Modifier.width(200.dp).height(300.dp)) {
        Column {
            val imageUrl = "${hero.thumbnail}.${hero.thumbnailExt}"
            AsyncImage(
                model = imageUrl,
                contentDescription = hero.name,
                modifier = Modifier.fillMaxWidth().weight(1f),
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
            )
            TextButton(
                onClick = {},
                modifier = Modifier.align(Alignment.End),
            ) {
                Text(text = "Ver mas ")
                Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "")
            }
        }
    }
}
