package com.guaniu.benben.repository.user.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.guaniu.benben.repository.user.local.entity.UserEntity
import io.reactivex.Single

@Dao
interface UserDao {
    @Insert
    fun saveLoginUser(user: UserEntity)

    @Update
    fun updateUser(user: UserEntity):Int

    @Query("select * from userentity")
    fun getLoginUser(): Single<UserEntity>

    @Query("delete from userentity")
    fun deleteAllUser()
}