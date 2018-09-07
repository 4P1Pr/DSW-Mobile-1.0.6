package id.depok.depoksinglewindow.ui.layananpdam

import id.depok.depoksinglewindow.data.InfoLayananPDAM
import id.depok.depoksinglewindow.ui.GenericPresenter
import org.koin.standalone.KoinComponent

class LayananPdamPresenter:
        GenericPresenter<LayananPdamContract.View>(),
        LayananPdamContract.Presenter, KoinComponent {

    override var view: LayananPdamContract.View? = null

    override fun start() {

    }

    override fun onMenuSelected(menu: InfoLayananPDAM) {
        when(menu) {
            (InfoLayananPDAM.KELOMPOK_TARIF) -> {view?.showKelompokTarifPDAM()}
            (InfoLayananPDAM.TIPS_HEMAT_AIR) -> {view?.showTipsHematAirPDAM()}
            (InfoLayananPDAM.HAK_DAN_KEWAJIBAN) -> {view?.showHakdanKewajibanPelangganPDAM()}
            (InfoLayananPDAM.KELUHAN_PELANGGAN) -> {view?.showKeluhanPelanggan()}
            (InfoLayananPDAM.KELUHAN_NON_PELANGGAN) -> {view?.showKeluhanNonPelanggan()}
            else -> {
            }
        }
    }
}