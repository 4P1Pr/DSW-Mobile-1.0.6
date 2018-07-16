package id.depok.depoksinglewindow.ui.layanankesehatan

import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by PiNGUiN on 2017-12-20.
 */
interface OldPatientQueuePageContract {

    interface View: MvpContract.BaseView<Presenter> {

        fun back()

        fun search()
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onPressBack()

        fun onPressSearch()
    }
}