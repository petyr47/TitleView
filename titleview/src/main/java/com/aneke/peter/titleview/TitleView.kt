package com.aneke.peter.titleview

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.view_title.view.*


class TitleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : FrameLayout(
    context,
    attrs) {

    private lateinit var attrsSet : TypedArray


    var showBackButton : Boolean = true



    var backgroundFrameColor : Int = 0


    var textColor : Int = 0

    var titleText = ""



    init {
        inflate(context, R.layout.view_title, this)
        attrs?.let {
            attrsSet = context.obtainStyledAttributes(attrs, R.styleable.TitleView)
            showBackButton = attrsSet.getBoolean(R.styleable.TitleView_showBackButton, true)
            backgroundFrameColor = attrsSet.getColor(R.styleable.TitleView_backgroundColor,
                ContextCompat.getColor(
                    context,
                    android.R.color.black))
            textColor = attrsSet.getColor(R.styleable.TitleView_textColor, ContextCompat.getColor(
                context,
                android.R.color.white))
            titleText = attrsSet.getString(R.styleable.TitleView_title) ?: ""
           // drawElements()
            attrsSet.recycle()
        }
    }


    private fun drawElements() {
        frame.setBackgroundColor(ContextCompat.getColor(context, backgroundFrameColor))
        title.setTextColor(ContextCompat.getColor(context, textColor))
        title.text = titleText
        if (!showBackButton) {
            back_btn.visibility = View.GONE
        } else {
            back_btn.visibility = View.VISIBLE
        }
        back_btn.setOnClickListener {
            getActivity()?.onBackPressed()
        }
    }

    private fun getActivity(): Activity? {
        var context = context
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }


}