package com.example.imageviewtouchlistner

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlinx.coroutines.*

class CircleOverlayView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private data class Circle(val x: Float, val y: Float)

    private val circles = mutableListOf<Circle>()

    private val circlePaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 10f
        isAntiAlias = true
    }

    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 50f
        typeface = Typeface.DEFAULT_BOLD
        isAntiAlias = true
    }

    private val textPadding = 20f

    fun showCircle(x: Float, y: Float) {
        val circle = Circle(x, y)
        circles.add(circle)
        invalidate()

        // Remove the circle after 2 seconds
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            circles.remove(circle)
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for ((x, y) in circles) {
            // Draw circle
            canvas.drawCircle(x, y, 50f, circlePaint)

            // Prepare coordinate text
            val coordsText = "(${x.toInt()}, ${y.toInt()})"
            val textWidth = textPaint.measureText(coordsText)
            val textHeight = textPaint.fontMetrics.bottom - textPaint.fontMetrics.top

            var textX = x - textWidth / 2
            var textY = y - 60f  // Default: above the circle

            // Ensure text stays within horizontal bounds
            if (textX < textPadding) textX = textPadding
            if (textX + textWidth > width - textPadding) textX = width - textWidth - textPadding

            // Ensure text stays within vertical bounds
            if (textY - textHeight < textPadding) {
                textY = y + 80f  // If too close to top, draw below the circle
            }

            // Draw text
            canvas.drawText(coordsText, textX, textY, textPaint)
        }
    }
}
