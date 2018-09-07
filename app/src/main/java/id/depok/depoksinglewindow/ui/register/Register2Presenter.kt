package id.depok.depoksinglewindow.ui.register

import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.DEPOK_ID
import id.depok.depoksinglewindow.data.LocationData
import id.depok.depoksinglewindow.data.MOCK_ID
import id.depok.depoksinglewindow.data.RegisterForm
import id.depok.depoksinglewindow.data.api.LocationDataResponse
import id.depok.depoksinglewindow.data.api.LoginResponse
import id.depok.depoksinglewindow.data.api.RegisterResponse
import id.depok.depoksinglewindow.data.source.AuthRepository
import id.depok.depoksinglewindow.data.source.LocationDataRepository
import id.depok.depoksinglewindow.data.source.UserRepository
import id.depok.depoksinglewindow.manager.AnalyticsManager
import id.depok.depoksinglewindow.manager.NetManager
import id.depok.depoksinglewindow.ui.GenericPresenter
import id.depok.depoksinglewindow.util.rx.SchedulerProvider
import id.depok.depoksinglewindow.util.rx.with
import org.koin.standalone.KoinComponent

/**
 * Created by PiNGUiN on 2017-12-14.
 */
class Register2Presenter(private val authRepository: AuthRepository,
                         private val userRepository: UserRepository,
                         private val locationDataRepository: LocationDataRepository,
                         private val netManager: NetManager,
                         private val schedulerProvider: SchedulerProvider,
                         private val analyticsManager: AnalyticsManager):
        GenericPresenter<Register2Contract.View>(),
        Register2Contract.Presenter, KoinComponent {

    override var view: Register2Contract.View? = null
    override var initialFormData: RegisterForm? = null

    override fun start() {
        request?.dispose()
        request = locationDataRepository.getProvinces()
                .with(schedulerProvider)
                .subscribe(
                        { response -> handleProvinceResponse(response) },
                        { handleProvinceError() }
                )

    }

    override fun onDepokResidencesSelected(index: Int) {
        view?.clearError()
        if(index == 0){
            view?.hideProvinceSpinner()
            view?.hideKabupatenSpinner()

            view?.clearKecamatans()
            view?.clearKelurahans()

            // get kecamatan on Depok
            request?.dispose()
            request = locationDataRepository.getKecamatans(DEPOK_ID)
                    .with(schedulerProvider)
                    .subscribe(
                            { response -> handleKecamatanResponse(response) },
                            { handleKecamatanError() }
                    )
        } else{
            view?.showProvinceSpinner()
            view?.showKabupatenSpinner()

            view?.clearKabupatens()
            view?.clearKecamatans()
            view?.clearKelurahans()

            request = locationDataRepository.getProvinces()
                    .with(schedulerProvider)
                    .subscribe(
                            { response -> handleProvinceResponse(response) },
                            { handleProvinceError() }
                    )
        }
    }

    override fun onProvinceSelected(province: LocationData) {
        view?.clearKabupatens()
        view?.clearKecamatans()
        view?.clearKelurahans()

        if (province.id > MOCK_ID) {
            request?.dispose()
            request = locationDataRepository.getKabupatens(province.id)
                    .with(schedulerProvider)
                    .subscribe(
                            { response -> handleKabupatenResponse(response) },
                            { handleKabupatenError() }
                    )
        }
    }

    override fun onKabupatenSelected(kabupaten: LocationData) {
        view?.clearKecamatans()
        view?.clearKelurahans()

        if (kabupaten.id > MOCK_ID) {
            request?.dispose()
            request = locationDataRepository.getKecamatans(kabupaten.id)
                    .with(schedulerProvider)
                    .subscribe(
                            { response -> handleKecamatanResponse(response) },
                            { handleKecamatanError() }
                    )
        }
    }

    override fun onKecamatanSelected(kecamatan: LocationData) {
        view?.clearKelurahans()

        if (kecamatan.id > MOCK_ID) {
            request?.dispose()
            request = locationDataRepository.getKelurahans(kecamatan.id)
                    .with(schedulerProvider)
                    .subscribe(
                            { response -> handleKelurahaanResponse(response) },
                            { handleKelurahanError() }
                    )
        }
    }

    override fun onPressRegister(formData: RegisterForm) {
        view?.hideKeyboard()
        if (isValid(formData)) {
            netManager.isConnectedToInternet?.let {
                if (it) {
                    request?.dispose()
                    view?.showLoading(null)

                    val combinedData: RegisterForm? = initialFormData?.let { it1 -> combineFormData(it1, formData) }
                    if (combinedData == null) {
                    } else {
                        request = authRepository.register(combinedData.createRegisterRequest())
                                .with(schedulerProvider)
                                .subscribe(
                                        { registerResponse -> handleRegisterResponse(registerResponse)},
                                        { handleRegisterError()}
                                )
                    }
                } else {
                    view?.showAlert(R.string.all_errorconnection)
                }
            }
        } else {
            view?.showAlert(R.string.all_datarequired)
        }
    }

    private fun isValid(form: RegisterForm): Boolean {
        var status = true
        view?.clearError()

        if (form.address.isEmpty()) {
            status = false
            view?.showErrorAddress(R.string.all_fieldrequired)
        }
        if (form.postalCode.isEmpty()) {
            status = false
            view?.showErrorPostalCode(R.string.all_fieldrequired)
        }
        if (form.isDepokResidents != 1) {
            if (form.province.id <= MOCK_ID) {
                status = false
            }
            if (form.kabupaten.id <= MOCK_ID) {
                status = false
            }
        }
        if (form.kecamatan.id <= MOCK_ID) {
            status = false
        }
        if (form.kelurahan.id <= MOCK_ID) {
            status = false
        }

        return status
    }

    private fun handleRegisterResponse(registerResponse: RegisterResponse) {
        view?.hideLoading()
        if(registerResponse.status) {
            userRepository.saveUser(registerResponse.user)
            analyticsManager.logSuccessfulRegister()
            view?.showToast(R.string.register_registersuccess)
            view?.showMain()
            // log successful register


            // login
            /*request?.dispose()
            if (initialFormData?.email != null && initialFormData?.password != null) {
                request = authRepository.login(
                            LoginRequest(initialFormData?.email!!,
                                        initialFormData?.password!!))
                                .with(schedulerProvider)
                                .subscribe(
                                    { loginResponse -> handleLoginResponse(loginResponse) },
                                    { handleLoginError() }
                                )
            } else {
                view?.hideLoading()
                view?.showAlert(R.string.login_loginfailed)
            }*/
        } else {
            view?.showAlert(R.string.register_registerfailed, registerResponse.message)
        }
    }

    private fun handleRegisterError() {
        view?.hideLoading()
        view?.showAlert(R.string.register_registerfailed)
    }

    private fun handleLoginResponse(loginResponse: LoginResponse) {
        view?.hideLoading()
        if(loginResponse.status) {
            // log successful login
            analyticsManager.logSuccessfulLogin()

            userRepository.saveUser(loginResponse.user)
            view?.showToast(R.string.register_registersuccess)
            view?.showMain()
        } else {
            view?.showAlert(R.string.login_loginfailed, loginResponse.message)
        }
    }

    private fun handleLoginError() {
        view?.hideLoading()
        view?.showAlert(R.string.login_loginfailed)
    }

    private fun handleProvinceResponse(locationDataResponse: LocationDataResponse) {
        if (locationDataResponse.status) {
            view?.setProvinces(locationDataResponse.locations)
        } else {
            view?.showToast(locationDataResponse.message)
        }
    }

    private fun handleProvinceError() {

    }

    private fun handleKabupatenResponse(locationDataResponse: LocationDataResponse) {
        if (locationDataResponse.status) {
            view?.setKabupatens(locationDataResponse.locations)
        } else {
            view?.showToast(locationDataResponse.message)
        }
    }

    private fun handleKabupatenError() {

    }

    private fun handleKecamatanResponse(locationDataResponse: LocationDataResponse) {
        if (locationDataResponse.status) {
            view?.setKecamatans(locationDataResponse.locations)
        } else {
            view?.showToast(locationDataResponse.message)
        }
    }

    private fun handleKecamatanError() {

    }

    private fun handleKelurahaanResponse(locationDataResponse: LocationDataResponse) {
        if (locationDataResponse.status) {
            view?.setKelurahans(locationDataResponse.locations)
        } else {
            view?.showToast(locationDataResponse.message)
        }
    }

    private fun handleKelurahanError() {

    }

    private fun combineFormData(initialFormData: RegisterForm, formData: RegisterForm): RegisterForm
            = RegisterForm(
                initialFormData.fullName,
                initialFormData.nickName,
                initialFormData.gender,
                initialFormData.email,
                initialFormData.phoneNumber,
                formData.isDepokResidents,
                formData.address,
                formData.province,
                formData.kabupaten,
                formData.kecamatan,
                formData.kelurahan,
                formData.postalCode,
                initialFormData.password
            )
}