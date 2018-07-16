package id.depok.depoksinglewindow.ui.zakat

import id.depok.depoksinglewindow.ui.MvpContract

interface ZakatContract {

    interface View: MvpContract.BaseView<Presenter> {

        fun showBayarZakat()

        fun showInfoZakat()
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onPressBayarZakat()

        fun onPressInfoZakat()
    }
}