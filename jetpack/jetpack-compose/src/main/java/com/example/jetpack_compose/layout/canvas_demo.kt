package com.example.jetpack_compose.layout

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun CanvasSample() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawLine(
            start = Offset(x = canvasWidth, y = 0F),
            end = Offset(x = 0F, y = canvasHeight),
            color = Color.Blue,
            strokeWidth = 5F
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DrawCircle() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawCircle(
            color = Color.Blue,
            center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
            radius = size.minDimension / 4
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DrawScopeSampleInset() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        // 可以通过 DrawScope.inset() 函数来调整当前作用域的默认参数，以更改绘图边界并相应地转换绘图
        inset(50F, 30F) {
            val canvasQuadrantSize = size / 2F
            drawRect(
                color = Color.Green,
                size = canvasQuadrantSize
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawScopeSampleRotate() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasSize = size
        val canvasWidth = size.width
        val canvasHeight = size.height
        rotate(degrees = 45F) {
            drawRect(
                color = Color.Gray,
                topLeft = Offset(x = canvasWidth / 3F, y = canvasHeight / 3F),
                size = canvasSize / 3F
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawScopeSampleTransform() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasSize = size
        val canvasWidth = size.width
        val canvasHeight = size.height
        withTransform({
            translate(left = canvasWidth / 5F)
            rotate(degrees = 45F)
        }) {
            drawRect(
                color = Color.Gray,
                topLeft = Offset(x = canvasWidth / 3F, y = canvasHeight / 3F),
                size = canvasSize / 3F
            )
        }
    }
}