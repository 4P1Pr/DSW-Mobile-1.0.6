package id.depok.depoksinglewindow.ui.pln

import id.depok.depoksinglewindow.ui.MvpContract

interface PlnContract {
    interface View: MvpContract.BaseView<Presenter> {

        fun showBayarPLN()

        fun showInfoPLN()
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onPressBayarPLN()

        fun onPressInfoPLN()
    }
}