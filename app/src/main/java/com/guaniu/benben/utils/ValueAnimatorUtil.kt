package com.guaniu.benben.utils

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.view.View
import android.view.animation.Animation
import com.orhanobut.logger.Logger
import java.lang.Exception
import java.lang.reflect.Field

class ValueAnimatorUtil {
    companion object{
        //抖动布局
        fun propertyValuesHolderDown(view: View?) {
            val pvhX = PropertyValuesHolder.ofFloat(
                "alpha",
                1f,
                0.9f,
                0.9f,
                0.91f,
                0.92f,
                0.93f,
                0.94f,
                0.95f,
                0.96f,
                0.97f,
                0.98f,
                0.99f,
                1f
            )
            val pvhY = PropertyValuesHolder.ofFloat(
                "scaleX",
                1f,
                0.9f,
                0.9f,
                0.91f,
                0.92f,
                0.93f,
                0.94f,
                0.95f,
                0.96f,
                0.97f,
                0.98f,
                0.99f,
                1f
            )
            val pvhZ = PropertyValuesHolder.ofFloat(
                "scaleY",
                1f,
                0.9f,
                0.9f,
                0.91f,
                0.92f,
                0.93f,
                0.94f,
                0.95f,
                0.96f,
                0.97f,
                0.98f,
                0.99f,
                1f
            )
            ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(300).start()
        }

        /**
         * 动画抖动效果
         * @param view 抖动控件
         * @param scaleLarge 缩小倍数
         * @param shakeDegrees 放大倍数
         * @param duration 抖动角度
         * @param isInfinite 是否循环抖动
         */
        fun startShakeByPropertyAnim(
            view: View?,
            scaleSmall: Float,
            scaleLarge: Float,
            shakeDegrees: Float,
            duration: Long,
            isInfinite: Boolean
        ) {
            var scaleSmall = scaleSmall
            var scaleLarge = scaleLarge
            var shakeDegrees = shakeDegrees
            var duration = duration
            if (view == null) {
                return
            }
            if (scaleSmall == 0f) scaleSmall = 0.9f
            if (scaleLarge == 0f) scaleLarge = 1f
            if (shakeDegrees == 0f) shakeDegrees = 1f
            if (duration == 0L) duration = 500
            val scaleXValuesHolder = PropertyValuesHolder.ofKeyframe(
                View.SCALE_X,
                Keyframe.ofFloat(0f, 1.0f),
                Keyframe.ofFloat(0.25f, scaleSmall),
                Keyframe.ofFloat(0.5f, scaleLarge),
                Keyframe.ofFloat(0.75f, scaleLarge),
                Keyframe.ofFloat(1.0f, 1.0f)
            )
            val scaleYValuesHolder = PropertyValuesHolder.ofKeyframe(
                View.SCALE_Y,
                Keyframe.ofFloat(0f, 1.0f),
                Keyframe.ofFloat(0.25f, scaleSmall),
                Keyframe.ofFloat(0.5f, scaleLarge),
                Keyframe.ofFloat(0.75f, scaleLarge),
                Keyframe.ofFloat(1.0f, 1.0f)
            )
            val rotateValuesHolder = PropertyValuesHolder.ofKeyframe(
                View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(0.1f, -shakeDegrees),
                Keyframe.ofFloat(0.2f, shakeDegrees),
                Keyframe.ofFloat(0.3f, -shakeDegrees),
                Keyframe.ofFloat(0.4f, shakeDegrees),
                Keyframe.ofFloat(0.5f, -shakeDegrees),
                Keyframe.ofFloat(0.6f, shakeDegrees),
                Keyframe.ofFloat(0.7f, -shakeDegrees),
                Keyframe.ofFloat(0.8f, shakeDegrees),
                Keyframe.ofFloat(0.9f, -shakeDegrees),
                Keyframe.ofFloat(1.0f, 0f)
            )
            val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                view,
                scaleXValuesHolder,
                scaleYValuesHolder,
                rotateValuesHolder
            )
            objectAnimator.duration = duration
            if (isInfinite) {
                objectAnimator.repeatCount = Animation.INFINITE
            }
            objectAnimator.start()
        }

        /**
         * 如果动画被禁用，则重置动画缩放时长
         */
        fun resetDurationScaleIfDisable() {
            val durationScale = getDurationScale()
            Logger.i("ValueAnimatorUtil  durationScale = $durationScale")
            if (durationScale == 0f || durationScale > 1.0) resetDurationScale()
        }

        /**
         * 重置动画缩放时长
         */
        fun resetDurationScale() {
            try {
                getField().setFloat(null, 1f)
            } catch (e: Exception) {
                Logger.i("e = " + e.message)
            }
        }

        private fun getDurationScale(): Float {
            return try {
                getField().getFloat(null)
            } catch (e: Exception) {
                -1.0f
            }
        }

        @Throws(NoSuchFieldException::class)
        private fun getField(): Field {
            @SuppressLint("SoonBlockedPrivateApi") val field =
                ValueAnimator::class.java.getDeclaredField("sDurationScale")
            field.isAccessible = true
            return field
        }
    }

}