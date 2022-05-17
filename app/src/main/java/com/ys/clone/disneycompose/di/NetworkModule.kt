package com.ys.clone.disneycompose.di

import android.content.Context
import android.os.Build
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.util.CoilUtils
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import com.ys.clone.disneycompose.network.DisneyService
import com.ys.clone.disneycompose.network.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	@Singleton
	fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
		return OkHttpClient.Builder()
			.addInterceptor(RequestInterceptor())
			.cache(CoilUtils.createDefaultCache(context))
			.build()
	}

	@Provides
	@Singleton
	fun provideImageLoader(
		@ApplicationContext context: Context,
		okHttpClient: OkHttpClient
	): ImageLoader {
		return ImageLoader.Builder(context)
			.okHttpClient { okHttpClient }
			.componentRegistry {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
					add(ImageDecoderDecoder(context))
				} else {
					add(GifDecoder())
				}
			}.build()
	}

	@Provides
	@Singleton
	fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
		return Retrofit.Builder()
			.client(okHttpClient)
			.baseUrl(
				"https://gist.githubusercontent.com/skydoves/176c209dbce4a53c0ff9589e07255f30/raw/6489d9712702e093c4df71500fb822f0d408ef75/"
			)
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
			.build()
	}

	@Provides
	@Singleton
	fun provideDisneyService(retrofit: Retrofit): DisneyService {
		return retrofit.create(DisneyService::class.java)
	}
}