package id.depok.depoksinglewindow.ui.pbb

import id.depok.depoksinglewindow.data.CekPBBMenuType
import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by PiNGUiN on 2017-12-19.
 */
interface PbbContract {

    interface View: MvpContract.BaseView<Presenter> {
        fun showTagihanPBB()

        fun showInformasiPBB()

        fun showInfoPembayaranPBB()
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onPBBMenuClicked(menu: CekPBBMenuType)
    }
}