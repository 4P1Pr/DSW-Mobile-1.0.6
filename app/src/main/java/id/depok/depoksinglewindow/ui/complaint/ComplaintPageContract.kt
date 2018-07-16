package id.depok.depoksinglewindow.ui.complaint

import android.net.Uri
import id.depok.depoksinglewindow.data.ComplaintQuestionCategory
import id.depok.depoksinglewindow.ui.MvpContract

/**
 * Created by PiNGUiN on 2017-12-20.
 */
interface ComplaintPageContract {

    interface View: MvpContract.BaseView<Presenter> {

        fun takePhoto()

        fun openGallery()

        fun attachFile()

        fun back()

        fun clearError()

        fun showErrorMessage(stringId: Int)

        fun setAttachedPicture(imageUri: Uri?)

        fun removeAttachedPicture()

        fun setAttachedFile(fileUri: Uri?)

        fun removeAttachedFile()

        fun loadCategories(questionCategories: List<ComplaintQuestionCategory>)

        fun showAlertLocationPermissionDenied()

        fun showAlertGPSTurnedOff()

        fun informSubmitSuccess(stringId: Int)

        fun closePage(shouldRefresh: Boolean)

        fun pickIncidentLocation()
    }

    interface Presenter: MvpContract.BasePresenter<View> {

        var isComplaint: Boolean

        var userLongitude: Double

        var userLatitude: Double

        var incidentLongitude: Double?

        var incidentLatitude: Double?

        fun onPressTakePhoto()

        fun onPressOpenGallery()

        fun onPressAttachFile()

        fun onPictureSelected(path: Uri?)

        fun onFileAttached(path: Uri?)

        fun onPictureRemoved()

        fun onFileRemoved()

        fun onPressBack()

        fun onPressSubmit(questionCategory: ComplaintQuestionCategory?, message: String)

        fun onReadStoragePermissionDenied()

        fun onWriteStorageAndCameraPermissionDenied()

        fun onLocationPermissionDenied()

        fun onCancelAddIncidentLocation()

        fun onGPSTurnedOff()

        fun onSuccessInfoDismissed()

        fun onPressAddIncidentLocation()
    }
}