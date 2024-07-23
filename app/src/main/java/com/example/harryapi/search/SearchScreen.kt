package com.loki.harrypotterapp.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.harryapi.navigation.Screens
import com.example.harryapi.ui.theme.character_list.components.CharacterItem

@Composable
fun SearchScreen(
    popUpScreen: () -> Unit,
    openScreen: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {

    Column {

        SearchTopBar(
            onSearch = { viewModel.searchCharacterList(it) },
            onClose = { popUpScreen() }
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            if (viewModel.message.value.isNotEmpty()) {
                Text(
                    text = viewModel.message.value,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            if (viewModel.errorMessage.value.isNotEmpty()) {
                Text(
                    text = viewModel.errorMessage.value,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            if (viewModel.searchedList.value.isNotEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {

                    items(viewModel.searchedList.value) { character ->

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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    onSearch: (String) -> Unit,
    onClose: () -> Unit
) {

    var value by remember { mutableStateOf("") }

    TopAppBar(
        title = {

            SearchField(
                onSearch = {
                    onSearch(it)
                    value = it
                }
            )

        },
        actions = {
            IconButton(
                onClick = {
                    onClose()
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Cancel,
                    contentDescription = null
                )
            }
        },

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    onSearch: (String) -> Unit
) {

    val focusRequester = remember { FocusRequester() }

    var text by remember {
        mutableStateOf("")
    }

    TextField(
        value = text,
        onValueChange = {
            text = it
            onSearch(it)
        },
        placeholder = {
            Text(text = "Enter Name")
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,

        ),
        maxLines = 1,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        leadingIcon = {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
        }
    )
    
    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }
}