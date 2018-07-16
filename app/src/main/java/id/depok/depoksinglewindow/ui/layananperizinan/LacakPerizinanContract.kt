package id.depok.depoksinglewindow.ui.layananperizinan

import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by PiNGUiN on 2017-12-20.
 */
interface LacakPerizinanContract {

    interface View: MvpContract.BaseView<Presenter> {

        fun back()

        fun search(token: String)
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onPressBack()

        fun onPressSearch(token: String)
    }
}