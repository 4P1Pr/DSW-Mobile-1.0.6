package id.depok.depoksinglewindow.util.ui

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import id.depok.depoksinglewindow.R

/**
 * Created by PiNGUiN on 2017-12-25.
 */
class DialogUtil {

    fun createAlertDialog(context: Context,
                          titleId: Int,
                          message: String,
                          onClickListener: DialogInterface.OnClickListener?,
                          cancellable: Boolean): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(titleId)
        builder.setMessage(message)
        builder.setCancelable(cancellable)
        builder.setPositiveButton(R.string.all_ok, onClickListener)
        return builder.create()
    }

    fun createAlertDialog(context: Context,
                          titleId: Int,
                          messageId: Int,
                          onClickListener: DialogInterface.OnClickListener?,
                          cancellable: Boolean): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(titleId)
        builder.setMessage(messageId)
        builder.setCancelable(cancellable)
        builder.setPositiveButton(R.string.all_ok, onClickListener)
        return builder.create()
    }

    fun createAlertDialog(mContext: Context,
                          message: String,
                          onClickListener: DialogInterface.OnClickListener?,
                          cancellable: Boolean): Dialog {
        val builder = AlertDialog.Builder(mContext)
        builder.setMessage(message)
        builder.setCancelable(cancellable)
        builder.setPositiveButton(R.string.all_ok, onClickListener)
        return builder.create()
    }

    fun createPositiveNegativeDialog(mContext: Context,
                                     messageStringRes: Int,
                                     mPositiveOnclickListener: DialogInterface.OnClickListener?,
                                     mNegativeOnclickListener: DialogInterface.OnClickListener?): Dialog {
        val builder = AlertDialog.Builder(mContext)
        builder.setMessage(messageStringRes)
        builder.setCancelable(false)
        builder.setPositiveButton(R.string.all_ok, mPositiveOnclickListener)
        builder.setNegativeButton(R.string.all_cancel, mNegativeOnclickListener)
        return builder.create()
    }
}