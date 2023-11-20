@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.jetanimeapp

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetanimeapp.Screen.detail.DetailScreen
import com.example.jetanimeapp.Screen.home.HomeScreen
import com.example.jetanimeapp.navigation.NavigationItem
import com.example.jetanimeapp.navigation.Screen
import com.example.jetanimeapp.ui.theme.JetAnimeAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JetAnimeApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    //cek misal yg route saat ini bkn detail reward bakal muncul
    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailAnime.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { animeId ->
                        //pindah ke screen detail reward dg kirim parameter animeId(int)
                        navController.navigate(Screen.DetailAnime.createRoute(animeId))
                        Log.d("route", animeId.toString())
                    }
                )
            }

            composable(
                route = Screen.DetailAnime.route,
                arguments = listOf(navArgument("animeId") {
                    type = NavType.IntType
                }),
            )
            {
                val id = it.arguments?.getInt("animeId") ?: -1
                DetailScreen(
                    animeId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
//    val groupAnime =
//        sortedMapOf(AnimeData.anime)
}


@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navigationItems: List<NavigationItem> = listOf(
            NavigationItem(
                title = stringResource(R.string.home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                selected = false,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = { Text(item.title) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetAnimeAppPreview() {
    JetAnimeAppTheme {
        JetAnimeApp()
    }
}

