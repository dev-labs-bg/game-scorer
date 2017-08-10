package bg.devlabs.gamescorer.ui.custom_views

import android.content.Context
import android.graphics.PorterDuff
import android.os.Build
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import bg.devlabs.gamescorer.R


/**
 * Created by Slavi Petrov on 10.08.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
class ColoredButton : AppCompatButton {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.ColoredButton, 0, 0)
        val colorResource = a.getColor(R.styleable.ColoredButton_color_resource, 0)
        setColor(colorResource)
        a.recycle()
    }

    fun setColor(colorResourceId: Int) {
        val version = android.os.Build.VERSION.SDK_INT
        if (version > Build.VERSION_CODES.KITKAT) {
            this.background.setColorFilter(colorResourceId, PorterDuff.Mode.SRC_ATOP)
        } else {
            this.highlightColor = colorResourceId
            this.setBackgroundColor(colorResourceId)
        }
    }
}