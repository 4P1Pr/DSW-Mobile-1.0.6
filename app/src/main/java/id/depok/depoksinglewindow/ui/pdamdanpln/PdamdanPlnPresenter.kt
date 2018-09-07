package id.depok.depoksinglewindow.ui.pdamdanpln

import id.depok.depoksinglewindow.data.PLNdanPDAMMenuType
import id.depok.depoksinglewindow.ui.GenericPresenter
import org.koin.standalone.KoinComponent

class PdamdanPlnPresenter:
        GenericPresenter<PdamdanPlnContract.View>(),
        PdamdanPlnContract.Presenter, KoinComponent {

    override var view: PdamdanPlnContract.View? = null

    override fun start() {

    }

    override fun onMenuSelected(menu: PLNdanPDAMMenuType) {
        when(menu) {
            (PLNdanPDAMMenuType.PDAM) -> {
                view?.showPDAM()}
            (PLNdanPDAMMenuType.PLN) -> {
                view?.showPLN()}
            else -> {
            }
        }

    }

}
