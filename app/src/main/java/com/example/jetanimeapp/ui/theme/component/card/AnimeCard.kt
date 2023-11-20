package com.example.jetanimeapp.ui.theme.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun AnimeCard(
    modifier: Modifier = Modifier,
    title: String,
    name: String,
    photoUrl: String,
    description: String,
) {
    Card(
        modifier = modifier
            .height(280.dp)
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        Column(
            modifier = modifier
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = title,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Text(
                    text = name,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Normal,
                    )
                )
                Text(
                    text = description,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        fontWeight = FontWeight.Normal,
                        color = Color.LightGray
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AnimeCardPreview() {
    AnimeCard(
        title = "Kaichou Wa Maid Sama",
        name = "Usui Takumi",
        photoUrl = "https://th.bing.com/th/id/R.43b49140c93c5b2cef4efed1ae23638f?rik=UCpVgsEzA%2b%2brAA&riu=http%3a%2f%2fimages4.fanpop.com%2fimage%2fphotos%2f22900000%2fUsui-usui-takumi-22961175-1024-640.jpg&ehk=PSIXZjNQfAWkswtLyf%2bDO%2fUZ16lKOG2Gz0DkCtMLbdc%3d&risl=&pid=ImgRaw&r=0",
        description = "Discover the enchanting world of 'Kaichou Wa Maid Sama,' a delightful anime series that seamlessly blends romance, comedy, and school life. Follow the charismatic student council president, Misaki Ayuzawa, as she navigates the challenges of maintaining order at Seika High, all while secretly working at a maid cafe. Immerse yourself in a tale of love, humor, and self-discovery, as Misaki's worlds collide in this captivating anime filled with endearing characters and heartwarming moments. Join us on a journey where love transcends societal expectations, and laughter is the best medicine. Watch 'Kaichou Wa Maid Sama' for a delightful escape into the joys and complexities of high school life."
    )
}
