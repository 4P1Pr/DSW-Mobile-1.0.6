package id.depok.depoksinglewindow.data.source

import id.depok.depoksinglewindow.data.api.*
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2017-12-10.
 */
class AuthRepository(private val authDataSource: AuthDataSource) : AuthDataSource {

    override fun login(loginRequest: LoginRequest): Single<LoginResponse> {
        return authDataSource.login(loginRequest)
    }

    override fun loginSigap(loginSigapRequest: LoginSigapRequest): Single<LoginSigapResponse> {
        return authDataSource.loginSigap(loginSigapRequest)
    }

    override fun register(registerRequest: RegisterRequest): Single<RegisterResponse> {
        return authDataSource.register(registerRequest)
    }

    override fun changePassword(changePasswordRequest: ChangePasswordRequest): Single<ChangePasswordResponse> {
        return authDataSource.changePassword(changePasswordRequest)
    }
}