package com.orost.covid19.utils

import android.annotation.SuppressLint
import android.content.Context
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.chart_marker_view.view.*

@SuppressLint("ViewConstructor")
class ChartMarkerView(context: Context?, layoutResource: Int) :
    MarkerView(context, layoutResource) {

    override fun refreshContent(e: Entry, highlight: Highlight) {
        if (e is CandleEntry) {
            content_text.text = e.high.toInt().toString()
        } else {
            content_text.text = e.y.toInt().toString()
        }
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
    }

}