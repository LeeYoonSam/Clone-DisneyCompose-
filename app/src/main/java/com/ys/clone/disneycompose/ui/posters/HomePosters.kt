package com.ys.clone.disneycompose.ui.posters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.statusBarsPadding
import com.ys.clone.disneycompose.model.Poster
import com.ys.clone.disneycompose.ui.custom.StaggeredVerticalGrid
import com.ys.clone.disneycompose.ui.theme.CloneDisneyComposeTheme
import com.ys.clone.disneycompose.utils.NetworkImage

@Composable
fun HomePosters(
	modifier: Modifier = Modifier,
	posters: List<Poster>,
	selectPoster: (Long) -> Unit
) {
	Column(
		modifier = modifier
			.statusBarsPadding()
			.verticalScroll(rememberScrollState())
			.background(MaterialTheme.colors.background)
	) {
		StaggeredVerticalGrid(
			modifier = Modifier.padding(4.dp),
			maxColumnWidth = 220.dp
		) {
			posters.forEach { poster ->
				HomePoster(
					poster = poster,
					selectPoster = selectPoster
				)
			}
		}
	}
}

@Composable
fun HomePoster(
	modifier: Modifier = Modifier,
	poster: Poster,
	selectPoster: (Long) -> Unit = {}
) {
	Surface(
		modifier = modifier
			.padding(4.dp)
			.clickable(
				onClick = { selectPoster(poster.id) }
			),
		color = MaterialTheme.colors.onBackground,
		elevation = 8.dp,
		shape = RoundedCornerShape(8.dp)
	) {
		ConstraintLayout {
			val (image, title, content) = createRefs()
			NetworkImage(
				modifier = Modifier
					.aspectRatio(0.8f)
					.constrainAs(image) {
						centerHorizontallyTo(parent)
						top.linkTo(parent.top)
					},
				url = poster.poster
			)

			Text(
				modifier = Modifier
					.constrainAs(title) {
						centerHorizontallyTo(parent)
						top.linkTo(image.bottom)
					}
					.padding(8.dp),
				text = poster.name,
				style = MaterialTheme.typography.h2,
				textAlign = TextAlign.Center,
			)

			Text(
				modifier = Modifier
					.constrainAs(content) {
						centerHorizontallyTo(parent)
						top.linkTo(title.bottom)
					}
					.padding(horizontal = 8.dp)
					.padding(bottom = 12.dp),
				text = poster.playtime,
				style = MaterialTheme.typography.body1,
				textAlign = TextAlign.Center,
			)
		}
	}
}

@Composable
@Preview(name = "HomePoster Light Theme")
private fun HomePosterPreviewLight() {
	CloneDisneyComposeTheme(darkTheme = false) {
		HomePoster(
			poster = Poster.mock()
		)
	}
}

@Composable
@Preview(name = "HomePoster Dark Theme")
private fun HomePosterPreviewDark() {
	CloneDisneyComposeTheme(darkTheme = true) {
		HomePoster(
			poster = Poster.mock()
		)
	}
}

