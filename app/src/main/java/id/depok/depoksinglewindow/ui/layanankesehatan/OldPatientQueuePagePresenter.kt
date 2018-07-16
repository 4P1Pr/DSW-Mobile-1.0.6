package id.depok.depoksinglewindow.ui.layanankesehatan

import id.depok.depoksinglewindow.ui.GenericPresenter
import org.koin.standalone.KoinComponent

/**
 * Created by PiNGUiN on 2017-12-20.
 */
class OldPatientQueuePagePresenter:
        GenericPresenter<OldPatientQueuePageContract.View>(),
        OldPatientQueuePageContract.Presenter, KoinComponent {

    override var view: OldPatientQueuePageContract.View? = null

    override fun start() {
    }

    override fun onPressBack() {
        view?.back()
    }

    override fun onPressSearch() {
        view?.search()
    }
}