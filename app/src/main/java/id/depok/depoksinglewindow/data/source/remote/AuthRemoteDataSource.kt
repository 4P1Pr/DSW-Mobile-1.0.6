package id.depok.depoksinglewindow.data.source.remote

import id.depok.depoksinglewindow.data.api.*
import id.depok.depoksinglewindow.data.source.AuthDataSource
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2017-12-10.
 */
class AuthRemoteDataSource(private val apiService: ApiService) : AuthDataSource {

    override fun login(loginRequest: LoginRequest): Single<LoginResponse> {
        return apiService.login(loginRequest)
    }

    override fun register(registerRequest: RegisterRequest): Single<RegisterResponse> {
        return apiService.register(registerRequest)
    }

    override fun changePassword(changePasswordRequest: ChangePasswordRequest): Single<ChangePasswordResponse> {
        return apiService.changePassword(changePasswordRequest.token, changePasswordRequest)
    }

}