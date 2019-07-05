package by.itacademy.pvt.dz4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.icu.util.Calendar
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import by.itacademy.pvt.R

class Dz4View : View {
    private val clockPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var clockCenterX = 0f
    private var clockCenterY = 0f
    private var clockRadius = 0f
    private var clockMargin = 0f

    private val lineClockPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var lineClockWidth = 0f
    private var lineClockLength = 0f

    private var centerHourClockPointPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var centerHourClockPointRadius = 0f

    private var centerSecClockPointPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var centerSecClockPointRadius = 0f

    private var centerClockPointPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var centerClockPointRadius = 0f

    private val rect = Rect()
    private val numbersClockPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val numbersClockArray = intArrayOf(3, 6, 9, 12)
    private var numbersClockMargin = 0f

    private val hourHandPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val minHandPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val secHandPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var hourHandLength = 0f
    private var otherHandLength = 0f
    private var handsIndents = 0f
    private var hourHandIndent = 0f

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    init {
        clockPaint.color = ContextCompat.getColor(context, R.color.clock)
        clockPaint.style = Paint.Style.STROKE
        clockPaint.strokeWidth = resources.getDimension(R.dimen.clock_width)
        clockMargin = resources.getDimension(R.dimen.clock_margin)

        lineClockPaint.color = ContextCompat.getColor(context, R.color.lineClock)
        lineClockPaint.style = Paint.Style.STROKE
        lineClockWidth = resources.getDimension(R.dimen.line_clock_width)

        centerClockPointPaint.color = ContextCompat.getColor(context, R.color.clockCenter)
        centerClockPointPaint.style = Paint.Style.FILL
        centerClockPointRadius = resources.getDimension(R.dimen.center_clock_point_radius)

        centerSecClockPointPaint.color = ContextCompat.getColor(context, R.color.secHand)
        centerSecClockPointPaint.style = Paint.Style.FILL
        centerSecClockPointRadius = resources.getDimension(R.dimen.center_sec_clock_point_radius)

        centerHourClockPointPaint.color = ContextCompat.getColor(context, R.color.hourHand)
        centerHourClockPointPaint.style = Paint.Style.FILL
        centerHourClockPointRadius = resources.getDimension(R.dimen.center_hour_clock_point_radius)

        numbersClockPaint.color = ContextCompat.getColor(context, R.color.clockNumbers)
        numbersClockPaint.style = Paint.Style.FILL
        numbersClockPaint.textSize = resources.getDimension(R.dimen.numbers_clock_size)
        numbersClockMargin = resources.getDimension(R.dimen.numbers_clock_margin)

        hourHandPaint.color = ContextCompat.getColor(context, R.color.hourHand)
        hourHandPaint.style = Paint.Style.STROKE
        hourHandPaint.strokeWidth = resources.getDimension(R.dimen.hour_hand_width)

        minHandPaint.color = ContextCompat.getColor(context, R.color.minHand)
        minHandPaint.style = Paint.Style.STROKE
        minHandPaint.strokeWidth = resources.getDimension(R.dimen.minute_hand_width)


        secHandPaint.color = ContextCompat.getColor(context, R.color.secHand)
        secHandPaint.style = Paint.Style.STROKE
        secHandPaint.strokeWidth = resources.getDimension(R.dimen.second_hand_width)
    }

    private fun numbersClockDraw(canvas: Canvas) {
        val radius = clockRadius + clockMargin - numbersClockMargin
        for (number in numbersClockArray) {
            val text = number.toString()
            numbersClockPaint.getTextBounds(text, 0, text.length, rect)
            val angle = Math.PI / 2 * (number + 1)
            val x = (clockCenterX + Math.cos(angle) * radius - rect.width() / 2)
            val y = (clockCenterY - Math.sin(angle) * radius + rect.height() / 2)
            canvas.drawText(text, x.toFloat(), y.toFloat(), numbersClockPaint)
        }
    }

    private fun lineClockDraw(canvas: Canvas) {
        lineClockPaint.strokeWidth = lineClockWidth
        val startMarkingLine = (clockCenterY - clockRadius) - lineClockLength / 2
        val endMarkingLine = (clockCenterY - clockRadius) + lineClockLength / 2
        canvas.save()
        for (i in 0..11) {
            canvas.drawLine(clockCenterX, startMarkingLine, clockCenterX, endMarkingLine, lineClockPaint)
            canvas.rotate(30f, clockCenterX, clockCenterY)
        }
        canvas.restore()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun clockHandsDraw(canvas: Canvas) {
        val calendar = Calendar.getInstance()
        var hour = calendar.get(Calendar.HOUR)
        otherHandLength = clockRadius - handsIndents
        hourHandLength = clockRadius - handsIndents - hourHandIndent
        drawConcreteHand(canvas, hourHandPaint, ((hour + calendar.get(Calendar.MINUTE) / 60.0) * 5), hourHandLength)
        drawConcreteHand(canvas, minHandPaint, calendar.get(Calendar.MINUTE).toDouble(), otherHandLength)
        drawConcreteHand(canvas, secHandPaint, calendar.get(Calendar.SECOND).toDouble(), otherHandLength)
    }

    private fun drawConcreteHand(canvas: Canvas, paint: Paint, value: Double, handLength: Float) {
        val angle = Math.PI * value / 30 - Math.PI / 2
        canvas.drawLine(
            clockCenterX, clockCenterY, (clockCenterX +
                    Math.cos(angle) *
                    handLength).toFloat(),
            (clockCenterY + Math.sin(angle) *
                    handLength).toFloat(),
            paint
        )
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        handsIndents = Math.min(width, height) / 20f
        hourHandIndent = Math.min(width, height) / 10f
        lineClockLength = Math.min(width, height) / 16f

        clockCenterX = width / 2f
        clockCenterY = height / 2f
        clockRadius = Math.min(clockCenterX, clockCenterY) - clockMargin
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        canvas.drawCircle(clockCenterX, clockCenterY, clockRadius, clockPaint)
        canvas.drawCircle(clockCenterX, clockCenterY, centerClockPointRadius, centerClockPointPaint)

        numbersClockDraw(canvas)

        lineClockDraw(canvas)

        canvas.drawCircle(clockCenterX, clockCenterY, centerHourClockPointRadius, centerHourClockPointPaint)
        clockHandsDraw(canvas)

        canvas.drawCircle(clockCenterX, clockCenterY, centerSecClockPointRadius, centerSecClockPointPaint)

        postInvalidateDelayed(500)
        invalidate()
    }
}