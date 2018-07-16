package id.depok.depoksinglewindow.ui.layananpendidikan

import id.depok.depoksinglewindow.data.LayananPendidikanMenuType
import id.depok.depoksinglewindow.ui.GenericPresenter
import org.koin.standalone.KoinComponent
/**
 * Created by fazhal on 11/01/18.
 */
class LayananPendidikanPresenter:
        GenericPresenter<LayananPendidikanContract.View>(),
        LayananPendidikanContract.Presenter, KoinComponent {


    override var view: LayananPendidikanContract.View? = null

    override fun start() {

    }

    override fun onMenuSelected(menu: LayananPendidikanMenuType) {
        when(menu) {
            (LayananPendidikanMenuType.DAFTAR_SEKOLAH) -> view?.showSchoolRegisterPage()
            (LayananPendidikanMenuType.NILAI_PASSINGGRADE) -> view?.showPassingGradePage()
            (LayananPendidikanMenuType.KALENDER_PENDIDIKAN) ->
                view?.showCalendarSchool()
            (LayananPendidikanMenuType.PPDB) ->
                view?.showPPDB()
            else -> {
            }
        }
    }
}