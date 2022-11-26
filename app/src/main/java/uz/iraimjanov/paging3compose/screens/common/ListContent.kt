package uz.iraimjanov.paging3compose.screens.common

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uz.iraimjanov.paging3compose.R
import uz.iraimjanov.paging3compose.model.UnsplashImage
import uz.iraimjanov.paging3compose.model.Urls
import uz.iraimjanov.paging3compose.model.User
import uz.iraimjanov.paging3compose.model.UserLinks
import uz.iraimjanov.paging3compose.ui.theme.Red

@Composable
fun ListContent(modifier: Modifier, items: LazyPagingItems<UnsplashImage>) {
    println("Error -> ${items.itemSnapshotList}")
    LazyColumn(
        modifier = modifier,
    ) {
        items(items = items, key = { item: UnsplashImage -> item.id }) { value: UnsplashImage? ->
            value?.let {
                UnsplashItem(unsplashImage = it)
            }
        }
    }
}

@Composable
fun UnsplashItem(unsplashImage: UnsplashImage) {
    val context = LocalContext.current
    ConstraintLayout(
        modifier = Modifier
            .clickable {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://unsplash.com/@${unsplashImage.user.username}?utm_source=DemoApp&utm_medium=referral")
                )
                startActivity(context, browserIntent, null)
            }
            .fillMaxWidth()
            .aspectRatio(1f / 1f),
    ) {
        val (image, view, ly_header) = createRefs()
        AsyncImage(
            model = ImageRequest.Builder(context = context)
                .data(data = unsplashImage.urls.regular)
                .crossfade(durationMillis = 1000)
                .placeholder(drawableResId = R.drawable.ic_placeholder)
                .error(drawableResId = R.drawable.ic_placeholder)
                .build(),
            contentDescription = "Image",
            modifier = Modifier
                .constrainAs(image) {
                    linkTo(parent.start, parent.end)
                    linkTo(parent.top, parent.bottom)

                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            contentScale = ContentScale.Crop,
        )
        Surface(
            modifier = Modifier
                .constrainAs(view) {
                    bottom.linkTo(anchor = parent.bottom)
                    start.linkTo(anchor = parent.start)
                    end.linkTo(anchor = parent.end)
                }
                .fillMaxWidth()
                .height(40.dp)
                .alpha(0.6f),
            color = Color.Black
        ) {}
        ConstraintLayout(
            modifier = Modifier
                .constrainAs(ly_header) {
                    bottom.linkTo(anchor = parent.bottom)
                    start.linkTo(anchor = parent.start)
                    end.linkTo(anchor = parent.end)
                }
                .fillMaxWidth()
                .height(40.dp),
        ) {
            val (tv_username, image_like, tv_like_count) = createRefs()

            Text(
                modifier = Modifier.constrainAs(tv_username) {
                    width = Dimension.fillToConstraints
                    start.linkTo(anchor = parent.start, margin = 16.dp)
                    top.linkTo(anchor = parent.top)
                    bottom.linkTo(anchor = parent.bottom)
                    end.linkTo(anchor = image_like.start, margin = 16.dp)
                },
                text = buildAnnotatedString {
                    append("Photo by ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                        append(unsplashImage.user.username)
                    }
                    append(" on Unsplash")
                },
                color = Color.White,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Image(
                modifier = Modifier.constrainAs(image_like) {
                    width = Dimension.value(24.dp)
                    height = Dimension.value(24.dp)
                    top.linkTo(anchor = parent.top)
                    bottom.linkTo(anchor = parent.bottom)
                    end.linkTo(anchor = tv_like_count.start, margin = 16.dp)
                },
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = "Image Like",
                colorFilter = ColorFilter.tint(Red)
            )

            Text(
                modifier = Modifier.constrainAs(tv_like_count) {
                    top.linkTo(anchor = parent.top)
                    bottom.linkTo(anchor = parent.bottom)
                    end.linkTo(anchor = parent.end, margin = 16.dp)
                },
                text = unsplashImage.likes.toString(),
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun UnsplashItemPreview() {
    UnsplashItem(
        unsplashImage = UnsplashImage(
            id = "1",
            urls = Urls(regular = ""),
            likes = 100,
            user = User(username = "Iqboljon", userLinks = UserLinks(html = ""))
        )
    )
}