package id.depok.depoksinglewindow.ui

/**
 * Created by PiNGUiN on 2017-12-07.
 */
interface MvpContract {

    interface BaseView<out T : BasePresenter<*>> {

        val presenter: T

        fun showLoading(messageId: Int)

        fun showLoading(message: String?)

        fun hideLoading()

        fun hideKeyboard()

        fun showAlert(messageId: Int)

        fun showAlert(message: String)

        fun showAlert(titleId: Int, message: String)

        fun showAlert(titleId: Int, messageId: Int)

        fun showToast(message: String)

        fun showToast(messageId: Int)
    }

    interface BasePresenter<T> {

        var view: T?

        fun start()

        fun onDestroy()
    }
}