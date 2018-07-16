package id.depok.depoksinglewindow.ui.pbb

import id.depok.depoksinglewindow.data.CekPBBMenuType
import id.depok.depoksinglewindow.ui.GenericPresenter
import org.koin.standalone.KoinComponent

/**
 * Created by PiNGUiN on 2017-12-19.
 */
class PbbPresenter :
        GenericPresenter<PbbContract.View>(),
        PbbContract.Presenter, KoinComponent {

    override var view: PbbContract.View? = null

    override fun start() {

    }

    override fun onPBBMenuClicked(menu: CekPBBMenuType) {
        when(menu) {
            (CekPBBMenuType.TAGIHAN_PBB) -> view?.showTagihanPBB()
            (CekPBBMenuType.INFORMASI_PBB) -> view?.showInformasiPBB()
            else -> {
            }
        }
    }
}