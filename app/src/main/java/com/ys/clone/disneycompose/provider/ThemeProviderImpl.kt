package com.ys.clone.disneycompose.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.ys.clone.disneycompose.R
import com.ys.clone.disneycompose.extensions.getPrefs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ThemeProviderImpl @Inject constructor(
	private val context: Context
) : ThemeProvider {

	private val sharedPreferences = context.getPrefs()

	private val defaultThemeValue = context.getString(R.string.pref_theme_default_value)

	private val preferenceKeyChangedFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)

	private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
		preferenceKeyChangedFlow.tryEmit(key)
	}

	companion object {
		const val KEY_THEME = "pref_theme"
	}

	init {
		sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
	}

	override var theme: ThemeProvider.Theme
		get() = getThemeForStorageValue(sharedPreferences.getString(KEY_THEME, defaultThemeValue) ?: defaultThemeValue)
		set(value) = sharedPreferences.edit {
			putString(KEY_THEME, value.storageKey)
		}

	private val ThemeProvider.Theme.storageKey: String
		get() = when (this) {
			ThemeProvider.Theme.LIGHT -> context.getString(R.string.pref_theme_light_value)
			ThemeProvider.Theme.DARK -> context.getString(R.string.pref_theme_dark_value)
			ThemeProvider.Theme.SYSTEM -> context.getString(R.string.pref_theme_system_value)
		}

	private fun getThemeForStorageValue(value: String) = when (value) {
		context.getString(R.string.pref_theme_light_value) -> ThemeProvider.Theme.LIGHT
		context.getString(R.string.pref_theme_dark_value) -> ThemeProvider.Theme.DARK
		else -> ThemeProvider.Theme.SYSTEM
	}

	override fun observeTheme(): Flow<ThemeProvider.Theme> {
		return preferenceKeyChangedFlow
			// 항상 초기 값을 보내도록 시작 시 방출
			.onStart { emit(KEY_THEME) }
			.filter { it == KEY_THEME }
			.map { theme }
			.distinctUntilChanged()
	}

	override fun isNightMode(): Boolean {
		return theme == ThemeProvider.Theme.DARK
	}

	override fun setNightMode(forceNight: Boolean) {
		theme = if (forceNight) {
			ThemeProvider.Theme.DARK
		} else {
			ThemeProvider.Theme.LIGHT
		}
	}
}