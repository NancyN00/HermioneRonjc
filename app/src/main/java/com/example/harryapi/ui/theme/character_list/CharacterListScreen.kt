package com.example.harryapi.ui.theme.character_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.harryapi.navigation.Screens
import com.example.harryapi.ui.theme.character_list.components.CharacterItem

@Composable
fun CharacterListScreen(
    openScreen: (String) -> Unit,
    viewModel: CharacterListViewModel = hiltViewModel()
) {

    val characters by viewModel.characterListState.collectAsStateWithLifecycle()

    Column {

        HomeTopBar(
            title = "Harry Potter",
            onClick = {
                openScreen(Screens.SearchScreen.route)
            }
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            if (characters.isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            if (characters.message.isNotEmpty()) {
                Text(
                    text = characters.message,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {

                items(characters.characterList) { character ->

                    CharacterItem(
                        characterItem = character,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        onItemClick = {
                            openScreen(Screens.DetailScreen.navWithCharacterId(it.id, it.name))
                        }
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    title: String,
    onClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title, fontSize = 18.sp)
        },
        actions = {
            IconButton(
                onClick = {
                    onClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null
                )
            }
        }
    )
}