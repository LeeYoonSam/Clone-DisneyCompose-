package com.ys.clone.disneycompose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.CompositionLocalProvider
import com.skydoves.landscapist.coil.LocalCoilImageLoader
import com.ys.clone.disneycompose.ui.root.RootViewModel
import com.ys.clone.disneycompose.ui.theme.CloneDisneyComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	@VisibleForTesting
	internal val viewModel: RootViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			CompositionLocalProvider(LocalCoilImageLoader provides viewModel.imageLoader) {
				CloneDisneyComposeTheme {
					DisneyMainScreen()
				}
			}
		}
	}
}