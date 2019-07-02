package by.itacademy.pvt.dz5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.graphics.RectF
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import by.itacademy.pvt.R
import kotlin.math.cos
import kotlin.math.sin


class Dz5View : View {

    private val pieChartRectF = RectF()
    private val pieChartTextRect = Rect()

    private val pieChartColors = arrayOf(
        R.color.pie_chart_1,
        R.color.pie_chart_2,
        R.color.pie_chart_3,
        R.color.pie_chart_4,
        R.color.pie_chart_5)

    private var pieChartRadius = 0f
    private var plateCX = 0f
    private var plateCY = 0f

    private var pieChartPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var pieChartTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var pieChartLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)


    private var pieChartScaledValues = FloatArray(0)
    var pieChartValues: FloatArray = FloatArray(0)
        set(value) {
            field = value
            pieChartScaledValues = scale(value)
            invalidate()
        }

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
        pieChartPaint.style = Paint.Style.FILL

        pieChartTextPaint.color = ContextCompat.getColor(context, R.color.black)
        pieChartTextPaint.textSize = resources.getDimension(R.dimen.pie_chart_text_size)

        pieChartLinePaint.color = ContextCompat.getColor(context, R.color.white)
        pieChartLinePaint.style = Paint.Style.FILL
        pieChartLinePaint.strokeWidth = resources.getDimension(R.dimen.pie_chart_line_width)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        var radialAngle: Double
        val textOffset = 1.4f
        var paintColorsIndex = 0
        var sliceStartPoint = 0f

        pieChartValues.zip(pieChartScaledValues).forEach {
            pieChartPaint.color = ContextCompat.getColor(context, pieChartColors[paintColorsIndex++])
            paintColorsIndex %= pieChartColors.size

            radialAngle = (sliceStartPoint + it.second / 2) * Math.PI / 180
            val ringX = (plateCX + (pieChartRadius * cos(radialAngle))).toFloat()
            val ringY = (plateCY + (pieChartRadius * sin(radialAngle))).toFloat()

            val textX = (plateCX + ((pieChartRadius * textOffset) * cos(radialAngle))).toFloat()
            val textY = (plateCY + ((pieChartRadius * textOffset) * sin(radialAngle))).toFloat()
            val text = it.first.toString()
            pieChartTextPaint.getTextBounds(text, 0, text.length, pieChartTextRect)
            val textWidth = pieChartTextPaint.measureText(text, 0, text.length) / 2.0f

            drawableInfoRing(canvas, ringX, ringY, textX, textY, textWidth)
            canvas.drawText(text, textX - textWidth, textY + pieChartTextRect.height() / 2, pieChartTextPaint)

            canvas.drawArc(pieChartRectF, sliceStartPoint, it.second, true, pieChartPaint)
            sliceStartPoint += it.second
        }
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        pieChartRadius = Math.min(width, height) * 0.3f
        plateCX = width / 2f
        plateCY = height / 2f

        pieChartRectF.left = plateCX - pieChartRadius
        pieChartRectF.top = plateCY - pieChartRadius
        pieChartRectF.right = plateCX + pieChartRadius
        pieChartRectF.bottom = plateCY + pieChartRadius
    }

    private fun scale(array: FloatArray): FloatArray {
        val values = FloatArray(array.size)
        val sum = getSum(array)
        for (i in 0 until array.size) {
            values[i] = array[i] / sum * 360f
        }
        return values
    }

    private fun getSum(array: FloatArray): Float {
        var sum = 0f
        for (i in array) {
            sum += i
        }
        return sum
    }

    private fun drawableInfoRing(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float, textWidth: Float) {
        canvas.drawLine(startX, startY, endX, endY, pieChartLinePaint)
        val radiusCircle = textWidth * 1.25f
        canvas.drawCircle(endX, endY, radiusCircle, pieChartLinePaint)
    }
}