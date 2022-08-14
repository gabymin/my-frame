package com.westig.frame.ui.splash

import android.os.Bundle
import com.orhanobut.logger.Logger
import com.westig.frame.R
import com.westig.frame.base.BaseFragment
import com.westig.frame.databinding.SplashFragmentBinding

class SplashFragment : BaseFragment<SplashViewModel, SplashFragmentBinding>() {
    companion object {
        fun newInstance() = SplashFragment()
    }

    override fun layoutResource() = R.layout.splash_fragment

    override fun viewModelClass()=SplashViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.isLogined.postValue(true)
        viewModel.loginLive.postValue("我试试而已")
    }
}