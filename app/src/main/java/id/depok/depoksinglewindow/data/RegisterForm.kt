package id.depok.depoksinglewindow.data

import id.depok.depoksinglewindow.data.api.RegisterRequest
import java.io.Serializable

/**
 * Created by PiNGUiN on 2017-12-14.
 */
data class RegisterForm(
        val fullName: String,
        val gender: Int,
        val email: String,
        val phoneNumber: String,
        val isDepokResidents: Int,
        var address: String,
        var province: LocationData,
        var kabupaten: LocationData,
        var kecamatan: LocationData,
        var kelurahan: LocationData,
        var postalCode: String,
        val password: String
): Serializable {

    fun createRegisterRequest(): RegisterRequest {
        return RegisterRequest(
                this.email,
                this.phoneNumber,
                this.isDepokResidents,
                this.fullName,
                this.gender - 1,
                this.address,
                this.postalCode,
                this.province.id,
                this.kabupaten.id,
                this.kecamatan.id,
                this.kelurahan.id,
                this.password
        )
    }
}