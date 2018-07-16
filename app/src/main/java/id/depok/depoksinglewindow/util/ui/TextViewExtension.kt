package id.depok.depoksinglewindow.util.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.Spanned
import android.view.View
import android.widget.TextView
import id.depok.depoksinglewindow.R

/**
 * Created by PiNGUiN on 2017-12-14.
 */
fun TextView.clickyfy(context: Context, clickableText: String, textColor: Int, callback: LinkCallback) {
    movementMethod = LinkTouchMovementMethod()
    highlightColor = ContextCompat
            .getColor(context, android.R.color.transparent)

    val textAsString = text.toString()
    val start = textAsString.indexOf(clickableText)
    val end = start + clickableText.length

    if(start == -1) return

    val spannable = SpannableString(textAsString)
    val span = object : TouchableSpan(textColor,
            textColor,
            ContextCompat.getColor(context, R.color.black10)) {
        override fun onClick(p0: View?) {
            callback.onClick()
        }
    }

    spannable.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    setText(spannable, TextView.BufferType.SPANNABLE)
}

interface LinkCallback {
    fun onClick()
}