package by.itacademy.pvt.dz5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import android.graphics.RectF
import android.support.v4.content.ContextCompat
import by.itacademy.pvt.R


class Dz5View : View {

    private val rectF = RectF()
    private val slicePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintColors = arrayOf(Color.BLUE, Color.GREEN, Color.MAGENTA, Color.RED)

    private var scaledValues = FloatArray(0)
    var pieChartValues: FloatArray = FloatArray(0)
        set(value) {
            field = value
            scaledValues = scale(value)
            invalidate()
        }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        slicePaint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        var sliceStartPoint = 0f
        for (i in 0 until pieChartValues.size step 1) {
            slicePaint.color = paintColors[i]
            canvas.drawArc(rectF, sliceStartPoint, scaledValues[i], true, slicePaint)
            sliceStartPoint += scaledValues[i]
        }
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        val padding = Math.min(width, height) * 0.10f

        rectF.left = padding
        rectF.top = padding
        rectF.right = Math.min(width, height).toFloat() - padding
        rectF.bottom = Math.min(width, height).toFloat() - padding
    }

    private fun scale(array: FloatArray): FloatArray {
        var scaledValues = FloatArray(array.size)
        val sum = getSum(array)
        for (i in 0 until array.size step 1) {
            scaledValues[i] = array[i] / sum * 360f
        }
        return scaledValues
    }

    private fun getSum(array: FloatArray): Float {
        var sum = 0f
        for (i in array) {
            sum += i
        }
        return sum
    }
}