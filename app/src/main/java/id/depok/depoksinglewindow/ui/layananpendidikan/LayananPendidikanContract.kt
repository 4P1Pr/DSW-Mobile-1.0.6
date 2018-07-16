package id.depok.depoksinglewindow.ui.layananpendidikan


import id.depok.depoksinglewindow.data.LayananPendidikanMenuType
import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by fazhal on 11/01/18.
 */
interface LayananPendidikanContract {

    interface View: MvpContract.BaseView<Presenter> {

        fun showSchoolRegisterPage()

        fun showPassingGradePage()

        fun showCalendarSchool()

        fun showPPDB()
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onMenuSelected(menu: LayananPendidikanMenuType)
    }
}