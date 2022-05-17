package com.ys.clone.disneycompose.network

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import timber.log.Timber

class RequestInterceptor : Interceptor {
	override fun intercept(chain: Chain): Response {
		val originalRequest = chain.request()
		val request = originalRequest.newBuilder().url(originalRequest.url).build()
		Timber.d(request.toString())
		return chain.proceed(request)
	}
}