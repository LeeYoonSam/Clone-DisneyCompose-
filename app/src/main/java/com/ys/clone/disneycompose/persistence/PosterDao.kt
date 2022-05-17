package com.ys.clone.disneycompose.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ys.clone.disneycompose.model.Poster

/**
 * @Dao
 * - 클래스를 데이터 액세스 개체로 표시합니다.
 * - 데이터 액세스 개체는 데이터베이스 상호 작용을 정의하는 기본 클래스입니다. 여기에는 다양한 쿼리 방법이 포함될 수 있습니다.
 */
@Dao
interface PosterDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertPosterList(posterList: List<Poster>)

	@Query("SELECT * FROM Poster WHERE id = :id_")
	suspend fun getPoster(id_: Long): Poster

	@Query("SELECT * FROM Poster")
	suspend fun getPosterList(): List<Poster>
}