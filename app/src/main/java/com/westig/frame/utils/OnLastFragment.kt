package com.westig.frame.utils

import android.os.Bundle
import com.westig.frame.base.BaseFragment

interface OnLastFragment {
    fun lastFragment(fragment: BaseFragment<*, *>, bundle: Bundle?)

    fun lastFragment(fragment: BaseFragment<*, *>, lastFragment: BaseFragment<*, *>)

}