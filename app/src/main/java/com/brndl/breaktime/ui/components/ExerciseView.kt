package com.brndl.breaktime.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.brndl.breaktime.data.exercise.Exercise

@Composable
fun ExerciseView(exercise: Exercise) {
    Surface(
        Modifier.fillMaxWidth()
    ) {
        Column() {
            Text(text = exercise.name)
            Text(text = exercise.description)
        }
    }
}