package com.ys.clone.disneycompose.ui.posters

import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryAdd
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.ys.clone.disneycompose.R
import com.ys.clone.disneycompose.extensions.visible
import com.ys.clone.disneycompose.model.Poster
import com.ys.clone.disneycompose.ui.main.MainViewModel
import com.ys.clone.disneycompose.ui.theme.Purple200

@Composable
fun Posters(
	viewModel: MainViewModel,
	selectPoster: (Long) -> Unit
) {
	val posters: List<Poster> by viewModel.posterList.collectAsState(initial = listOf())
	val isLoading: Boolean by viewModel.isLoading
	val selectedTab = DisneyHomeTab.getTabFromResource(viewModel.selectedTab.value)
	val tabs = DisneyHomeTab.values()

	ConstraintLayout {
		val (body, progress) = createRefs()

		Scaffold(
			backgroundColor = MaterialTheme.colors.primarySurface,
			topBar = { PosterAppBar() },
			modifier = Modifier.constrainAs(body) { top.linkTo(parent.top) },
			bottomBar = {
				BottomNavigation(
					backgroundColor = Purple200,
					modifier = Modifier
						.navigationBarsHeight(56.dp)
				) {
					tabs.forEach { tab ->
						BottomNavigationItem(
							icon = { Icon(imageVector = tab.icon, contentDescription = null) },
							label = { Text(text = stringResource(id = tab.title), color = Color.White) },
							selected = tab == selectedTab,
							onClick = { viewModel.selectTab(tab.title) },
							selectedContentColor = LocalContentColor.current,
							unselectedContentColor = LocalContentColor.current,
							modifier = Modifier.navigationBarsPadding()
						)
					}
				}
			}
		) { innerPadding ->
			val modifier = Modifier.padding(innerPadding)
			Crossfade(selectedTab) { destination ->
				when (destination) {
					DisneyHomeTab.HOME -> HomePosters(modifier, posters, selectPoster)
					DisneyHomeTab.RADIO -> RadioPosters(modifier, posters, selectPoster)
					DisneyHomeTab.LIBRARY -> LibraryPosters(modifier, posters, selectPoster)
				}
			}
		}

		CircularProgressIndicator(
			modifier = Modifier
				.constrainAs(progress) {
					top.linkTo(parent.top)
					bottom.linkTo(parent.bottom)
					start.linkTo(parent.start)
					end.linkTo(parent.end)
				}
				.visible(isLoading)
		)
	}
}

@Preview
@Composable
private fun PosterAppBar() {
	TopAppBar(
		elevation = 6.dp,
		backgroundColor = Purple200,
		modifier = Modifier.height(58.dp)
	) {
		Text(
			modifier = Modifier
				.padding(8.dp)
				.align(Alignment.CenterVertically),
			text = stringResource(id = R.string.app_name),
			color = Color.White,
			fontSize = 18.sp,
			fontWeight = FontWeight.Bold
		)
	}
}

enum class DisneyHomeTab(
	@StringRes val title: Int,
	val icon: ImageVector
) {
	HOME(R.string.menu_home, Icons.Filled.Home),
	RADIO(R.string.menu_radio, Icons.Filled.Radio),
	LIBRARY(R.string.menu_library, Icons.Filled.LibraryAdd);

	companion object {
		fun getTabFromResource(@StringRes resource: Int): DisneyHomeTab {
			return when(resource) {
				R.string.menu_radio -> RADIO
				R.string.menu_library -> LIBRARY
				else -> HOME
			}
		}
	}
}