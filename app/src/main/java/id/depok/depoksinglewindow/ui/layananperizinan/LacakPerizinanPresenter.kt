package id.depok.depoksinglewindow.ui.layananperizinan

import id.depok.depoksinglewindow.ui.GenericPresenter
import org.koin.standalone.KoinComponent

/**
 * Created by PiNGUiN on 2017-12-20.
 */
class LacakPerizinanPresenter:
        GenericPresenter<LacakPerizinanContract.View>(),
        LacakPerizinanContract.Presenter,
        KoinComponent {

    override var view: LacakPerizinanContract.View? = null

    override fun start() {

    }

    override fun onPressBack() {
        view?.back()
    }

    override fun onPressSearch(token: String) {
        view?.search(token)
    }
}