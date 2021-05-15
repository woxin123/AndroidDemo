package com.example.jetpack_compose.layout

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

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

@Preview(showBackground = true)
@Composable
fun ScrollBoxes() {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .size(100.dp)
            .verticalScroll(rememberScrollState())
    ) {
        repeat(10) {
            Text(text = "Item $it", modifier = Modifier.padding(2.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScrollBoxesSmooth() {

    // Smoothly scroll 100px on first composition
    val state = rememberScrollState()
    LaunchedEffect(key1 = Unit, block = {
        state.animateScrollTo(100)
    })
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .size(100.dp)
            .padding(horizontal = 8.dp)
            .verticalScroll(state)
    ) {
        repeat(10) {
            Text(text = "Item $it", modifier = Modifier.padding(2.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScrollableSample() {
    // actual composable state
    var offset = remember { mutableStateOf(0F) }
    Box(
        modifier = Modifier
            .size(100.dp)
            .scrollable(
                orientation = Orientation.Vertical,
                // Scrollable state: describes how to comsume
                // scrolling delta and update offset
                state = rememberScrollableState { delta ->
                    offset.value += delta
                    delta
                }
            )
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(text = offset.value.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun NestedScrollSample() {
    val gradient = Brush.verticalGradient(0F to Color.Gray, 1000F to Color.White)
    Box(
        modifier = Modifier
            .background(Color.LightGray)
            .height(240.dp)
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ) {
        Column {
            repeat(6) {
                Box(
                    modifier = Modifier
                        .height(128.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Scroll here",
                        modifier = Modifier
                            .border(12.dp, Color.DarkGray)
                            .background(brush = gradient)
                            .padding(24.dp)
                            .height(150.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DraggableSample() {
    var offsetX = remember {
        mutableStateOf(0F)
    }
    Text(
        text = "Drag me",
        modifier = Modifier
            .offset { IntOffset(offsetX.value.roundToInt(), 0) }
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    offsetX.value += delta
                }
            )
    )
}

@Preview
@Composable
fun DraggableSample2() {
    Box(modifier = Modifier.fillMaxSize()) {
        var offsetX = remember {
            mutableStateOf(0F)
        }
        var offsetY = remember {
            mutableStateOf(0F)
        }
        Box(modifier = Modifier
            .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
            .background(Color.Blue)
            .size(50.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consumeAllChanges()
                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                }
            }
        )
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun SwipeableSample() {
    val width = 96.dp
    val squareSize = 48.dp

    val swipeableState = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) {
        squareSize.toPx()
    }
    val anchors = mapOf(0F to 0, sizePx to 1) // Maps anchor points (in px) to state

    Box(modifier = Modifier
        .width(width)
        .swipeable(
            state = swipeableState,
            anchors = anchors,
            thresholds = { _, _ -> FractionalThreshold(0.3F) },
            orientation = Orientation.Horizontal
        )
        .background(Color.LightGray)
    ) {
        Box(modifier = Modifier
            .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
            .size(squareSize)
            .background(Color.DarkGray)
        )
    }
}

@Preview
@Composable
fun TransformableSample() {
    // set up all transformation states
    val scale = remember {
        mutableStateOf(1F)
    }
    val rotation = remember {
        mutableStateOf(0F)
    }
    val offset = remember {
        mutableStateOf(Offset.Zero)
    }
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale.value  *= zoomChange
        rotation.value += rotationChange
        offset.value += offsetChange
    }
    Box(modifier = Modifier
        // apply other transformations like rotation and zoom
        // on the pizza slice emoji
        .graphicsLayer {
            scaleX = scale.value
            scaleY = scale.value
            rotationZ = rotation.value
            translationX = offset.value.x
            translationY = offset.value.y
        }
        // add transformable to listen to multitouch transformation events
        .transformable(state = state, enabled = true)
        .background(Color.Blue)
        .fillMaxSize()
    )
}