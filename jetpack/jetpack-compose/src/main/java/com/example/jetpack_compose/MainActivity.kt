package com.example.jetpack_compose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose.layout.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            NewStory()
//            ArtistCard {
//                Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
//            }
//            AlignInRow()
//            Column {
//                PaddedComposeable()
//                Spacer(Modifier.height(20.dp))
//                SizedComposable()
//                Spacer(Modifier.height(20.dp))
//                FixedSizeComposable()
//                Spacer(Modifier.height(20.dp))
//                FillSizeComposable()
//                Spacer(Modifier.height(20.dp))
//                MatchParentSizeComposable()
//                Spacer(Modifier.height(20.dp))
//                TextWithPaddingFromBaseline()
//                Spacer(Modifier.height(20.dp))
//                OffsetComposable()
//                Spacer(Modifier.height(20.dp))
//                FlexdComposable()
//                Spacer(Modifier.height(20.dp))
//                WithConstraintsComposable()
//        }
//            HomeScreen()
            Column {
//                ConstraintLayoutContent()
//                Spacer(Modifier.height(20.dp))
                DecoupledConstraintLayout()
            }
        }
    }
}


@Composable
fun NewStory() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.header),
            contentDescription = null,
            modifier = Modifier.height(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(16.dp))
        Text(
            "A day wandering through the sandhills " +
                    "in Shark Fin Cove, and a few of the sight I saw.",
            style = typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text("Davenport, California", style = typography.body2)
        Text("December 2018", style = typography.body2)
    }
}