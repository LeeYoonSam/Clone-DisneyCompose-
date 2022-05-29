package com.ys.clone.disneycompose.ui.settings

import androidx.lifecycle.ViewModel
import com.ys.clone.disneycompose.R
import com.ys.clone.disneycompose.provider.ResourceProvider
import com.ys.clone.disneycompose.provider.ThemeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
	private val themeProvider: ThemeProvider,
	private val resourceProvider: ResourceProvider
) : ViewModel() {

	val darkModeTitle = resourceProvider.getString(R.string.use_dark_theme)

	fun isNightMode() = themeProvider.isNightMode()

	fun saveThemeMode(isChecked: Boolean) {
		themeProvider.theme = if (isChecked) {
			ThemeProvider.Theme.DARK
		} else {
			ThemeProvider.Theme.LIGHT
		}
	}
}