package com.guaniu.benben.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.guaniu.benben.base.BaseActivity

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