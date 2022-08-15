package com.guaniu.benben.repository.user.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserEntity (
    //用户id
    @PrimaryKey
    var user_id: Int,
    //用户手机
    @ColumnInfo
    var phone:String,
    //用户账号（手机号）
    @ColumnInfo
    var username: String,
    //姓名
    @ColumnInfo
    var nickname: String,
    //性别
    @ColumnInfo
    var sex: Int,
    //邮箱
    @ColumnInfo
    var email: String,
    //创建时间
    @ColumnInfo
    var created_time: String,
    //头像
    @ColumnInfo
    var avatar: String,
    //身高
    @ColumnInfo
    var height: Double,
    //体重
    @ColumnInfo
    var weight: Double,
    //生日
    @ColumnInfo
    var birthday: String,
)