package id.depok.depoksinglewindow.ui.pembayaranpdam

import id.depok.depoksinglewindow.data.BankMenuType
import id.depok.depoksinglewindow.ui.MvpContract

interface PembayaranPdamContract {
    interface View: MvpContract.BaseView<Presenter> {

        fun showInfoBNI()

        fun showInfoMandiri()

        fun showInfoBCA()

        fun showPPOBdanBankLain()

    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onMenuSelected(menu: BankMenuType)
    }
}