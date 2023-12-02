@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.devfestdemo.compose

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.material3.CompactCard
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.ImmersiveList
import androidx.tv.material3.ImmersiveListScope
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.techlads.devfestdemo.data.DataModel.products
import com.techlads.devfestdemo.data.Product
import com.techlads.devfestdemo.utils.gradientOverlay


@Composable
fun ImmersiveMovies() {
    val gradientColor = MaterialTheme.colorScheme.surface
    var currentItemIndex by remember { mutableStateOf(0) }

    ImmersiveList(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        background = { index, _ ->
            BackgroundImage(index, gradientColor)
        }
    ) {
        HorizontalMovies(currentItemIndex) {
            currentItemIndex = it
        }
    }
}

@Composable
fun ImmersiveListScope.HorizontalMovies(
    currentItemIndex: Int,
    onItemFocus: (selectedIndex: Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val movie = remember(products, currentItemIndex) { products[currentItemIndex] }

        ProductDetail(Modifier.height(LocalConfiguration.current.screenHeightDp.times(0.5f).dp),movie = movie)

        TvLazyRow(modifier = Modifier.padding(start = 16.dp)) {
            items(products.size) { index ->
                CompactCard(modifier = Modifier
                    .immersiveListItem(index)
                    .onFocusChanged {
                        if (it.isFocused) {
                            onItemFocus(index)
                        }
                    }
                    .height(cardHeight)
                    .padding(cardSpacing), onClick = {}, image = {
                    AsyncImage(products[index].image, contentDescription = "")
                }, title = {})
            }
        }
    }
}

@Composable
fun ProductDetail(modifier: Modifier, movie: Product) {
    Column(
        modifier = modifier.padding(
            start = 32.dp, bottom = 32.dp
        ),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = movie.title, style = MaterialTheme.typography.displaySmall)
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(0.5f),
            text = movie.description,
            maxLines = 3,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
fun BackgroundImage(index: Int, gradientColor: Color) {
    Crossfade(
        targetState = products.get(index).backdropPath, label = "CrossFade"
    ) { posterUri ->
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier
                .fillMaxSize()
                .gradientOverlay(gradientColor)
        ) {
            AsyncImage(
                modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.times(0.7f).dp),
                model = ImageRequest.Builder(LocalContext.current).data(posterUri).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}
