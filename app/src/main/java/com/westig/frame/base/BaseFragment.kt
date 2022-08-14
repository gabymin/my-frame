package com.westig.frame.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.orhanobut.logger.Logger
import com.westig.frame.BR
import com.westig.frame.utils.OnLastFragment
import com.westig.frame.utils.OnNextFragment
import com.westig.frame.utils.ValueAnimatorUtil
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseFragment <ViewModelT : BaseViewModel, DataBindingT : ViewDataBinding> : DaggerFragment() {
    @LayoutRes
    protected abstract fun layoutResource(): Int

    protected abstract fun viewModelClass(): Class<out BaseViewModel>

    @JvmField
    @Inject
    var viewModelFactory: ViewModelProvider.Factory? = null
    lateinit var viewModel: ViewModelT
    lateinit var dataBinding: DataBindingT

    protected val compositeDisposable = CompositeDisposable()

    fun disposeAfterDestroy(block: () -> Disposable) {
        compositeDisposable.add(block())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        ValueAnimatorUtil.resetDurationScale()
        dataBinding = DataBindingUtil.inflate(
            inflater, layoutResource(), container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        return dataBinding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(viewModelClass()) as ViewModelT
        dataBinding.setVariable(BR.viewModel, viewModel)
    }


    override fun onResume() {
        super.onResume()
        Logger.i("在哪里      "+javaClass.simpleName)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    /**
     * 跳转activity,不带参数
     *
     * @param clz 跳转的activity
     */
    fun startActivity(clz: Class<*>) {
        startActivity(Intent(activity, clz))
    }

    /**
     * 跳转activity，带参数
     *
     * @param clz    跳转的activity
     * @param bundle 传递的参数
     */
    fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent(activity, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     * 跳往下一个fragment
     */
    fun nextFragment(bundle: Bundle?) {
        if (activity is OnNextFragment) {
            (requireActivity() as OnNextFragment).nextFragment(this, bundle)
        }
    }

    /**
     * 跳往下一个fragment
     */
    fun nextFragment(nextFragment: BaseFragment<*, *>) {
        if (activity is OnNextFragment) {
            (requireActivity() as OnNextFragment).nextFragment(this, nextFragment)
        }
    }

    /**
     * 返回上一个fragment
     */
    fun lastFragment(bundle: Bundle?) {
        if (activity is OnLastFragment) {
            (requireActivity() as OnLastFragment).lastFragment(this, bundle)
        }
    }
}