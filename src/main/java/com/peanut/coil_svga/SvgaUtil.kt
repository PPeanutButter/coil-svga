package com.peanut.coil_svga

import android.util.Log
import coil.request.Tags
import com.opensource.svgaplayer.SVGACache
import com.opensource.svgaplayer.SVGADrawable
import com.opensource.svgaplayer.SVGAParser
import com.opensource.svgaplayer.SVGAVideoEntity
import kotlinx.coroutines.suspendCancellableCoroutine
import okio.BufferedSource
import okio.ByteString
import java.io.InputStream
import kotlin.coroutines.resume

object SvgaUtil {
    const val TAG = "SvgaUtil"
    private val SVGA_HEADER_ZIP = ByteString.of(120, -100)
    private val SVGA_HEADER = ByteString.of(80, 75, 3, 4)

    fun isSvga(source: BufferedSource): Boolean {
        return source.rangeEquals(0, SVGA_HEADER_ZIP) || source.rangeEquals(0, SVGA_HEADER)
    }

    suspend fun SVGAParser.loadSVGAFromInputStream(inputStream: InputStream, name: String) = suspendCancellableCoroutine { c ->
        this.decodeFromInputStream(
            inputStream,
            SVGACache.buildCacheKey(name),
            object : SVGAParser.ParseCompletion {
                override fun onComplete(videoItem: SVGAVideoEntity) {
                    Log.d(TAG, "onComplete")
                    c.resume(SVGADrawable(videoItem))
                }

                override fun onError() {
                    Log.d(TAG, "onError")
                }
            },
            false,
            null,
            alias = name
        )
    }
}