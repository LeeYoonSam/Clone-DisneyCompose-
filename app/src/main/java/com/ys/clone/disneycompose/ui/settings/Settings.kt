package com.ys.clone.disneycompose.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun Settings(
	modifier: Modifier,
	viewModel: SettingsViewModel
) {
	Column(
		modifier = modifier
			.statusBarsPadding()
			.padding(16.dp)
			.verticalScroll(rememberScrollState())
	) {
		SwitchSetting(viewModel)
	}
}

@Composable
fun SwitchSetting(
	viewModel: SettingsViewModel
) {
	var isDarkMode by remember { mutableStateOf(viewModel.isNightMode()) }

	Surface(
		elevation = 8.dp,
		color = MaterialTheme.colors.onBackground,
		shape = RoundedCornerShape(8.dp)
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.height(50.dp)
				.padding(16.dp)
		) {
			Text(
				modifier = Modifier
					.fillMaxWidth()
					.weight(1f)
					.align(Alignment.CenterVertically),
				text = viewModel.darkModeTitle,
				style = MaterialTheme.typography.body1,
				fontWeight = FontWeight.Normal
			)

			Switch(
				checked = isDarkMode,
				onCheckedChange = {
					isDarkMode = !isDarkMode
					viewModel.saveThemeMode(isDarkMode)
				}
			)
		}
	}
}