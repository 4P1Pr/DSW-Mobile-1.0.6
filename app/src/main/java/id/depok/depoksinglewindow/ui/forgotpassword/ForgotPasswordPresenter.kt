package id.depok.depoksinglewindow.ui.forgotpassword

import id.depok.depoksinglewindow.ui.GenericPresenter
import org.koin.standalone.KoinComponent

/**
 * Created by PiNGUiN on 2017-12-14.
 */
class ForgotPasswordPresenter:
        GenericPresenter<ForgotPasswordContract.View>(),
        ForgotPasswordContract.Presenter, KoinComponent {

    override var view: ForgotPasswordContract.View? = null

    override fun start() {

    }

    override fun onPressClose() {
        view?.close()
    }

    override fun onPressConfirm(email: String) {
    }
}