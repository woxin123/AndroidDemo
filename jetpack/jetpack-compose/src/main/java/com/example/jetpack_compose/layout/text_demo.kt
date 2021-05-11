package com.example.jetpack_compose.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose.R

@Preview
@Composable
fun SimpleText() {
    Text("Hello World!")
}

@Preview
@Composable
fun StringResourceText() {
    Text(stringResource(R.string.hello_world))
}

@Preview
@Composable
fun BlueText() {
    Text("Hello World!", color = Color.Blue)
}

@Preview
@Composable
fun ItalicText() {
    Text("Hello World", fontStyle = FontStyle.Italic)
}

@Preview
@Composable
fun BoldText() {
    Text("Hello World", fontWeight = FontWeight.Bold)
}

@Preview(showBackground = true)
@Composable
fun CenterText() {
    Text(
        "Hello World", textAlign = TextAlign.Center,
        modifier = Modifier.width(150.dp)
    )
}

val batmfaFonts = FontFamily(
    Font(R.font.batmfa_1)
)

@Preview
@Composable
fun DifferentFonts() {
    Column {
        Text("Hello World", fontFamily = FontFamily.Serif)
        Text("Hello World", fontFamily = FontFamily.SansSerif)
        Text("Hello World", fontFamily = batmfaFonts)
    }
}

@Preview(showBackground = true)
@Composable
fun MultipleStyleInText() {
    Text(buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Blue)) {
            append('H')
        }
        append("ello ")

        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
            append("W")
        }
        append("ord")
    })
}

@Preview(showBackground = true)
@Composable
fun ParagraphStyle() {
    Text(buildAnnotatedString {
        withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("Hello\n")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                append("World\n")
            }
            append("Compose")
        }
    })
}

@Preview(showBackground = true)
@Composable
fun LongText() {
    Text("hello".repeat(50), maxLines = 2)
}

@Preview(showBackground = true)
@Composable
fun OverflowedText() {
    Text("Hello Compose".repeat(50), maxLines = 2, overflow = TextOverflow.Ellipsis)
}

@Preview(showBackground = true)
@Composable
fun SelectableText() {
    SelectionContainer {
        Text("This text is selectable")
    }
}

@Preview(showBackground = true)
@Composable
fun PartiallySelectableText() {
    SelectionContainer {
        Column {
            Text("This text is selectable")
            Text("This one too")
            Text("This one as well")
            DisableSelection {
                Text("But not this one")
                Text("Neither this one")
            }
            Text("But again, you can select this one")
            Text("And this one too")
        }
    }
}