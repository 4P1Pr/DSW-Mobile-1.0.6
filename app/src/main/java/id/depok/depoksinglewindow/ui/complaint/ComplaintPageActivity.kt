package id.depok.depoksinglewindow.ui.complaint

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import droidninja.filepicker.FilePickerBuilder
import droidninja.filepicker.FilePickerConst
import id.depok.depoksinglewindow.BuildConfig
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.ComplaintQuestionCategory
import id.depok.depoksinglewindow.databinding.ActivityComplaintpageBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.util.DateFormat.TIMESTAMP_FORMAT
import id.depok.depoksinglewindow.util.ui.DialogUtil
import org.koin.android.ext.android.inject
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

const val ARG_IS_COMPLAINT = "arg_is_complaint"

private const val ZOOM_LEVEL = 17f

private const val RC_GET_PICTURE = 1
private const val RC_TAKE_PICTURE = 2
private const val RC_PICK_FILE = 3
private const val RC_PICK_LOCATION = 4

private const val RC_REQUEST_READ_STORAGE = 1
private const val RC_REQUEST_WRITE_STORAGE_AND_CAMERA = 2
private const val RC_REQUEST_LOCATION_ACCESS = 23
@Suppress("DEPRECATION")
class ComplaintPageActivity : BaseActivity<ComplaintPageContract.Presenter>(),
    ComplaintPageContract.View, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback{

    override val presenter by inject<ComplaintPageContract.Presenter>()
    lateinit var binding: ActivityComplaintpageBinding

    private var mCurrentPhotoPath: String? = null
    private var actionType: Int = RC_GET_PICTURE
    private var googleApiClient: GoogleApiClient? = null
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_complaintpage)

        initialize()
    }

    override fun onStart() {
        googleApiClient?.connect()
        super.onStart()
    }

    override fun onStop() {
        googleApiClient?.disconnect()
        super.onStop()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == RC_GET_PICTURE) {
                val selectedImage: Uri? = data?.data
                if (selectedImage != null) {
                    // crop image
                    CropImage.activity(selectedImage)
                            .setInitialCropWindowPaddingRatio(0f)
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .start(this)
                }
            } else if (requestCode == RC_TAKE_PICTURE) {
                if (mCurrentPhotoPath != null) {
                    val imagePath = mCurrentPhotoPath

                    val imageUri = Uri.fromFile(File(imagePath))
                    // crop image
                    CropImage.activity(imageUri)
                            .setInitialCropWindowPaddingRatio(0f)
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .start(this)
                }
            } else if (requestCode == RC_PICK_FILE) {
                val selectedFile: Uri? = data?.data
                presenter.onFileAttached(selectedFile)
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                // crop result
                val result = CropImage.getActivityResult(data)
                val resultUri = result.uri
                presenter.onPictureSelected(resultUri)
            } else if (requestCode == FilePickerConst.REQUEST_CODE_DOC) {
                val docPaths = ArrayList<String>()
                data?.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS)?.let {
                    docPaths.addAll(it)
                }
                if (docPaths.size > 0) {
                    presenter.onFileAttached(Uri.fromFile(File(docPaths[0])))
                }
            } else if (requestCode == RC_PICK_LOCATION) {
                val place: Place? = PlacePicker.getPlace(this, data)
                if (place != null) {
                    val location = place.latLng
                    onIncidentLocationSelected()
                    selectIncidentLocation(location.latitude, location.longitude)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var isRequestedPermissionsGranted = true
        for (grantResult in grantResults) {
            isRequestedPermissionsGranted = isRequestedPermissionsGranted &&
                    grantResult == PackageManager.PERMISSION_GRANTED
        }
        if (isRequestedPermissionsGranted) {
            when (requestCode) {
                RC_REQUEST_READ_STORAGE -> //resume tasks needing this permission
                    when(actionType) {
                        RC_GET_PICTURE -> dispatchGallery()
                        RC_PICK_FILE -> openFilePicker()
                    }
                RC_REQUEST_WRITE_STORAGE_AND_CAMERA -> //resume tasks needing this permission
                    dispatchCamera()
                RC_REQUEST_LOCATION_ACCESS -> // location permission granted
                    onConnected(null)
            }
        } else {
            when (requestCode) {
                RC_REQUEST_READ_STORAGE -> presenter.onReadStoragePermissionDenied()
                RC_REQUEST_WRITE_STORAGE_AND_CAMERA -> presenter.onWriteStorageAndCameraPermissionDenied()
                RC_REQUEST_LOCATION_ACCESS -> presenter.onLocationPermissionDenied()
            }
        }
    }

    override fun takePhoto() {
        if (isWriteStorageAndCameraPermissionGranted()) {
            dispatchCamera()
        }
    }

    override fun openGallery() {
        actionType = RC_GET_PICTURE
        if (isReadStoragePermissionGranted()) {
            dispatchGallery()
        }
    }

    override fun attachFile() {
        actionType = RC_PICK_FILE
        if (isReadStoragePermissionGranted()) {
            openFilePicker()
        }
    }

    override fun back() {
        finish()
    }

    override fun clearError() {
        binding.edittextComplaintpageMessage.error = null
    }

    override fun showErrorMessage(stringId: Int) {
        binding.edittextComplaintpageMessage.error = getString(stringId)
    }

    override fun setAttachedPicture(imageUri: Uri?) {
        binding.apply {
            Glide.with(this@ComplaintPageActivity)
                    .load(imageUri)
                    .into(imageviewComplaintpageAttachedimage)
            imageviewComplaintpageAttachedimage.visibility = View.VISIBLE
            buttonComplaintpageRemoveimagebutton.visibility = View.VISIBLE
            buttonComplaintpageTakephotobutton.visibility = View.GONE
            buttonComplaintpageOpengallerybutton.visibility = View.GONE
        }
    }

    override fun removeAttachedPicture() {
        binding.apply {
            imageviewComplaintpageAttachedimage.setImageBitmap(null)
            imageviewComplaintpageAttachedimage.visibility = View.GONE
            buttonComplaintpageRemoveimagebutton.visibility = View.GONE
            buttonComplaintpageTakephotobutton.visibility = View.VISIBLE
            buttonComplaintpageOpengallerybutton.visibility = View.VISIBLE
        }
    }

    override fun setAttachedFile(fileUri: Uri?) {
        binding.apply {
            buttonComplaintpageAttachfilebutton.visibility = View.GONE
            buttonComplaintpageRemovefilebutton.visibility = View.VISIBLE
            val file = File(fileUri?.path)
            textviewComplaintpageAttachfileinfo.text = file.name
        }
    }

    override fun removeAttachedFile() {
        binding.apply {
            buttonComplaintpageAttachfilebutton.visibility = View.VISIBLE
            buttonComplaintpageRemovefilebutton.visibility = View.GONE
            textviewComplaintpageAttachfileinfo.text = getString(R.string.all_attachfileinfo)
        }
    }

    override fun loadCategories(questionCategories: List<ComplaintQuestionCategory>) {
        binding.spinnerComplaintpageCategoryspinner.adapter =
            ArrayAdapter<ComplaintQuestionCategory>(this,
                    android.R.layout.simple_list_item_1,
                    questionCategories)
    }

    override fun showAlertLocationPermissionDenied() {
        DialogUtil().createAlertDialog(this,
                getString(R.string.all_errorLocationPermissionDenied),
                DialogInterface.OnClickListener { _,_ -> finish() },
                false).show()
    }

    override fun informSubmitSuccess(stringId: Int) {
        DialogUtil().createAlertDialog(this,
                getString(stringId),
                DialogInterface.OnClickListener { _,_ -> presenter.onSuccessInfoDismissed() },
                false).show()
    }

    override fun closePage(shouldRefresh: Boolean) {
        if (shouldRefresh) {
            setResult(Activity.RESULT_OK)
        }
        finish()
    }

    override fun showAlertGPSTurnedOff() {
        DialogUtil().createAlertDialog(this,
                getString(R.string.all_errorGPSTurnedOff),
                DialogInterface.OnClickListener { _,_ -> finish() },
                false).show()
    }

    override fun pickIncidentLocation() {
        val builder = PlacePicker.IntentBuilder()
        startActivityForResult(builder.build(this), RC_PICK_LOCATION)
    }

    override fun onConnected(bundle: Bundle?) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                // location permission not granted, ask permission
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        RC_REQUEST_LOCATION_ACCESS)
            } else {
                // location permission granted
                if (googleApiClient != null) {
                    handleRetrievedLocation(LocationServices.FusedLocationApi
                            .getLastLocation(googleApiClient))
                }
            }
        } else {
            // under marshmallow
            if (googleApiClient != null) {
                handleRetrievedLocation(LocationServices.FusedLocationApi
                        .getLastLocation(googleApiClient))
            }
        }
    }

    override fun onConnectionSuspended(p0: Int) {
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map

        googleMap?.uiSettings?.isZoomGesturesEnabled = false
        googleMap?.uiSettings?.isZoomControlsEnabled = false
        googleMap?.uiSettings?.isRotateGesturesEnabled = false
        googleMap?.uiSettings?.isScrollGesturesEnabled = false
        googleMap?.uiSettings?.isTiltGesturesEnabled = false
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarComplaintpage)

        val isComplaint = intent.extras.getBoolean(ARG_IS_COMPLAINT)

        binding.apply {
            toolbarComplaintpage.setNavigationOnClickListener{ finish() }

            if (!isComplaint) {
                textviewComplaintpageHeadertitle.text = getString(R.string.questionandaspiration_headerinfo)
                imageviewComplaintpageHeadericon.setImageDrawable(
                        ContextCompat.getDrawable(
                                this@ComplaintPageActivity,
                                R.drawable.icon_linewhite_aspirasi)
                )
            } else {
                textviewComplaintpageHeadertitle.text = getString(R.string.complaintpage_headerinfo)
                imageviewComplaintpageHeadericon.setImageDrawable(
                    ContextCompat.getDrawable(
                            this@ComplaintPageActivity,
                            R.drawable.icon_linewhite_pengaduan)
                )
            }

            buttonComplaintpageTakephotobutton.visibility =
                if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA))
                    View.VISIBLE
                else
                    View.GONE
            buttonComplaintpageTakephotobutton.setOnClickListener{ presenter.onPressTakePhoto() }
            buttonComplaintpageOpengallerybutton.setOnClickListener { presenter.onPressOpenGallery() }
            buttonComplaintpageAttachfilebutton.setOnClickListener { presenter.onPressAttachFile() }
            buttonComplaintpageBackbutton.setOnClickListener { presenter.onPressBack() }
            buttonComplaintpageRemoveimagebutton.setOnClickListener { presenter.onPictureRemoved() }
            buttonComplaintpageRemovefilebutton.setOnClickListener { presenter.onFileRemoved() }
            buttonComplaintpageSubmitbutton.setOnClickListener {
                presenter.onPressSubmit(spinnerComplaintpageCategoryspinner.selectedItem as ComplaintQuestionCategory?,
                        edittextComplaintpageMessage.text.toString())
            }
            buttonComplaintpageAddIncidentLocationButton.setOnClickListener{
                presenter.onPressAddIncidentLocation()
            }
            buttonComplaintpageCancelAddIncidentLocationButton.setOnClickListener {
                viewComplaintpageMapcontainer.visibility = View.GONE
                buttonComplaintpageCancelAddIncidentLocationButton.visibility = View.GONE
                buttonComplaintpageAddIncidentLocationButton.visibility = View.VISIBLE
                buttonComplaintpageEditIncidentLocationButton.visibility = View.GONE
                presenter.onCancelAddIncidentLocation()
            }
            buttonComplaintpageEditIncidentLocationButton.setOnClickListener{
                presenter.onPressAddIncidentLocation()
            }

            val fm: FragmentManager = supportFragmentManager
            val supportMapFragment = SupportMapFragment.newInstance()
            fm.beginTransaction().replace(R.id.view_complaintpage_mapcontainer, supportMapFragment)
                    .commit()
            supportMapFragment.getMapAsync(this@ComplaintPageActivity)
        }

        // init Google API Client
        if (googleApiClient == null)
            googleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.isComplaint = isComplaint
        presenter.start()
    }

    private fun isWriteStorageAndCameraPermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.CAMERA) ==
                            PackageManager.PERMISSION_GRANTED) {
                true
            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA), RC_REQUEST_WRITE_STORAGE_AND_CAMERA)
                false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            true
        }

    }

    private fun isReadStoragePermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED) {
                true
            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), RC_REQUEST_READ_STORAGE)
                false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            true
        }
    }

    private fun dispatchCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            // Create the File where the photo should go
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
                // Error occurred while creating the File
                ex.printStackTrace()
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                val photoURI = FileProvider.getUriForFile(this,
                        BuildConfig.APPLICATION_ID,
                        photoFile)
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)

                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
                    // grant permission to avoid crash on camera app for android Kitkat or lower
                    val resInfoList = packageManager.queryIntentActivities(takePictureIntent,
                            PackageManager.MATCH_DEFAULT_ONLY)
                    resInfoList
                        .map { it.activityInfo.packageName }
                        .forEach {
                            grantUriPermission(it, photoURI,
                                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION or
                                                Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        }
                }

                startActivityForResult(takePictureIntent, RC_TAKE_PICTURE)
            }
        } else {
            Toast.makeText(this, R.string.all_errorcameranotavailable, Toast.LENGTH_SHORT).show()
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat(TIMESTAMP_FORMAT,
                Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */
        )

        mCurrentPhotoPath = image.absolutePath
        return image
    }

    private fun dispatchGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)

        val type = "image/*"
        intent.type = type

        startActivityForResult(intent,
                RC_GET_PICTURE)
    }

    private fun openFilePicker() {
        FilePickerBuilder.getInstance().setMaxCount(1)
                .setActivityTheme(R.style.AppTheme_AppBarOverlay)
                .addFileSupport("DOC", arrayOf(".doc", ".docx"), R.drawable.ic_word)
                .addFileSupport("PDF", arrayOf(".pdf"), R.drawable.ic_pdf)
                .addFileSupport("XLS", arrayOf(".xls", ".xlsx"), R.drawable.ic_excel)
                .enableDocSupport(false)
                .pickFile(this)
    }

    private fun handleRetrievedLocation(location: Location?) {
        if (location != null) {
            presenter.userLongitude = location.longitude
            presenter.userLatitude = location.latitude
        } else {
            presenter.onGPSTurnedOff()
        }
    }

    private fun onIncidentLocationSelected() {
        binding.apply {
            viewComplaintpageMapcontainer.visibility = View.VISIBLE
            buttonComplaintpageCancelAddIncidentLocationButton.visibility = View.VISIBLE
            buttonComplaintpageAddIncidentLocationButton.visibility = View.GONE
            buttonComplaintpageEditIncidentLocationButton.visibility = View.VISIBLE
        }
    }

    private fun selectIncidentLocation(latitude: Double, longitude: Double) {
        presenter.incidentLatitude = latitude
        presenter.incidentLongitude = longitude

        val latLng = LatLng(latitude, longitude)
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(
                latLng, ZOOM_LEVEL))

        googleMap?.clear()

        val marker = MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_black_36dp))
        googleMap?.addMarker(marker)
    }
}
