package id.depok.depoksinglewindow.ui.complaint

import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.api.GetComplaintQuestionResponse
import id.depok.depoksinglewindow.data.source.ComplaintQuestionQuestionRepository
import id.depok.depoksinglewindow.data.source.UserRepository
import id.depok.depoksinglewindow.manager.NetManager
import id.depok.depoksinglewindow.ui.GenericPresenter
import id.depok.depoksinglewindow.util.rx.SchedulerProvider
import id.depok.depoksinglewindow.util.rx.with

/**
 * Created by PiNGUiN on 2018-01-20.
 */
class ComplaintListPresenter(
        private val userRepository: UserRepository,
        private val complaintQuestionRepository: ComplaintQuestionQuestionRepository,
        private val schedulerProvider: SchedulerProvider,
        private val netManager: NetManager): GenericPresenter<ComplaintListContract.View>(),
        ComplaintListContract.Presenter {

    override var view: ComplaintListContract.View? = null
    override var isComplaint = true

    override fun start() {
        getData()
    }

    override fun onRefresh() {
        getData()
    }

    override fun onPressCreateButton() {
        view?.showCreatePage(isComplaint)
    }

    private fun getData() {
        netManager.isConnectedToInternet?.let {
            if (it) {
                view?.showDataLoading()
                if (isComplaint) {
                    userRepository.getUser()?.token?.let {
                        token -> complaintQuestionRepository.getComplaint(token)
                            .with(schedulerProvider)
                            .subscribe(
                                    { response -> handleComplaintQuestionResponse(response) },
                                    { handleComplaintQuestionError() }
                            )
                    }
                } else {
                    userRepository.getUser()?.token?.let {
                        token -> complaintQuestionRepository.getQuestion(token)
                            .with(schedulerProvider)
                            .subscribe(
                                    { response -> handleComplaintQuestionResponse(response) },
                                    { handleComplaintQuestionError() }
                            )
                    }
                }
            } else {
                view?.hideDataLoading()
                view?.showPlaceholderMessage(R.string.all_errorconnection)
            }
        }
    }

    private fun handleComplaintQuestionResponse(response: GetComplaintQuestionResponse) {
        view?.hideDataLoading()
        if (response.status) {
            view?.loadData(response.data)
        } else {
            view?.showPlaceholderMessage(response.message)
        }
    }

    private fun handleComplaintQuestionError() {
        view?.hideDataLoading()
        view?.showPlaceholderMessage(R.string.all_errorconnection)
    }
}