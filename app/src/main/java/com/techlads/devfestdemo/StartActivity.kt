@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.devfestdemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.LocalContentColor
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.devfestdemo.compose.ComposeActivity
import com.techlads.devfestdemo.view.LeanbackActivity
import com.techlads.test.ui.theme.ComposeTheme

class StartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                StartScreen {
                    when (it) {
                        Screen.COMPOSE -> {
                            startActivity(Intent(this, ComposeActivity::class.java))
                        }

                        Screen.LEANBACK -> {
                            startActivity(Intent(this, LeanbackActivity::class.java))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StartScreen(startScreen: (Screen) -> Unit) {
    val radial = Brush.radialGradient(
        listOf(
            Color.Black,
            Color.Black.copy(alpha = 0.1f)
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(radial), contentAlignment = Center
    ) {
        Column {
            Text(text = "Select UI type", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = "This is a demo app for DevFest 2023, where we are showcasing how to use Compose in Android TV. and Compared it with Leanback. You can select any of the UI type and see the difference.",
                modifier = Modifier.width(300.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = LocalContentColor.current.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.size(50.dp))
            Row {
                Button(onClick = {
                    startScreen(Screen.COMPOSE)
                }, shape = ButtonDefaults.shape(RoundedCornerShape(8.dp))) {
                    Text(text = "Compose")
                }
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = {
                    startScreen(Screen.LEANBACK)
                }, shape = ButtonDefaults.shape(RoundedCornerShape(8.dp))) {
                    Text(text = "Leanback")
                }
            }
        }
    }
}

enum class Screen {
    COMPOSE, LEANBACK
}

@Preview
@Composable
private fun ButtonsPreview() {
    ComposeTheme {
        // A surface container using the 'background' color from the theme
        StartScreen {

        }
    }
}