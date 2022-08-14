package com.westig.frame.repository.user.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.westig.frame.repository.user.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
}