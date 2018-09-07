package id.depok.depoksinglewindow.data.source

import id.depok.depoksinglewindow.data.api.*
import io.reactivex.Single

/**
 * Created by PiNGUiN on 2017-12-10.
 */
interface AuthDataSource {
    fun login(loginRequest: LoginRequest) : Single<LoginResponse>

    fun register(registerRequest: RegisterRequest) : Single<RegisterResponse>

    fun changePassword(changePasswordRequest: ChangePasswordRequest) : Single<ChangePasswordResponse>


    fun loginSigap(loginSigapRequest: LoginSigapRequest): Single<LoginSigapResponse>
}