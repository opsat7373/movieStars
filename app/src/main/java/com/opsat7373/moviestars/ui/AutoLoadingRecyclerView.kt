package com.opsat7373.moviestars.ui

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.subjects.PublishSubject

class AutoLoadingRecyclerView : RecyclerView {

    private var scrolEndingThreshold = 2

    private val scrollEndingStream : PublishSubject<Int> = PublishSubject.create()

    constructor(context : Context) : super(context){
        init()
    }

    constructor(context : Context, attributeSet : AttributeSet) : super(context, attributeSet){
        init()
    }

    constructor(context : Context, attributeSet : AttributeSet, styleInt : Int) : super(context, attributeSet, styleInt){
        init()
    }

    private fun init() {
        this.addOnScrollListener(scrollListener)
        this.setScrollEndingThreshold()
    }

    fun setScrollEndingThreshold(threshold : Int = 2) {
        scrolEndingThreshold = threshold
    }

    private val scrollListener : OnScrollListener = object : OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val last = layoutManager.findLastVisibleItemPosition()
            val count = adapter!!.itemCount
            if (count - scrolEndingThreshold <= last ) {
                scrollEndingStream.onNext(last)
            }
        }
    }

    fun getListEndingStream() : PublishSubject<Int> {
        return scrollEndingStream
    }
}