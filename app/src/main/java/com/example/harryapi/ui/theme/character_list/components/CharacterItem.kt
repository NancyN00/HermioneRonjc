package com.example.harryapi.ui.theme.character_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.harryapi.domain.models.CharacterItem
import com.example.harryapi.util.Constants.DEFAULT_IMG_URL
import com.google.android.material.chip.Chip

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    characterItem: CharacterItem,
    onItemClick: (CharacterItem) -> Unit = {}
) {


    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(characterItem) }
            .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(4.dp))
    ) {

        Row (
            horizontalArrangement = Arrangement.Center
        ) {

            Box(modifier = Modifier.size(150.dp) ) {


                Image(
                    painter = rememberImagePainter(
                        data = if (!characterItem.image.isEmpty()) characterItem.image else DEFAULT_IMG_URL,
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = characterItem.name,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                if (characterItem.ancestry.isNotEmpty()) {
                    Text(
                        text = characterItem.ancestry,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                if (characterItem.actor.isNotEmpty()) {
                    Text(
                        text = characterItem.actor,
                        fontStyle = FontStyle.Italic,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.BottomEnd
//                ) {
//
//                    val color = if (characterItem.wizard) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onError
//
//
//                    Chip(
//                        onClick = { /*TODO*/ },
//                    ) {
//                        Text(
//                            text = if (characterItem.wizard) "Wizard" else "Not Wizard",
//                            color = color
//                        )
//                    }
//                }
            }
        }
    }
}