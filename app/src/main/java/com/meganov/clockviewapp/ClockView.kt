package com.meganov.clockviewapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import java.util.Calendar
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

/**
 * Clock with optional parameters (color, borderColor, borderWidth)
 */
class ClockView : View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // Use Handler to update the clock every second asynchronously
    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            invalidate()
            handler.postDelayed(this, 1000)
        }
    }

    private var clockColor: Int
    private var borderColor: Int
    private var borderWidth: Float

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.ClockView, 0, 0).apply {
            try {
                clockColor = getColor(R.styleable.ClockView_clockColor, Color.WHITE)
                borderColor = getColor(R.styleable.ClockView_borderColor, Color.BLACK)
                borderWidth = getDimension(R.styleable.ClockView_borderWidth, min(width, height) / 100f)
            } finally {
                recycle()
            }
        }
        paint.style = Paint.Style.STROKE
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        handler.post(runnable)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        handler.removeCallbacks(runnable)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Define the center and the radius
        val centerX = width / 2f
        val centerY = height / 2f
        val radius = min(centerX, centerY) / 1.025f

        // Draw the clock
        paint.color = clockColor
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawCircle(centerX, centerY, radius, paint)

        // Draw the clock border
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth
        canvas.drawCircle(centerX, centerY, radius, paint)

        // Draw the numbers
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.textAlign = Paint.Align.CENTER
        paint.strokeWidth = min(width, height) / 120f
        val metrics = paint.fontMetrics
        paint.textSize = min(width, height) / 10f
        for (i in 1..12) {
            val angle = Math.toRadians((i * 30 - 90).toDouble()).toFloat()
            val numX = centerX + radius * 0.77f * cos(angle)
            val numY = centerY + radius * 0.77f * sin(angle) - (metrics.ascent + metrics.descent) / 2
            canvas.drawText(i.toString(), numX, numY, paint)
        }

        // Draw the minute dots
        val thin = min(width, height) / 250f
        val thick = min(width, height) / 125f
        for (i in 0..59) {
            val angle = Math.toRadians((i * 6 - 90).toDouble()).toFloat()
            val dotX = centerX + radius * 0.95f * cos(angle)
            val dotY = centerY + radius * 0.95f * sin(angle)
            paint.strokeWidth = if (i % 5 == 0) thick else thin
            canvas.drawPoint(dotX, dotY, paint)
        }

        // Get the current time with Calendar
        val now = Calendar.getInstance()
        val seconds = now.get(Calendar.SECOND)
        val minutes = now.get(Calendar.MINUTE)
        val hours = now.get(Calendar.HOUR)

        // Calculate the hands angles
        val secAngle = Math.toRadians((seconds * 6 - 90).toDouble()).toFloat()
        val minAngle = Math.toRadians(((minutes + seconds / 60.0) * 6 - 90)).toFloat()
        val hourAngle = Math.toRadians(((hours + minutes / 60.0) * 30 - 90)).toFloat()

        // Draw the second hand
        paint.color = Color.RED
        paint.strokeWidth = min(width, height) / 300f
        val secX = centerX + radius * 0.8f * cos(secAngle)
        val secY = centerY + radius * 0.9f * sin(secAngle)
        canvas.drawLine(centerX, centerY, secX, secY, paint)

        // Draw the minute hand
        paint.color = Color.BLUE
        paint.strokeWidth = min(width, height) / 200f
        val minX = centerX + radius * 0.6f * cos(minAngle)
        val minY = centerY + radius * 0.6f * sin(minAngle)
        canvas.drawLine(centerX, centerY, minX, minY, paint)

        // Draw the hour hand
        paint.color = Color.GREEN
        paint.strokeWidth = min(width, height) / 100f
        val hourX = centerX + radius * 0.45f * cos(hourAngle)
        val hourY = centerY + radius * 0.45f * sin(hourAngle)
        canvas.drawLine(centerX, centerY, hourX, hourY, paint)

        // Dot on the center (hands mounting)
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawCircle(centerX, centerY, min(centerX, centerY) / 100f, paint)
    }
}
