package com.guaniu.benben.base

import android.content.Context
import androidx.room.Room
import com.guaniu.benben.repository.user.local.UserDao
import com.guaniu.benben.repository.user.local.UserDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryLocalModule {
    @Module
    companion object {
        @JvmStatic
        @Singleton
        @Provides
        fun provideUserDatabase(context: Context): UserDatabase {
            return Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "user.db")
                .fallbackToDestructiveMigration()
                .build()
        }


        @JvmStatic
        @Singleton
        @Provides
        fun provideUserDao(userDatabase: UserDatabase): UserDao {
            return userDatabase.userDao()
        }
    }
}