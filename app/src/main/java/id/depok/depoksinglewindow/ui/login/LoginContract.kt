package id.depok.depoksinglewindow.ui.login

import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by PiNGUiN on 2017-12-12.
 */
interface LoginContract {

    interface View: MvpContract.BaseView<Presenter> {

        fun close()

        fun showForgotPassword()

        fun showRegister()

        fun showMain()

        fun showErrorEmail(stringId: Int)

        fun showErrorPassword(stringId: Int)

        fun clearError()

    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onPressClose()

        fun onPressLogin(email: String, password: String)

        fun onPressForgotPassword()

        fun onPressRegister()
    }
}
