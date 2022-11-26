package uz.iraimjanov.paging3compose.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import uz.iraimjanov.paging3compose.R
import uz.iraimjanov.paging3compose.screens.common.ListContent

@ExperimentalPagingApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {
    val searchTextState by searchViewModel.searchTextState
    val searchedImages = searchViewModel.searchedImages.collectAsLazyPagingItems()

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (appBar, content) = createRefs()

        SearchAppBar(
            modifier = Modifier.constrainAs(appBar) {
                linkTo(start = parent.start, end = parent.end)
                top.linkTo(parent.top)

                width = Dimension.fillToConstraints
                height = Dimension.value(64.dp)

            },
            text = searchTextState,
            onTextChanged = {
                searchViewModel.updateSearchTextState(newValue = it)
            },
            onCloseClicked = {
                navController.popBackStack()
            },
            onSearchClicked = {
                searchViewModel.searchHeroes(query = it)
            },
        )

        ListContent(modifier = Modifier.constrainAs(content) {
            linkTo(start = parent.start, end = parent.end)
            linkTo(top = appBar.bottom, bottom = parent.bottom)

            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints

        }, items = searchedImages)

    }
}

@ExperimentalMaterial3Api
@Composable
fun SearchAppBar(
    modifier: Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onTextChanged(it) },
            placeholder = {
                Text(
                    text = "Search here...",
                    color = Color.White.copy(0.4f),
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = Color.White,
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(0.4f), onClick = {}
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChanged("")
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cross),
                        contentDescription = "Close",
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = androidx.compose.ui.text.input.ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearchClicked(text) }
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                cursorColor = Color.White.copy(0.4f),

                )
        )
    }
}

@ExperimentalPagingApi
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(rememberNavController())
}