package com.example.newhorizon.Network

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.NetworkImageView
import com.android.volley.toolbox.Volley
import com.example.newhorizon.Apply.DroidApp

object ImageRequester {
    private val _requestQueue: RequestQueue
    private val _imageLoader: ImageLoader
    private val _maxByteSize: Int

    init {
        val context = DroidApp.instance
        this._requestQueue = Volley.newRequestQueue(context)
        this._requestQueue.start()
        this._maxByteSize = calculateMaxByteSize(context)
        this._imageLoader = ImageLoader(
            _requestQueue,
            object : ImageLoader.ImageCache {
                private val lruCache = object : LruCache<String, Bitmap>(_maxByteSize) {
                    override fun sizeOf(url: String, bitmap: Bitmap): Int {
                        return bitmap.byteCount
                    }
                }
                @Synchronized
                override fun getBitmap(url: String): Bitmap? {
                    return lruCache.get(url)
                }
                @Synchronized
                override fun putBitmap(url: String, bitmap: Bitmap) {
                    lruCache.put(url, bitmap)
                }
            })
    }

    fun setImageFromUrl(networkImageView: NetworkImageView, url: String) {
        networkImageView.setImageUrl(url, _imageLoader)
    }

    private fun calculateMaxByteSize(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        val screenBytes = displayMetrics.widthPixels * displayMetrics.heightPixels * 4
        return screenBytes * 3
    }
}