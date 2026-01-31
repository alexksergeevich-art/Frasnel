package com.example.fresnel

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlin.math.sqrt

class ProfileActivity : AppCompatActivity() {

    private lateinit var chart: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chart = LineChart(this)
        setContentView(chart)

        val lat1 = intent.getDoubleExtra("point1_lat", 0.0)
        val lng1 = intent.getDoubleExtra("point1_lng", 0.0)
        val lat2 = intent.getDoubleExtra("point2_lat", 0.0)
        val lng2 = intent.getDoubleExtra("point2_lng", 0.0)

        // Пример данных (для демо, позже подключим Elevation API)
        val distances = List(50) { it.toDouble() }
        val heights = List(50) { 100 + it * 0.5 } // имитация профиля
        val fresnel = List(50) { sqrt(it * (50-it).toDouble()) * 0.3 }

        val profileEntries = distances.mapIndexed { i, d ->
            Entry(d.toFloat(), heights[i].toFloat())
        }
        val fresnelEntries = distances.mapIndexed { i, d ->
            Entry(d.toFloat(), (heights[i] + fresnel[i]).toFloat())
        }

        val profileSet = LineDataSet(profileEntries, "Рельеф").apply {
            color = Color.BLUE
            setDrawCircles(false)
            lineWidth = 2f
        }

        val fresnelSet = LineDataSet(fresnelEntries, "1-я зона Френнеля").apply {
            color = Color.RED
            setDrawCircles(false)
            lineWidth = 1.5f
            setDrawFilled(true)
            fillColor = Color.RED
            fillAlpha = 50
        }

        chart.data = LineData(profileSet, fresnelSet)
        chart.legend.isEnabled = true
        chart.description.isEnabled = false
        chart.invalidate()
    }
}
