package com.guaniu.benben.repository.user

import com.guaniu.benben.utils.Resource
import com.guaniu.benben.repository.user.entity.User
import com.guaniu.benben.repository.user.local.UserDao
import com.guaniu.benben.repository.user.local.entity.UserEntity
import com.guaniu.benben.repository.user.remote.UserApi
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(val userApi: UserApi,
                                         val userDao: UserDao
) {

    fun getLoginUser(): Single<User> {
        return userDao.getLoginUser().map {
            User.fromUserEntity(it)
        }
    }

    fun logout(): Single<Boolean> {
        //退出登录，直接清除本地数据
        return Single.fromCallable {
            userDao.deleteAllUser()
            true
        }
    }

    fun updateUserData(user: UserEntity?):Single<Int>{
        return Single
            .fromCallable {
                user?.let {
                    userDao.updateUser(user)
                }
            }
    }

    /**
     * gaid上传到server端
     */
    fun gaidUpload(gaid: String): Single<Resource<Any>> {
        return userApi.gaidUpload(gaid)
    }
}