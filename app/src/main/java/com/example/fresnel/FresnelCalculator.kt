package com.example.fresnel

import kotlin.math.sqrt

object FresnelCalculator {
    fun firstZone(d1: Double, d2: Double, freqHz: Double): Double {
        val c = 3e8
        val lambda = c / freqHz
        return sqrt(lambda * d1 * d2 / (d1 + d2))
    }

    fun overlapPercent(height: Double, losHeight: Double, fresnelRadius: Double): Double {
        return ((height - losHeight) / fresnelRadius) * 100
    }
}
