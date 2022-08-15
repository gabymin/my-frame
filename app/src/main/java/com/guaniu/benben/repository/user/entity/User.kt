package com.guaniu.benben.repository.user.entity

import com.guaniu.benben.repository.user.local.entity.UserEntity

class User {
    lateinit var user_info: UserInfo
    class UserInfo {
        //用户类型
        var user_type: String = ""

        //id
        var user_id: Int = 0

        //用户名
        var user_name: String = ""

        //user_name
        var phone: String = ""

        //创建时间
        var create_time: String = ""

        //昵称
        var nick_name: String = ""

        //生日
        var birthday: String = ""
        //性别
        var sex: Int = 0

        //电子邮箱
        var email: String = ""
        var contact_email : String = ""
        //身高
        var height: Double = 0.0

        //体重
        var weight: Double = 0.0

        //头像
        var avatar: String = ""

        // 是否进行过map 测试过，1表示测试过，0表示没有测试过
        var map_tested: Int = 0

        //cocos的第三方用户标识
        var third_uid: String=""

    }
    companion object {
        fun fromUserEntity(userEntity: UserEntity): User {
            val user = User()
            user.user_info = UserInfo()
            user.user_info.user_id = userEntity.user_id
            user.user_info.phone = userEntity.phone
            user.user_info.user_name = userEntity.username
            user.user_info.nick_name = userEntity.nickname
            user.user_info.sex = userEntity.sex
            user.user_info.email = userEntity.email
            user.user_info.contact_email = userEntity.email
            user.user_info.create_time = userEntity.created_time
            user.user_info.avatar = userEntity.avatar
            user.user_info.height = userEntity.height
            user.user_info.weight = userEntity.weight
            user.user_info.birthday = userEntity.birthday
            return user
        }
    }
}