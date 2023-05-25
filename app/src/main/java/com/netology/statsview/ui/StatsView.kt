package com.netology.statsview.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import androidx.lifecycle.viewmodel.CreationExtras
import com.netology.statsview.R
import com.netology.statsview.utils.AndroidUtils
import kotlin.math.min
import kotlin.random.Random

class StatsView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(
    context,
    attributeSet,
    defStyleAttr,
    defStyleRes
) {
    private var textSize = AndroidUtils.dp(context, 30).toFloat()
    private var lineWidth = AndroidUtils.dp(context, 3).toFloat()
    var colors = emptyList<Int>()

    init {
        context.withStyledAttributes(attributeSet, R.styleable.StatsView){
            textSize=getDimension(R.styleable.StatsView_textSize, textSize)
            lineWidth = getDimension(R.styleable.StatsView_lineWidth, lineWidth)

             colors = listOf(
                getColor(R.styleable.StatsView_color1, generateRandomColor()),
                getColor(R.styleable.StatsView_color2, generateRandomColor()),
                getColor(R.styleable.StatsView_color3, generateRandomColor()),
                getColor(R.styleable.StatsView_color4, generateRandomColor()),
                 getColor(R.styleable.StatsView_color1, generateRandomColor()),
            )
        }
    }
    var data : List<Float> = emptyList()
        set(value) {
            field =value
            invalidate()
        }

    var percent =100F
    private var radius = 0F
    private var center = PointF()//?
    private var oval = RectF()
//    private val lineWith = AndroidUtils.dp(context, 5)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = this@StatsView.lineWidth
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = this@StatsView.textSize
        textAlign = Paint.Align.CENTER
        strokeWidth = AndroidUtils.dp(context, 2).toFloat()
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = min(w, h) / 2F - this@StatsView.lineWidth //
        center = PointF(w / 2F, h / 2F)
        oval = RectF(
            center.x - radius,
            center.y - radius,
            center.x + radius,
            center.y + radius
        )
    }

//    override fun onDraw(canvas: Canvas) {
//        canvas.drawCircle(center.x, center.y, radius, paint)
//    }
    override fun onDraw(canvas: Canvas) {
        if(data.isEmpty()) {
            return
        }
        var startAngle = -90F
        partes(percent).forEachIndexed { index, datum ->
//            val angle = datum/data.sum()*360F
            val angle = datum*360F
            paint.color = colors.getOrElse(index) {generateRandomColor()}
            canvas.drawArc(oval,startAngle,angle,false,paint)
            startAngle +=angle
        }

    if(percent ==100F) {
//        putLastDot
        val lastDot = 1F
        paint.color = colors.getOrElse(0) {generateRandomColor()}
        canvas.drawArc(oval,startAngle,lastDot,false,paint)
    }



        canvas.drawText(
//            "100%",
            "%.2f%%".format(percent),
            center.x,
            center.y+textPaint.textSize/4,
            textPaint)

    println(partes(90F))

    println("s="+partes(90F).sum())

}
    private fun partes(percent: Float): List<Float> {
        val sum =data.sum()
        return data.map{
            data -> data/sum*(percent/100F)
        }

    }




    private fun generateRandomColor() = Random.nextInt(0xFF000000.toInt(), 0xFFFFFFFF.toInt())


}
