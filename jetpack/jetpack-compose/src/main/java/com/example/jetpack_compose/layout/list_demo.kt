package com.example.jetpack_compose.layout

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MessageList(messages: List<String>) {
    LazyColumn {
        items(messages) { message ->
            Text(message)
        }
    }
}

@Preview
@Composable
fun TextLazyColumn() {
    LazyColumn(Modifier.height(50.dp)) {
        // Add a single item
        item {
            Text(text = "First item")
        }

        // Add 5 items
        items(5) { index ->
            Text(text = "Item: $index")
        }

        // Add another single item
        item {
            Text(text = "Last item")
        }
    }

}