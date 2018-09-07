package id.depok.depoksinglewindow.ui.sigap.loginsigap

import id.depok.depoksinglewindow.ui.MvpContract

interface SigapLoginContract {
    interface View: MvpContract.BaseView<Presenter> {

        fun close()

        fun showMain()

        fun showErrorEmail(stringId: Int)

        fun showErrorPassword(stringId: Int)

        fun clearError()
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onPressClose()

        fun onPressLogin(email: String, password: String)

    }
}