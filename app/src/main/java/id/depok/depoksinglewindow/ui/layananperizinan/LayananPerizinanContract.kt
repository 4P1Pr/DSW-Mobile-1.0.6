package id.depok.depoksinglewindow.ui.layananperizinan

import id.depok.depoksinglewindow.data.LayananPerizinanMenuType
import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by PiNGUiN on 2017-12-20.
 */
interface LayananPerizinanContract {

    interface View: MvpContract.BaseView<Presenter> {

        fun showLacakPerizinan()

        fun showInformasiPerizinan()

        fun showPerizinanOnline()
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onMenuSelected(menu: LayananPerizinanMenuType)
    }
}