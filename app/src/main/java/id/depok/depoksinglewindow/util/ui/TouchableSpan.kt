package id.depok.depoksinglewindow.util.ui

import android.graphics.Color
import android.text.TextPaint
import android.text.style.ClickableSpan



/**
 * Created by PiNGUiN on 2017-12-13.
 */
abstract class TouchableSpan(private val mNormalTextColor: Int,
                             private val mPressedTextColor: Int,
                             private val mPressedBackgroundColor: Int) : ClickableSpan() {
    private var mIsPressed: Boolean = false

    fun setPressed(isSelected: Boolean) {
        mIsPressed = isSelected
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.color = if (mIsPressed) mPressedTextColor else mNormalTextColor
        ds.bgColor = if (mIsPressed) mPressedBackgroundColor else Color.parseColor("#00000000")
    }
}