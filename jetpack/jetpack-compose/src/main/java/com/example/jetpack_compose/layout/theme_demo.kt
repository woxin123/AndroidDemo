package com.example.jetpack_compose.layout

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MaterialThemeColor() {
    Text("Hello theming", color = MaterialTheme.colors.primary)
}