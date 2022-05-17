package com.ys.clone.disneycompose.di

import android.app.Application
import androidx.room.Room
import com.ys.clone.disneycompose.R
import com.ys.clone.disneycompose.persistence.AppDatabase
import com.ys.clone.disneycompose.persistence.PosterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

	/**
	 * fallbackToDestructiveMigration()
	 *  - 장치의 데이터베이스 버전이 최신 스키마 버전과 일치하지 않는 경우 Room은 데이터베이스에서 필요한 마이그레이션을 실행합니다.
	 *  - 데이터베이스를 현재 버전으로 가져올 마이그레이션 집합을 찾을 수 없으면 IllegalStateException이 발생합니다.
	 *  - 이 메서드를 호출하여 충돌하는 대신 데이터베이스를 다시 생성하도록 이 동작을 변경할 수 있습니다.
	 */
	@Provides
	@Singleton
	fun provideAppDatabase(application: Application) : AppDatabase {
		return Room
			.databaseBuilder(
				application,
				AppDatabase::class.java,
				application.getString(R.string.database)
			)
			.fallbackToDestructiveMigration()
			.build()
	}

	@Provides
	@Singleton
	fun providePosterDao(appDatabase: AppDatabase): PosterDao {
		return appDatabase.posterDao()
	}
}