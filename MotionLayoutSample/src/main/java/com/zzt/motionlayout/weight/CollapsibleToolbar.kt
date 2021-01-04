package com.zzt.motionlayout.weight

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.android.material.appbar.AppBarLayout

/**
 * @author: zeting
 * @date: 2020/12/24
 *
 */
class CollapsibleToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MotionLayout(context, attrs, defStyleAttr), AppBarLayout.OnOffsetChangedListener {

    val TAG = CollapsibleToolbar::class.java.simpleName
    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        progress = -verticalOffset / appBarLayout?.totalScrollRange?.toFloat()!!
        Log.d(
            TAG,
            "verticalOffset:" + verticalOffset + " - " + appBarLayout?.totalScrollRange?.toFloat() +
                    " - " + (-verticalOffset / appBarLayout?.totalScrollRange?.toFloat()!!)
        )
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as? AppBarLayout)?.addOnOffsetChangedListener(this)
    }

}
