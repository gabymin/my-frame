package com.guaniu.benben.utils

import android.os.Bundle
import com.guaniu.benben.base.BaseFragment

interface OnNextFragment {
    fun nextFragment(fragment: BaseFragment<*, *>, bundle: Bundle?)

    fun nextFragment(fragment: BaseFragment<*, *>, nextFragment: BaseFragment<*, *>)
}