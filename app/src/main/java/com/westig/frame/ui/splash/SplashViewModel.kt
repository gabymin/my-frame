package com.westig.frame.ui.splash

import androidx.lifecycle.MutableLiveData
import com.orhanobut.logger.Logger
import com.westig.frame.base.BaseViewModel
import com.westig.frame.repository.user.UserRepository
import com.westig.frame.utils.background
import com.westig.frame.utils.request
import javax.inject.Inject

class SplashViewModel @Inject constructor(val userRepository: UserRepository) : BaseViewModel() {
    val isLogined = MutableLiveData<Boolean>()
    val loginLive = MutableLiveData<String>()

    fun logout() {
        disposeAfterClear {
            userRepository.logout()
                .background()
                .subscribe()
        }
    }
    //gaid上传结果
    val gaidUploadResult = MutableLiveData<Boolean>()
    /**
     * 上传gaid到server端
     */
    fun gaidUpload(gaid: String) {
        disposeAfterClear {
            userRepository.gaidUpload(gaid)
                .background()
                .request(this, {
                    gaidUploadResult.postValue(true)
                }, {
                    Logger.i("zwt message = ${it.message}")
                    gaidUploadResult.postValue(false)
                })
        }
    }
}