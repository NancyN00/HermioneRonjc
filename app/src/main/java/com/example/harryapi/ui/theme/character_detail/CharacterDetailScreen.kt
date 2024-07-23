package com.loki.harrypotterapp.ui.character_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.harryapi.domain.models.CharacterItem
import com.example.harryapi.util.Constants

@Composable
fun CharacterDetailScreen(
    popUpScreen: () -> Unit,
    name: String,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {

    val character by viewModel.characterDetail.collectAsStateWithLifecycle()

    Column {

        DetailTopBar(title = name) {
            popUpScreen()
        }

        if (character.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        if (character.message.isNotEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = character.message,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        Column(Modifier.verticalScroll(rememberScrollState())) {

            character.characterDetail?.let {

                TopSection(
                    characterItem = it,
                    modifier = Modifier.padding(16.dp)
                )

                Text(
                    text = "More Information",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                MoreDetailsSection(
                    characterItem = it,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    title: String,
    onIconClick: () -> Unit
) {

    TopAppBar(
        title = {
            Text(text = title, fontSize = 18.sp)
        },
        navigationIcon = {
            IconButton(
                onClick = { onIconClick() }
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    characterItem: CharacterItem
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(4.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(modifier = Modifier.fillMaxWidth()) {
                if (characterItem.hogwartsStudent) {
                    Text(
                        text = "Student",
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.align(Alignment.TopEnd)
                    )
                }

                if (characterItem.hogwartsStaff) {
                    Text(
                        text = "Staff",
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic,
                        color =  MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.align(Alignment.TopEnd)
                    )
                }
            }


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(shape = CircleShape, color = Color.Transparent),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = if (!characterItem.image.isEmpty()) characterItem.image else Constants.DEFAULT_IMG_URL,
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                )
            }

            Text(
                text = characterItem.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                    
                    Text(text = "Actor", color = MaterialTheme.colorScheme.onSurface)
                    
                    Text(
                        text = characterItem.actor,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 12.sp
                    )
                }


                Spacer(modifier = Modifier.weight(1f))

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ViewAgenda,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )

                    Text(text = "Gender", color = MaterialTheme.colorScheme.onSurface)

                    Text(
                        text = characterItem.gender,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Rounded.CalendarMonth,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )

                    Text(text = "Birth Year", color = MaterialTheme.colorScheme.onSurface)

                    Text(
                        text = if (characterItem.yearOfBirth == 0) "Not indicated" else characterItem.yearOfBirth.toString(),
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun MoreDetailsSection(
    modifier: Modifier = Modifier,
    characterItem: CharacterItem
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(4.dp)
            )
    ) {

        Column {
            DetailRow(title = "Ancestry", desc = characterItem.ancestry)
            DetailRow(title = "Species", desc = characterItem.species)
            DetailRow(title = "House", desc = characterItem.house)
            DetailRow(title = "Patronus", desc = if (characterItem.patronus.isEmpty()) "No Patronous" else characterItem.patronus)
            DetailRow(title = "Eye Color", desc = characterItem.eyeColour)
            DetailRow(title = "Hair Color", desc = characterItem.hairColour)
        }
    }
}

@Composable
fun DetailRow(
    modifier: Modifier = Modifier,
    title: String,
    desc: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(vertical = 16.dp, horizontal = 12.dp)
    ) {

        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = desc,
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}