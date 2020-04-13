package com.orost.covid19.ui

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.orost.covid19.R
import com.orost.covid19.model.GlobalByDate
import com.orost.covid19.utils.ChartMarkerView
import com.orost.covid19.utils.DayAxisValueFormatter
import com.orost.covid19.viewmodel.ChartViewModel
import kotlinx.android.synthetic.main.fragment_chart.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class ChartFragment : BaseFragment() {

    private val viewModel: ChartViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun initUI(savedInstanceState: Bundle?) {
        initLineChart()
        initBarChart()
    }

    override fun subscribeToLiveData() {
        viewModel.statisticByDate.observe(this, Observer { statisticsByDate ->
            buildBarChart(statisticsByDate)
            buildLineChart(statisticsByDate)
        })
    }

    private fun initBarChart() {
        val xAxis: XAxis = bar_chart.xAxis
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.textColor = Color.WHITE
        xAxis.axisLineColor = Color.WHITE
        xAxis.gridColor = Color.WHITE
        xAxis.textColor = Color.WHITE
        xAxis.granularity = 1f
        xAxis.labelCount = 5
        xAxis.valueFormatter = DayAxisValueFormatter(bar_chart)

        val leftAxis: YAxis = bar_chart.axisLeft
        leftAxis.setLabelCount(8, false)
        leftAxis.textColor = Color.WHITE
        leftAxis.axisLineColor = Color.WHITE
        leftAxis.gridColor = Color.WHITE
        leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART)
        leftAxis.spaceTop = 15f
        leftAxis.axisMinimum = 0f

        val rightAxis: YAxis = bar_chart.axisRight
        rightAxis.setDrawGridLines(false)
        rightAxis.textColor = Color.WHITE
        rightAxis.axisLineColor = Color.WHITE
        rightAxis.gridColor = Color.WHITE
        rightAxis.setLabelCount(8, false)
        rightAxis.spaceTop = 15f
        rightAxis.axisMinimum = 0f

        val l: Legend = bar_chart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.textColor = Color.WHITE
        l.form = LegendForm.SQUARE
        l.formSize = 9f
        l.textSize = 11f
        l.xEntrySpace = 4f

        val mv = ChartMarkerView(context, R.layout.chart_marker_view)
        mv.chartView = bar_chart
        bar_chart.marker = mv
    }

    private fun initLineChart() {

        line_chart.description.isEnabled = false
        line_chart.setTouchEnabled(true)

        line_chart.setDrawGridBackground(false)

        line_chart.isDragEnabled = true
        line_chart.setScaleEnabled(true)

        line_chart.setPinchZoom(true)

        val xAxis = line_chart.xAxis
        xAxis.enableGridDashedLine(10f, 10f, 0f)
        xAxis.textColor = Color.WHITE
        xAxis.axisLineColor = Color.WHITE
        xAxis.gridColor = Color.WHITE
        xAxis.valueFormatter = DayAxisValueFormatter(bar_chart)

        val yAxis = line_chart.axisLeft
        line_chart.axisRight.isEnabled = false
        yAxis.enableGridDashedLine(10f, 10f, 0f)
        yAxis.axisMinimum = 0f
        yAxis.textColor = Color.WHITE
        yAxis.axisLineColor = Color.WHITE
        yAxis.gridColor = Color.WHITE

        yAxis.setDrawLimitLinesBehindData(true)
        xAxis.setDrawLimitLinesBehindData(true)

        line_chart.legend.textColor = Color.WHITE

        line_chart.legend.form = LegendForm.LINE

        val mv = ChartMarkerView(context, R.layout.chart_marker_view)
        mv.chartView = line_chart
        line_chart.marker = mv
    }

    private fun buildBarChart(statisticByDate: GlobalByDate) {
        val values = mutableListOf<BarEntry>()

        var prevValue = 0f

        statisticByDate.result.forEach { entry ->
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = formatter.parse(entry.key)
            val calendar = Calendar.getInstance()
            calendar.time = date
            val confirmedCases = entry.value.confirmed.toFloat() - prevValue
            values.add(
                BarEntry(
                    calendar.get(Calendar.DAY_OF_YEAR).toFloat(),
                    confirmedCases
                )
            )
            prevValue = confirmedCases
        }

        val set1 = BarDataSet(values, getString(R.string.bar_chart_legend))
        set1.colors = listOf(
            ContextCompat.getColor(context!!, R.color.yellow1),
            ContextCompat.getColor(context!!, R.color.yellow2),
            ContextCompat.getColor(context!!, R.color.yellow3),
            ContextCompat.getColor(context!!, R.color.yellow4)
        )
        set1.setDrawValues(false)

        val dataSets = mutableListOf<IBarDataSet>()
        dataSets.add(set1)

        val data = BarData(dataSets)
        bar_chart.data = data
        bar_chart.setFitBars(true)
        bar_chart.description.isEnabled = false
        bar_chart.animateX(1500)
        bar_chart.invalidate()
    }

    private fun buildLineChart(statisticByDate: GlobalByDate) {
        val values = mutableListOf<Entry>()

        statisticByDate.result.forEach { entry ->
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = formatter.parse(entry.key)
            val calendar = Calendar.getInstance()
            calendar.time = date
            values.add(
                Entry(
                    calendar.get(Calendar.DAY_OF_YEAR).toFloat(),
                    entry.value.confirmed.toFloat()
                )
            )
        }
        val set1 = LineDataSet(values, getString(R.string.line_chart_legend))
        set1.setDrawIcons(false)
        set1.enableDashedLine(10f, 5f, 0f)
        set1.color = Color.YELLOW
        set1.setCircleColor(Color.YELLOW)
        set1.lineWidth = 1f
        set1.circleRadius = 3f
        set1.setDrawCircleHole(false)
        set1.formLineWidth = 1f
        set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
        set1.formSize = 15f
        set1.valueTextSize = 9f
        set1.enableDashedHighlightLine(10f, 5f, 0f)
        set1.setDrawFilled(true)
        set1.setDrawValues(false)
        set1.setFillFormatter { _, _ ->
            line_chart.axisLeft.axisMinimum
        }
        val drawable = ContextCompat.getDrawable(context!!, R.drawable.fade_yellow)
        set1.fillDrawable = drawable

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1)
        val data = LineData(dataSets)
        line_chart.data = data
        line_chart.animateX(1500)
        line_chart.invalidate()
    }


}
