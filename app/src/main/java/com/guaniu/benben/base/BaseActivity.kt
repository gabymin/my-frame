package com.guaniu.benben.base

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.orhanobut.logger.Logger
import com.guaniu.benben.R
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    /**
     * activity 基本都是空的，主要的初始布局在fragment中
     */
    abstract fun newInstanceFragment(): Fragment?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //默认设置空的activity 并且用 newInstanceFragment 填充,
        onCreateBase(savedInstanceState)
    }
    /**
     * 如果子类布局不一样，即不是默认的空页面，可以重写这里
     */
    protected open fun onCreateBase(savedInstanceState: Bundle?) {
        //默认的activity都是一个空的布局，主要的内容在对应的fragment中
        setContentView(R.layout.empty_activity)
        val fragment = newInstanceFragment()
        if (null == savedInstanceState && fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
    }
    /**
     * 跳转activity,不带参数
     *
     * @param clz 跳转的activity
     */
    fun startActivity(clz: Class<*>) {
        startActivity(Intent(this, clz))
    }

    /**z
     * 跳转activity，带参数
     *
     * @param clz    跳转的 activity
     * @param bundle 传递的参数
     */
    fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        Logger.i("在哪里:      " + javaClass.simpleName)
    }


    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

        Logger.w("onTrimMemory 内存低 level = $level")
    }
}