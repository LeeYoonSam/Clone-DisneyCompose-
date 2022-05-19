package com.ys.clone.disneycompose.ui.main

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import com.ys.clone.disneycompose.model.Poster
import com.ys.clone.disneycompose.network.DisneyService
import com.ys.clone.disneycompose.persistence.PosterDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
	private val disneyService: DisneyService,
	private val posterDao: PosterDao
) {

	init {
		Timber.d("Injection MainRepository")
	}

	@WorkerThread
	fun loadDisneyPosters(
		onStart: () -> Unit,
		onCompletion: () -> Unit,
		onError: (String) -> Unit
	) = flow {
		val posters: List<Poster> = posterDao.getPosterList()
		if (posters.isEmpty()) {
			// API 네트워크 호출을 비동기적으로 요청합니다.
			disneyService.fetchDisneyPosterList()
				// API 요청이 성공 응답을 받는 경우를 처리합니다.
				.suspendOnSuccess {
					posterDao.insertPosterList(data)
					emit(data)
				}
				// API 요청이 실패한 경우를 처리합니다. 예를 들어 인터넷 서버 오류.
				.onFailure { onError(this) }
		} else {
			emit(posters)
		}
	}.onStart { onStart() }.onCompletion { onCompletion }.flowOn(Dispatchers.IO)


}