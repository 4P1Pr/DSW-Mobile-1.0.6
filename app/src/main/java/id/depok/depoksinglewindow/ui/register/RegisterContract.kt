package id.depok.depoksinglewindow.ui.register

import id.depok.depoksinglewindow.data.RegisterForm
import id.depok.depoksinglewindow.data.WorkerData
import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by PiNGUiN on 2017-12-14.
 */
interface RegisterContract {

    interface View: MvpContract.BaseView<Presenter> {

        fun close()

        fun showErrorFullName(stringId: Int)

        fun showErrorNickName (stringId: Int)

        fun showErrorEmail(stringId: Int)

        fun showErrorPassword(stringId: Int)

        fun showErrorPhoneNumber(stringId: Int)

        fun showSetPekerjaan(workerlist : MutableList<WorkerData>)

        fun clearError()

        fun showNextPage(formData: RegisterForm)
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        fun onPressNext(formData: RegisterForm)

        fun onPekerjaanSelected (pekerjaan: WorkerData)
    }
}