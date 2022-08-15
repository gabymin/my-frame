package com.guaniu.benben.ui.splash

import android.os.Bundle
import com.guaniu.benben.R
import com.guaniu.benben.base.BaseFragment
import com.guaniu.benben.databinding.SplashFragmentBinding

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