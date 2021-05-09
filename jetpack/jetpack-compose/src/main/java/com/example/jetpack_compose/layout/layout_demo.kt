package com.example.jetpack_compose.layout

import android.graphics.drawable.shapes.RoundRectShape
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose.R

@Composable
fun ArtistCard(
    onClick: () -> Unit
) {
    val padding = 16.dp
    Column(
        Modifier
            .padding(padding)
            .clickable(onClick = onClick)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.touxiang),
                contentDescription = null,
                modifier = Modifier.height(60.dp)
                    .padding(10.dp)
                    .fillMaxHeight()
                    .clip(shape = CircleShape),
                alignment = Alignment.Center,
                contentScale = ContentScale.FillHeight
            )
            Column {
                Text("Alfred Sisley")
                Text("3 minutes ago")
            }
        }
        Spacer(Modifier.size(padding))
        Card(elevation = 4.dp) {
            Image(
                painter = painterResource(R.drawable.header),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(180.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun AlignInRow() {
    Row(
        modifier = Modifier
            .size(150.dp)
            .background(Color.Yellow)
            .background(Color.Yellow),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.size(50.dp).background(Color.Red))
        Box(Modifier.size(50.dp).background(Color.Blue))
    }
}

@Composable
fun PaddedComposeable() {
    Text("Hello World!", modifier = Modifier.background(Color.Green).padding(20.dp))
}

@Composable
fun SizedComposable() {
    Box(Modifier.size(100.dp, 100.dp).background(Color.Red))
}

@Composable
fun FixedSizeComposable() {
    // 如果指定的尺寸不符合来自父项的约束条件，则可能不会采用该尺寸。如果你希望可组合的尺寸固定不变，儿不考虑传入的约束条件，
    // 请使用 requireSize 修饰符
    Box(Modifier.size(90.dp, 150.dp).background(Color.Green)) {
        Box(Modifier.requiredSize(100.dp, 100.dp).background(Color.Red))
    }
}

@Composable
fun FillSizeComposable() {
    Box(Modifier.background(Color.Green).size(50.dp).padding(10.dp)) {
        Box(Modifier.background(Color.Blue).fillMaxSize())
    }
}

@Composable
fun MatchParentSizeComposable() {
    Box {
        // 这里如果使用 fillMaxSize 代替 mathParentSize，Spacer 将占用父项允许的所有可用空间，反过来是父项展开并填满所有可用空间
        Spacer(Modifier.matchParentSize().background(Color.Green))
        Text("Hello World")
    }
}

@Composable
fun TextWithPaddingFromBaseline() {
    Box(Modifier.background(Color.Yellow)) {
        // 如果要在文本基线上方添加内边距，以实现从布局顶部到基线保持特定的距离，请使用 paddingFromBaseline 修饰符
        Text("Hi there", Modifier.paddingFromBaseline(top = 32.dp))
    }
}

@Composable
fun OffsetComposable() {
    // offset 与 padding 的区别是，offset 不会改变其测量结果
    Box(Modifier.background(Color.Yellow).size(width = 150.dp, height = 70.dp)) {
        Text(
            "Layout offset modifier sample",
            Modifier.offset(x = 15.dp, y = 20.dp)
        )
    }
}