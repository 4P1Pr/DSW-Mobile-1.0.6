package id.depok.depoksinglewindow.ui.bphtb

import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by PiNGUiN on 2018-01-08.
 */
interface BphtbContract {

    interface View: MvpContract.BaseView<Presenter> {

        fun showCheckBphtb()

        fun showInfoBphtb()

        fun showInfoPembayaranBphtb()
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onPressCheckBphtb()

        fun onPressInfoBphtb()

        fun onPressInfoPembayaranBphtb()
    }
}