package com.example.jetpack_compose.layout

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun ClickableSample() {
    val count = remember {
        mutableStateOf(0)
    }
    // content that you want to make clickable
    Text(text = count.value.toString(),
        modifier = Modifier.clickable { count.value += 1 }
    )
}