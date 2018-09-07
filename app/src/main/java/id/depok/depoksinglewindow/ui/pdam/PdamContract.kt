package id.depok.depoksinglewindow.ui.pdam

import id.depok.depoksinglewindow.data.MenuPDAM
import id.depok.depoksinglewindow.ui.MvpContract

interface PdamContract {
    interface View: MvpContract.BaseView<Presenter> {

        fun showRegisterPDAM()

        fun showInfoLayananPDAM()

        fun showJaringanLayananPDAM()

        fun showCekTagihanPDAM()

        fun showInfoPembayaran()

        fun showSimulasiTagihan()
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onMenuSelected(menu: MenuPDAM)
    }
}