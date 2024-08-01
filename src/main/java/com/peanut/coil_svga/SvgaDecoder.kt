package com.peanut.coil_svga

import android.content.Context
import android.graphics.Bitmap
import coil.ImageLoader
import coil.decode.DecodeResult
import coil.decode.Decoder
import coil.decode.ImageSource
import coil.fetch.SourceResult
import coil.request.Options
import com.opensource.svgaplayer.SVGADrawable
import com.opensource.svgaplayer.SVGADynamicEntity
import com.opensource.svgaplayer.SVGAParser
import com.peanut.coil_svga.SvgaUtil.loadSVGAFromInputStream


class SvgaDecoder(
    private val source: ImageSource,
    private val context: Context,
    private val tag: Map<String, Any>?
) : Decoder {

    override suspend fun decode(): DecodeResult {
        //Init SVGAParser here, safe to call multi times
        val svgaParser = SVGAParser.shareParser()
        svgaParser.init(context)
        //Load data
        val drawable: SVGADrawable = svgaParser.loadSVGAFromInputStream(source.source().inputStream(), source.file().name)
        if (tag?.isNotEmpty() == true){
            for ((k, image) in tag) {
                drawable.dynamicItem.setDynamicImage(image as Bitmap, k)
            }
        }
        return DecodeResult(
            drawable = AnimateSVGADrawable(drawable),
            isSampled = true,
        )
    }

    class Factory : Decoder.Factory {
        override fun create(
            result: SourceResult, options: Options, imageLoader: ImageLoader
        ): Decoder? {
            return if (!SvgaUtil.isSvga(result.source.source())) null
            else SvgaDecoder(result.source, options.context, options.tags.tag<Map<String, Any>>())
        }
    }
}