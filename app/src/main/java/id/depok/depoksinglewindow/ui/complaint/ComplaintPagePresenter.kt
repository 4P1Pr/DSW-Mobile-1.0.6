package id.depok.depoksinglewindow.ui.complaint

import android.net.Uri
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.ComplaintQuestionCategory
import id.depok.depoksinglewindow.data.api.ComplainQuestionCategoriesResponse
import id.depok.depoksinglewindow.data.api.ComplaintQuestionRequest
import id.depok.depoksinglewindow.data.api.ComplaintQuestionResponse
import id.depok.depoksinglewindow.data.source.ComplaintQuestionCategoryRepository
import id.depok.depoksinglewindow.data.source.ComplaintQuestionQuestionRepository
import id.depok.depoksinglewindow.data.source.UserRepository
import id.depok.depoksinglewindow.manager.AnalyticsManager
import id.depok.depoksinglewindow.manager.NetManager
import id.depok.depoksinglewindow.manager.SEND_COMPLAINT
import id.depok.depoksinglewindow.manager.SEND_QUESTION
import id.depok.depoksinglewindow.ui.GenericPresenter
import id.depok.depoksinglewindow.util.rx.SchedulerProvider
import id.depok.depoksinglewindow.util.rx.with
import org.koin.standalone.KoinComponent

/**
 * Created by PiNGUiN on 2017-12-20.
 */
class ComplaintPagePresenter(private val userRepository: UserRepository,
                             private val complaintQuestionRepository: ComplaintQuestionQuestionRepository,
                             private val complaintCategoryRepository: ComplaintQuestionCategoryRepository,
                             private val schedulerProvider: SchedulerProvider,
                             private val netManager: NetManager,
                             private val analyticsManager: AnalyticsManager):
        GenericPresenter<ComplaintPageContract.View>(),
        ComplaintPageContract.Presenter, KoinComponent {

    override var view: ComplaintPageContract.View? = null
    override var isComplaint = false
    override var userLongitude: Double = 0.0
    override var userLatitude: Double = 0.0
    override var incidentLongitude: Double? = null
    override var incidentLatitude: Double? = null

    private var imageUri: Uri? = null
    private var fileUri: Uri? = null

    private var type="1"

    override fun start() {
        request?.dispose()
        request = if(isComplaint) {
            type = "1"
            complaintCategoryRepository.getComplaintCategories(userRepository.getUser()?.token)
                    .with(schedulerProvider)
                    .subscribe(
                            { response -> handleComplaintCategoryResponse(response) },
                            { handleComplaintCategoryError() }
                    )

        } else {
            type = "2"
            complaintCategoryRepository.getQuestionCategories(userRepository.getUser()?.token)
                    .with(schedulerProvider)
                    .subscribe(
                            { response -> handleComplaintCategoryResponse(response) },
                            { handleComplaintCategoryError() }
                    )

        }
    }

    override fun onPressTakePhoto() {
        view?.takePhoto()
    }

    override fun onPressOpenGallery() {
        view?.openGallery()
    }

    override fun onPressAttachFile() {
        view?.attachFile()
    }

    override fun onPictureSelected(path: Uri?) {
        imageUri = path
        view?.setAttachedPicture(path)
    }

    override fun onFileAttached(path: Uri?) {
        if (path != null) {
           if (path.path.endsWith(".doc", true) or
                   path.path.endsWith(".docx", true) or
                   path.path.endsWith(".xls", true) or
                   path.path.endsWith(".xlsx", true) or
                   path.path.endsWith(".pdf", true)) {
               fileUri = path
               view?.setAttachedFile(path)
           } else {
               view?.showAlert(R.string.all_attachfileinfo)
           }
        }
    }

    override fun onPictureRemoved() {
        imageUri = null
        view?.removeAttachedPicture()
    }

    override fun onFileRemoved() {
        fileUri = null
        view?.removeAttachedFile()
    }

    override fun onPressBack() {
        view?.back()
    }

    override fun onPressSubmit(questionCategory: ComplaintQuestionCategory?, message: String) {
        if (isValid(questionCategory, message)) {
            netManager.isConnectedToInternet?.let {
                if (it) {
                    request?.dispose()
                    view?.showLoading(null)
                    request = complaintQuestionRepository.sendComplaintQuestion(ComplaintQuestionRequest(
                            (userRepository.getUser()?.name ?: ""),
                            (userRepository.getUser()?.email?: ""),
                            (userRepository.getUser()?.phoneNumber?: ""),
                            (questionCategory?.id?: 0),
                            message,
                            fileUri,
                            imageUri,
                            userRepository.getUser()?.token,
                            userLongitude,
                            userLatitude,
                            incidentLongitude,
                            incidentLatitude,
                            type
                    ))
                    .with(schedulerProvider)
                    .subscribe(
                            { complaintResponse -> handleComplaintResponse(complaintResponse) },
                            { handleComplaintError() } )
                } else {
                    view?.showAlert(R.string.all_errorconnection)
                }
            }
        } else {
            view?.showAlert(R.string.all_datarequired)
        }
    }

    override fun onReadStoragePermissionDenied() {
        view?.showAlert(R.string.all_errorreadpermissiondenied)
    }

    override fun onWriteStorageAndCameraPermissionDenied() {
        view?.showAlert(R.string.all_errorwriteandcameradenied)
    }

    override fun onLocationPermissionDenied() {
        view?.showAlert(R.string.all_errorLocationPermissionDenied)
        view?.showAlertLocationPermissionDenied()
    }

    override fun onCancelAddIncidentLocation() {
        incidentLatitude = null
        incidentLongitude = null
    }

    override fun onGPSTurnedOff() {
        view?.showAlertGPSTurnedOff()
    }

    override fun onSuccessInfoDismissed() {
        view?.closePage(true)
    }

    override fun onPressAddIncidentLocation() {
        view?.pickIncidentLocation()
    }

    private fun isValid(questionCategory: ComplaintQuestionCategory?, message: String) : Boolean {
        var status = true
        view?.clearError()
        if (questionCategory == null) {
            status = false
        }
        if (message.isEmpty()) {
            status = false
            view?.showErrorMessage(R.string.all_fieldrequired)
        }

        return status
    }

    private fun handleComplaintResponse(complaintQuestionResponse: ComplaintQuestionResponse) {
        view?.hideLoading()
        if (complaintQuestionResponse.status) {
            analyticsManager.logEvent(if (isComplaint) SEND_COMPLAINT else SEND_QUESTION)
            view?.informSubmitSuccess(R.string.complaintpage_successsendingform)
        } else {
            view?.showAlert(R.string.complaintpage_failedsendingform, complaintQuestionResponse.message)
        }
    }

    private fun handleComplaintError() {
        view?.hideLoading()
        view?.showAlert(R.string.complaintpage_failedsendingform)
    }

    private fun handleComplaintCategoryResponse(responseComplainQuestion: ComplainQuestionCategoriesResponse) {
        view?.hideLoading()
        if (responseComplainQuestion.status) {
            view?.loadCategories(responseComplainQuestion.data)
        } else {
            view?.showAlert(R.string.complaintpage_failedfetchcategory,
                    responseComplainQuestion.message)
        }
    }

    private fun handleComplaintCategoryError() {
        view?.hideLoading()
        view?.showAlert(R.string.complaintpage_failedfetchcategory)
    }
}