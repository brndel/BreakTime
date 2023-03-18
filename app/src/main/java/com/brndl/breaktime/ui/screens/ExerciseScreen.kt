package com.brndl.breaktime.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import com.brndl.breaktime.R
import com.brndl.breaktime.data.BreaktimeDatabase
import com.brndl.breaktime.data.exercise.Exercise
import com.brndl.breaktime.ui.components.ExerciseView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseScreen(database: BreaktimeDatabase) {
    val dao = remember {
        database.exerciseDao()
    }
    val exercises by dao.getAll().collectAsState(initial = listOf());

    val composeScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            Text("Exercise list")
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                composeScope.launch {
                    dao.upsert(Exercise("Hello", "Description"))
                }
            }) {
                Icon(painter = painterResource(id = R.drawable.add), contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        LazyColumn() {
            items(exercises) {
                ExerciseView(exercise = it)
            }
        }
    }
}