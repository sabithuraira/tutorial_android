package com.dev.myapp.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.dev.myapp.R

class CustomView: View {
    private val backgroundPaint = Paint()
    private val armrestPaint = Paint()
    private val mBounds = Rect()
    private val bottomSeatPaint = Paint()
    private val numberSeatPaint = Paint(Paint.FAKE_BOLD_TEXT_FLAG)

    constructor(context: Context): super(context){
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs){
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle){
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        backgroundPaint.color = ResourcesCompat.getColor(resources, R.color.grey_400, null)
        armrestPaint.color = ResourcesCompat.getColor(resources, R.color.grey_800, null)
        bottomSeatPaint.color = ResourcesCompat.getColor(resources, R.color.grey_400, null)
        numberSeatPaint.color = ResourcesCompat.getColor(resources, R.color.white, null)

        canvas?.save()

        canvas?.translate(100F as Float, 100F as Float)

        val backgroundPath = Path()
        backgroundPath.addRect(0F, 0F, 200F, 200F, Path.Direction.CCW)
        backgroundPath.addCircle(100F, 50F, 75F, Path.Direction.CCW)
        canvas?.drawPath(backgroundPath, backgroundPaint)

        val armrestPath = Path()
        armrestPath.addRect(0F, 0F, 50F, 200F, Path.Direction.CCW)
        canvas?.drawPath(armrestPath, armrestPaint)
        canvas?.translate(150F, 0F)
        canvas?.drawPath(armrestPath, armrestPaint)

        canvas?.translate(-150F, 175F)
        val bottomSeatPath = Path()
        bottomSeatPath.addRect(0F, 0F, 200F, 25F, Path.Direction.CCW)
        canvas?.drawPath(bottomSeatPath, bottomSeatPaint)

        canvas?.translate(0F, -175F)
        numberSeatPaint.apply {
            textSize = 30F
            numberSeatPaint.getTextBounds("My View", 0, 7, mBounds)
        }
        canvas?.drawText("My view", 100F - mBounds.centerX(), 100F, numberSeatPaint)

        canvas?.restore()
    }
}