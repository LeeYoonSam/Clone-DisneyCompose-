package com.ys.clone.disneycompose.di

import android.content.Context
import com.ys.clone.disneycompose.provider.ThemeProvider
import com.ys.clone.disneycompose.provider.ThemeProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProviderModule {

	@Provides
	@Singleton
	fun provideThemeProvider(
		@ApplicationContext context: Context
	): ThemeProvider {
		return ThemeProviderImpl(context)
	}
}