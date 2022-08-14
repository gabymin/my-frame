package com.westig.frame.utils

import android.os.Bundle
import com.westig.frame.base.BaseFragment

interface OnNextFragment {
    fun nextFragment(fragment: BaseFragment<*, *>, bundle: Bundle?)

    fun nextFragment(fragment: BaseFragment<*, *>, nextFragment: BaseFragment<*, *>)
}