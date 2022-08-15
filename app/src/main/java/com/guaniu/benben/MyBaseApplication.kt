package com.guaniu.benben

import android.os.Environment
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import com.guaniu.benben.base.BaseApplicationComponent
import com.guaniu.benben.base.DaggerBaseApplicationComponent
import com.orhanobut.logger.*
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import java.io.File
import java.lang.Exception
import java.lang.RuntimeException

class MyBaseApplication : DaggerApplication() {
    private lateinit var instance: MyBaseApplication

    fun getInstance(): MyBaseApplication {
        return instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initThirdpartyFramework()

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        val cmp: BaseApplicationComponent =
            DaggerBaseApplicationComponent.builder().application(this).build()
        return cmp
    }

    @Throws(RuntimeException::class)
    private fun initThirdpartyFramework() {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false) // (Optional) Whether to show thread info or not. Default true
            .methodCount(0) // (Optional) How many method line to show. Default 2
            .methodOffset(7) // (Optional) Hides internal method calls up to offset. Default 5
            .logStrategy(LogcatLogStrategy()) // (Optional) Changes the log strategy to print out. Default LogCat
            .tag("magelog") // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()


//        if (BuildConfig.SAVE_LOG) { //将log保存到文件，logger/目录下
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
        val diskFormatStrategy: FormatStrategy = CsvFormatStrategy.newBuilder()
            .tag("magelog")
            .build()
        Logger.addLogAdapter(object : DiskLogAdapter(diskFormatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return true
            }
        })
        //        }

//        if (BuildConfig.SAVE_LOG) { //将log保存到文件，logger/目录下
        initLogger()
        //        }
        RxJavaPlugins.setErrorHandler { throwable -> Logger.e(throwable, "发生错误") }
    }

    private fun initLogger() {
        //无法直接引用SystemConfig.MAGE_LOG_PATH，只好两边各写了一份。要注意保持一致。
        val folder: String =getInstance().getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS
            )?.absolutePath + File.separator + "logger"
        val ht = HandlerThread("AndroidFileLogger.$folder")
        ht.start()
        try {
            //通过反射实例化DiskLogStrategy中的内部类WriteHandler
            val clazz = Class.forName("com.orhanobut.logger.DiskLogStrategy\$WriteHandler")
            val constructor = clazz.getDeclaredConstructor(
                Looper::class.java,
                String::class.java,
                Int::class.javaPrimitiveType
            )
            //开启强制访问
            constructor.isAccessible = true
            //核心：通过构造函数，传入相关属性，得到WriteHandler实例
            val handler = constructor.newInstance(ht.looper, folder, 512000 * 1024) as Handler
            //创建缓存策略
            val strategy: FormatStrategy =
                CsvFormatStrategy.newBuilder().logStrategy(DiskLogStrategy(handler)).build()
            val adapter = DiskLogAdapter(strategy)
            Logger.addLogAdapter(adapter)
        } catch (e: Exception) {
            e.printStackTrace()
            ht.quit()
        }
    }
}