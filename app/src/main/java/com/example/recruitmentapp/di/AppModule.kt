package com.example.recruitmentapp.di

import android.content.Context
import androidx.room.Room
import com.example.recruitmentapp.source.local.TasksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): TasksDatabase {
        return Room.databaseBuilder(
            context,
            TasksDatabase::class.java,
            TasksDatabase.DB_NAME
        ).build()
    }
}

//@Module
//@InstallIn(SingletonComponent::class)
//class ProductionModule {
//
//    @Singleton
//    @Provides
//    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
//        return Room
//            .databaseBuilder(appContext, AppDatabase::class.java, AppDatabase.DB_NAME)
//            .build()
//    }
//}