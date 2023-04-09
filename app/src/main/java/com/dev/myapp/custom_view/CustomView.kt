package com.dev.myapp.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.dev.myapp.R

class CustomView: View {
    private val bgPaint = Paint()

    constructor(context: Context): super(context){
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs){
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle){
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        bgPaint.color = ResourcesCompat.getColor(resources, R.color.grey_400, null)

        canvas?.save()



    }
}