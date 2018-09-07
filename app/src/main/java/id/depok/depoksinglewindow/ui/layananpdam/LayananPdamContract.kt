package id.depok.depoksinglewindow.ui.layananpdam

import id.depok.depoksinglewindow.data.InfoLayananPDAM
import id.depok.depoksinglewindow.ui.MvpContract

interface LayananPdamContract {
    interface View: MvpContract.BaseView<Presenter> {

        fun showKelompokTarifPDAM()

        fun showTipsHematAirPDAM()

        fun showHakdanKewajibanPelangganPDAM()

        fun showKeluhanPelanggan()

        fun showKeluhanNonPelanggan()
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onMenuSelected(menu: InfoLayananPDAM)
    }
}