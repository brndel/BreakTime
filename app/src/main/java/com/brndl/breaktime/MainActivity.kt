package com.brndl.breaktime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.brndl.breaktime.data.BreaktimeDatabase
import com.brndl.breaktime.ui.screens.ExerciseScreen
import com.brndl.breaktime.ui.screens.HomeScreen
import com.brndl.breaktime.ui.theme.BreakTimeTheme

sealed class Screen(val route: String, @StringRes val nameId: Int, @DrawableRes val iconId: Int) {
    object Home : Screen("home", R.string.home, R.drawable.home)
    object Exercise : Screen("exercise", R.string.exercise, R.drawable.exercise)
    companion object {
        val all = listOf(Home, Exercise)
    }
}

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = BreaktimeDatabase.getDatabase(this)

        setContent {
            BreakTimeTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            for (screen in Screen.all) {
                                NavigationBarItem(
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = { navController.navigate(screen.route) },
                                    label = { Text(resources.getString(screen.nameId)) },
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = screen.iconId),
                                            contentDescription = null
                                        )
                                    })
                            }
                        }
                    },

                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = "home", modifier = Modifier.padding(innerPadding)) {
                        composable("home") { HomeScreen(database = db) }
                        composable("exercise") { ExerciseScreen(database = db) }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}