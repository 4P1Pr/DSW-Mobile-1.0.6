package id.depok.depoksinglewindow.ui.pdamdanpln

import id.depok.depoksinglewindow.data.PLNdanPDAMMenuType
import id.depok.depoksinglewindow.ui.MvpContract

interface PdamdanPlnContract {
    interface View: MvpContract.BaseView<Presenter> {

        fun showPDAM()

        fun showPLN()
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onMenuSelected(menu: PLNdanPDAMMenuType)
    }
}