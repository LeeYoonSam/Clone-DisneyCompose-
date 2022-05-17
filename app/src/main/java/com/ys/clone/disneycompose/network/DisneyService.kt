package com.ys.clone.disneycompose.network

import com.skydoves.sandwich.ApiResponse
import com.ys.clone.disneycompose.model.Poster
import retrofit2.http.GET

interface DisneyService {

	@GET("DisneyPosters2.json")
	suspend fun fetchDisneyPosterList(): ApiResponse<List<Poster>>
}