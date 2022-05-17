package com.ys.clone.disneycompose.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.palette.BitmapPalette
import com.ys.clone.disneycompose.R
import com.ys.clone.disneycompose.ui.theme.ShimmerHighLight

/**
 * 기본 [contentScale]을 설정하고 디즈니 포스터 이미지를 로드할 때 indicator를 표시하는 [CoilImage]의 래퍼.
 *
 * @CoilImage 참조 https://github.com/skydoves/landscapist#coil
 */
@Preview
@Composable
fun NetworkImage(
	@PreviewParameter(NetworkUrlPreviewProvider::class) url: String,
	modifier: Modifier = Modifier,
	circularRevealEnabled: Boolean = false,
	contentScale: ContentScale = ContentScale.Crop,
	bitmapPalette: BitmapPalette? = null
) {
	CoilImage(
		imageModel = url,
		modifier = modifier,
		contentScale = contentScale,
		circularReveal = CircularReveal(duration = 300).takeIf { circularRevealEnabled },
		bitmapPalette = bitmapPalette,
		previewPlaceholder = R.drawable.poster,
		shimmerParams = ShimmerParams(
			baseColor = MaterialTheme.colors.background,
			highlightColor = ShimmerHighLight,
			dropOff = 0.65f
		),
		failure = {
			Column(
				modifier = modifier,
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally,
			) {
				Text(
					text = "image request failed.",
					style = MaterialTheme.typography.body2
				)
			}
		},
	)
}