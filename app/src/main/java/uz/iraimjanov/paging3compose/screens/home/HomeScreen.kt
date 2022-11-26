package uz.iraimjanov.paging3compose.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
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
import uz.iraimjanov.paging3compose.navigation.Screen
import uz.iraimjanov.paging3compose.screens.common.ListContent

@ExperimentalPagingApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {

    val getAllImages = homeViewModel.getAllImages.collectAsLazyPagingItems()

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (appBar, content) = createRefs()

        TopAppBar(
            modifier = Modifier
                .constrainAs(appBar) {
                    linkTo(start = parent.start, end = parent.end)
                    top.linkTo(anchor = parent.top)

                    width = Dimension.fillToConstraints
                    height = Dimension.value(64.dp)
                }
                .background(MaterialTheme.colorScheme.primary),
            onSearchClicked = {
                navController.navigate(Screen.Search.route)
            }
        )

        ListContent(modifier = Modifier.constrainAs(content) {
            linkTo(start = parent.start, end = parent.end)
            linkTo(top = appBar.bottom, bottom = parent.bottom)

            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints

        }, items = getAllImages)

    }

}

@Composable
fun TopAppBar(modifier: Modifier, onSearchClicked: () -> Unit) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (tv_title, image_add) = createRefs()

        Text(
            modifier = Modifier.constrainAs(tv_title) {
                width = Dimension.fillToConstraints
                top.linkTo(anchor = parent.top)
                bottom.linkTo(anchor = parent.bottom)
                start.linkTo(anchor = parent.start, margin = 16.dp)
                end.linkTo(anchor = image_add.start, margin = 8.dp)
            },
            text = "Home",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Image(
            modifier = Modifier
                .constrainAs(image_add) {
                    height = Dimension.value(24.dp)
                    width = Dimension.value(24.dp)
                    top.linkTo(anchor = parent.top)
                    bottom.linkTo(anchor = parent.bottom)
                    end.linkTo(anchor = parent.end, margin = 16.dp)
                }
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false, color = Color.White.copy(0.5f))
                ) {
                    onSearchClicked()
                },
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Plus",
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}

@ExperimentalPagingApi
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}