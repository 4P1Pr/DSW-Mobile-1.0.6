package id.depok.depoksinglewindow.ui

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import dmax.dialog.SpotsDialog
import id.depok.depoksinglewindow.util.ui.DialogUtil
import id.depok.depoksinglewindow.util.ui.hideSoftKeyboard

/**
 * Created by PiNGUiN on 2017-12-23.
 */
abstract class BaseActivity<out T : MvpContract.BasePresenter<*>>:
        AppCompatActivity(),
        MvpContract.BaseView<T> {

    private var alertDialog: android.app.AlertDialog? = null

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showLoading(message: String?) {
        alertDialog = SpotsDialog(this, message)
        alertDialog?.setCancelable(false)
        alertDialog?.show()
    }

    override fun showLoading(messageId: Int) {
        alertDialog = SpotsDialog(this, getString(messageId))
        alertDialog?.setCancelable(false)
        alertDialog?.show()
    }

    override fun hideLoading() {
        alertDialog?.dismiss()
        alertDialog = null
    }

    override fun showAlert(titleId: Int, message: String) {
        DialogUtil().createAlertDialog(this, titleId, message,
                null, true).show()
    }

    override fun showAlert(titleId: Int, messageId: Int) {
        DialogUtil().createAlertDialog(this, titleId, messageId,
                null, true).show()
    }

    override fun showAlert(messageId: Int) {
        DialogUtil().createAlertDialog(this, getString(messageId),
                null, true).show()
    }

    override fun showAlert(message: String) {
        DialogUtil().createAlertDialog(this, message, null, true)
                .show()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showToast(messageId: Int) {
        Toast.makeText(this, messageId, Toast.LENGTH_LONG).show()
    }

    override fun hideKeyboard() {
        hideSoftKeyboard()
    }
}