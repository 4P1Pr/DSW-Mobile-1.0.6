package id.depok.depoksinglewindow.ui.layananperizinan

import id.depok.depoksinglewindow.data.LayananPerizinanMenuType
import id.depok.depoksinglewindow.ui.GenericPresenter
import org.koin.standalone.KoinComponent

/**
 * Created by PiNGUiN on 2017-12-20.
 */
class LayananPerizinanPresenter:
        GenericPresenter<LayananPerizinanContract.View>(),
        LayananPerizinanContract.Presenter, KoinComponent {

    override var view: LayananPerizinanContract.View? = null

    override fun start() {

    }

    override fun onMenuSelected(menu: LayananPerizinanMenuType) {
        when(menu) {
            LayananPerizinanMenuType.LACAK_PERIZINAN -> view?.showLacakPerizinan()
            LayananPerizinanMenuType.INFORMASI_PERIZINAN -> view?.showInformasiPerizinan()
            LayananPerizinanMenuType.PERIZINAN_ONLINE -> view?.showPerizinanOnline()
        }
    }
}