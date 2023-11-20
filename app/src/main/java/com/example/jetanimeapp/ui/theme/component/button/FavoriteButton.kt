package com.example.jetanimeapp.ui.theme.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetanimeapp.R


@Composable
fun FavoriteButton(
    count: Int,
    navigateToFavorite: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
    ) {
        IconButton(
            onClick = {
                navigateToFavorite()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Favorite ,
                contentDescription = stringResource(id = R.string.favorite)
            )
        }

        if(count > 0) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .align(Alignment.TopEnd),
                contentAlignment = Alignment.Center,
            ){
                Text(
                    text = count.toString(),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun FavoriteButtonPreview() {
    FavoriteButton(count = 1, navigateToFavorite = {})
}