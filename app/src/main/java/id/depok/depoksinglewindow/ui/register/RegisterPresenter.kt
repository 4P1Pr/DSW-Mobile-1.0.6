package id.depok.depoksinglewindow.ui.register

import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.RegisterForm
import id.depok.depoksinglewindow.data.WORKER_ID
import id.depok.depoksinglewindow.data.WorkerData
import id.depok.depoksinglewindow.data.api.WorkerDataResponse
import id.depok.depoksinglewindow.data.source.WorkerDataRepository
import id.depok.depoksinglewindow.ui.GenericPresenter
import id.depok.depoksinglewindow.util.isValidEmailAddress
import id.depok.depoksinglewindow.util.rx.SchedulerProvider
import id.depok.depoksinglewindow.util.rx.with
import org.koin.standalone.KoinComponent

/**
 * Created by PiNGUiN on 2017-12-14.
 */
class RegisterPresenter(private val workerDataRepository: WorkerDataRepository,
                        private val schedulerProvider: SchedulerProvider):
        GenericPresenter<RegisterContract.View>(),
        RegisterContract.Presenter, KoinComponent {

    override var view: RegisterContract.View? = null

    override fun start() {
        request?.dispose()
        request = workerDataRepository.getWorkers()
                .with(schedulerProvider)
                .subscribe(
                        { response -> handlePekerjaanResponse(response) },
                        { handlePekerjaanError() }
                )

    }

    override fun onPressNext(formData: RegisterForm) {
        view?.hideKeyboard()
        if (isValid(formData)) {
            view?.showNextPage(formData)
        } else {
            view?.showAlert(R.string.all_datarequired)
        }
    }

    override fun onPekerjaanSelected(pekerjaan: WorkerData) {

//        if (pekerjaan.id > WORKER_ID) {
//            request?.dispose()
//            request = WorkerDataRepository.getPekerjaan(province.id)
//                    .with(schedulerProvider)
//                    .subscribe(
//                            { response -> handleKabupatenResponse(response) },
//                            { handleKabupatenError() }
//                    )
//        }
    }

    private fun isValid(form: RegisterForm): Boolean {
        var status = true
        view?.clearError()
        if (form.fullName.isEmpty()) {
            status = false
            view?.showErrorFullName(R.string.all_fieldrequired)
        }
        if (form.nickName.isEmpty()) {
            status = false
            view?.showErrorFullName(R.string.all_fieldrequired)
        }
        if (form.gender == 0) {
            status = false
        }
        if (form.province.id <= WORKER_ID) {
            status = false
        }
        if (form.email.isEmpty()) {
            status = false
            view?.showErrorEmail(R.string.all_fieldrequired)
        } else if (!form.email.isValidEmailAddress()) {
            status = false
            view?.showErrorEmail(R.string.all_errorinvalidemail)
        }

        if (form.password.isEmpty()) {
            status = false
            view?.showErrorPassword(R.string.all_fieldrequired)
        }
        if (form.phoneNumber.isEmpty()) {
            status = false
            view?.showErrorPhoneNumber(R.string.all_fieldrequired)
        }

        return status
    }

    private fun handlePekerjaanResponse(workerDataResponse: WorkerDataResponse) {
        if (workerDataResponse.status) {
            view?.showSetPekerjaan(workerDataResponse.workers)
        } else {
            view?.showToast(workerDataResponse.message)
        }
    }

    private fun handlePekerjaanError() {

    }

}