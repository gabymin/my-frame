package com.westig.frame.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.westig.frame.base.BaseActivity

class SplashActivity : BaseActivity(){
    override fun newInstanceFragment(): Fragment {
        return SplashFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!isTaskRoot) {
            finish()
            return
        }
    }
}