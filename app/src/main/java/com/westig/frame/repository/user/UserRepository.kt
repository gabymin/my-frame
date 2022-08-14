package com.westig.frame.repository.user

import com.westig.frame.repository.user.entity.User
import com.westig.frame.repository.user.local.UserDao
import com.westig.frame.repository.user.local.entity.UserEntity
import com.westig.frame.repository.user.remote.UserApi
import com.westig.frame.utils.Resource
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