package com.sebasgrdev.marvelwiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.sebasgrdev.marvelwiki.model.data.herodata.Url
import com.sebasgrdev.marvelwiki.ui.screens.topbar.TopBar
import com.sebasgrdev.marvelwiki.ui.screens.CharactersScreen
import com.sebasgrdev.marvelwiki.ui.screens.DetailScreen
import com.sebasgrdev.marvelwiki.ui.screens.topbar.TopBarDetail
import com.sebasgrdev.marvelwiki.ui.theme.MarvelWikiTheme
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarvelWikiTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        if (currentRoute(navController) == "characters") {
                            TopBar()
                        } else {
                            TopBarDetail(navController)
                        }
                    }) { innerPadding ->
                    NavigationComponent(navController = navController, innerPadding = innerPadding)
                }
            }
        }
    }

    @Composable
    private fun currentRoute(navController: NavHostController): String? {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        return navBackStackEntry?.destination?.route
    }

    @Composable
    fun NavigationComponent(navController: NavHostController, innerPadding: PaddingValues) {
        NavHost(navController = navController, startDestination = "characters") {
            composable("characters") { CharactersScreen(navController = navController, modifier = Modifier.padding(innerPadding)) }
            composable(
                "detail/{characterId}/{name}/{thumbnail}/{thumbnailExt}/{comics}/{urls}",
                arguments = listOf(
                    navArgument("characterId") { type = NavType.IntType },
                    navArgument("name") { type = NavType.StringType },
                    navArgument("thumbnail") { type = NavType.StringType },
                    navArgument("thumbnailExt") { type = NavType.StringType },
                    navArgument("comics") { type = NavType.StringType },
                    navArgument("urls") { type = NavType.StringType },
                )
            ) { backStackEntry ->
                val characterId = backStackEntry.arguments?.getInt("characterId") ?: -1
                val name = URLDecoder.decode(backStackEntry.arguments?.getString("name"), "UTF-8")
                val thumbnail = URLDecoder.decode(backStackEntry.arguments?.getString("thumbnail"), "UTF-8")
                val thumbnailExt = URLDecoder.decode(backStackEntry.arguments?.getString("thumbnailExt"), "UTF-8")
                val comics = Gson().fromJson(URLDecoder.decode(backStackEntry.arguments?.getString("comics"), "UTF-8"), Array<String>::class.java).toList()
                val urls = Gson().fromJson(URLDecoder.decode(backStackEntry.arguments?.getString("urls"), "UTF-8"), Array<Url>::class.java).toList()
                DetailScreen(
                    modifier = Modifier.padding(innerPadding),
                    characterId = characterId,
                    name = name,
                    thumbnail = thumbnail,
                    thumbnailExt = thumbnailExt,
                    comics = comics,
                    urls = urls
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MarvelWikiTheme {
        TopBar()
    }
}