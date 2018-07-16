package id.depok.depoksinglewindow.ui.forgotpassword

import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by PiNGUiN on 2017-12-14.
 */
interface ForgotPasswordContract {

    interface View: MvpContract.BaseView<Presenter> {

        fun close()

        fun showErrorEmail(stringId: Int)

        fun clearError()

    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onPressClose()

        fun onPressConfirm(email: String)
    }
}