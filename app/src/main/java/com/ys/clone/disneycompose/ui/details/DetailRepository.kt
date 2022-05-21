package com.ys.clone.disneycompose.ui.details

import androidx.annotation.WorkerThread
import com.ys.clone.disneycompose.persistence.PosterDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepository @Inject constructor(
	private val posterDao: PosterDao
) {

	@WorkerThread
	fun getPosterById(posterId: Long) = flow {
		val poster = posterDao.getPoster(posterId)
		emit(poster)
	}.flowOn(Dispatchers.IO)
}