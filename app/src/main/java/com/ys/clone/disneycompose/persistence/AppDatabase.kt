package com.ys.clone.disneycompose.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ys.clone.disneycompose.model.Poster

/**
 * entities
 *  - 데이터베이스에 포함된 엔터티 목록입니다.
 *  - 각 엔터티는 데이터베이스의 테이블로 바뀝니다.
 *
 * exportSchema
 *  - room.schemaLocation이 설정되면 Room은 이 변수를 확인하고 true로 설정하면 데이터베이스 스키마를 지정된 폴더로 내보냅니다.
 *  - exportSchema는 기본적으로 true이지만 버전 기록을 유지하지 않으려는 경우(예: 인메모리 전용 데이터베이스) 데이터베이스에 대해 비활성화할 수 있습니다
 */
@Database(entities = [Poster::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

	abstract fun posterDao(): PosterDao
}