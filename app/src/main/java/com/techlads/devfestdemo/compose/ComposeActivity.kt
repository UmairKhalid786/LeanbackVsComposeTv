@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.devfestdemo.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.techlads.test.ui.theme.ComposeTheme

val cardSpacing = 10.dp
val cardHeight = 200.dp


class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ImmersiveMovies()
                }
            }
        }
    }
}
