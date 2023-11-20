package com.example.jetanimeapp.ui.theme.component.header

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onSearchIconClick: () -> Unit,
    onClearIconClick: () -> Unit,
    hint: String = "Search...",
    backgroundColor: Color = MaterialTheme.colorScheme.background,

    ) {
    Log.d("copy", MaterialTheme.colorScheme.onBackground.toString() )
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .height(50.dp)
            .clip(CircleShape),
    ) {
        TextField(
            value = searchQuery,
            modifier = Modifier.padding(0.dp),
            onValueChange = {
                onSearchQueryChange(it)
            },
            textStyle = TextStyle(fontSize = MaterialTheme.typography.labelLarge.fontSize),
            placeholder = {
                Text(
                    text = hint,
                    style = TextStyle.Default.copy(
                        color = MaterialTheme.colorScheme.onBackground.copy(
                            alpha = 0.6f
                        )
                    )
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchIconClick()
                }
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(12.dp)
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable { onClearIconClick() }
                    )
                }
            },
        )
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(
        searchQuery = "Cari",
        onSearchQueryChange = {},
        onSearchIconClick = { /*TODO*/ },
        onClearIconClick = { /*TODO*/ })
}