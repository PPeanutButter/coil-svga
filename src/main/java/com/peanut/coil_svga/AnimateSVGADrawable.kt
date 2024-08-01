package com.peanut.coil_svga

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.opensource.svgaplayer.SVGADrawable
import com.peanut.coil_svga.SvgaUtil.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AnimateSVGADrawable(private val svgaDrawable: SVGADrawable): Animatable2Compat, Drawable() {
    //TODO check min delay > frame rate
    private val delay = 1000L / svgaDrawable.videoItem.FPS
    private val frameCount = svgaDrawable.videoItem.frames
    private var job: Job? = null

    companion object {
        private val clearedField = SVGADrawable::class.java.getDeclaredField("cleared").apply { isAccessible = true }
        private val currentFrameField = SVGADrawable::class.java.getDeclaredField("currentFrame").apply { isAccessible = true }
    }

    override fun start() {
        if (isRunning) return
        clearedField.set(svgaDrawable, false)
        job = CoroutineScope(Dispatchers.Main).launch {
            var frameIdx = 0
            while (true) {
                Log.d(TAG, "render frame:$frameIdx")
                currentFrameField.set(svgaDrawable, frameIdx)
                frameIdx = (frameIdx + 1) % frameCount
                invalidateSelf()
                delay(delay)
            }
        }
    }

    override fun stop() {
        job?.cancel()
    }

    override fun isRunning(): Boolean {
        return !(clearedField.get(svgaDrawable) as Boolean)
    }

    override fun registerAnimationCallback(callback: Animatable2Compat.AnimationCallback) {
        //TODO("Not yet implemented")
    }

    override fun unregisterAnimationCallback(callback: Animatable2Compat.AnimationCallback): Boolean {
        //TODO("Not yet implemented")
        return true
    }

    override fun clearAnimationCallbacks() {
        //TODO("Not yet implemented")
    }

    override fun draw(canvas: Canvas) {
        svgaDrawable.draw(canvas)
    }

    override fun setAlpha(alpha: Int) {
        svgaDrawable.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        svgaDrawable.colorFilter = colorFilter
    }

    @Deprecated("Deprecated in Java")
    override fun getOpacity(): Int {
        return svgaDrawable.alpha
    }
}