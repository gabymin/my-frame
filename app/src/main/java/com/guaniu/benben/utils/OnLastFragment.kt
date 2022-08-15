package com.guaniu.benben.utils

import android.os.Bundle
import com.guaniu.benben.base.BaseFragment

interface OnLastFragment {
    fun lastFragment(fragment: BaseFragment<*, *>, bundle: Bundle?)

    fun lastFragment(fragment: BaseFragment<*, *>, lastFragment: BaseFragment<*, *>)

}