package id.depok.depoksinglewindow.data.api

import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * Created by PiNGUiN on 2017-12-10.
 */
interface ApiService {

    @POST(ApiSettings.PATH_LOGIN)
    fun login(@Body loginRequest: LoginRequest) : Single<LoginResponse>

    @POST(ApiSettings.LOGIN_SIGAP)
    fun loginSigap(@Body loginSigapRequest:LoginSigapRequest) : Single<LoginSigapResponse>

    @POST(ApiSettings.PATH_REGISTER)
    fun register(@Body registerRequest: RegisterRequest) : Single<RegisterResponse>

    @POST(ApiSettings.PATH_CHANGE_PASSWORD)
    fun changePassword(@Header("Authorization") token: String?,
                       @Body changePasswordRequest: ChangePasswordRequest) : Single<ChangePasswordResponse>

    @Multipart
    @POST(ApiSettings.PATH_COMPLAINT)
    fun sendComplaint(
            @Header("Authorization") token: String?,

            @Part("name")
            name: RequestBody,

            @Part("email")
            email: RequestBody,

            @Part("phone")
            phone: RequestBody,

            @Part("category")
            category: RequestBody,

            @Part("message")
            message: RequestBody,

            @Part
            file: MultipartBody.Part?,

            @Part
            image: MultipartBody.Part?,

            @Part("user_coordinate_long")
            userLongitude: RequestBody,

            @Part("user_coordinate_lat")
            userLatitude: RequestBody,

            @Part("insident_coordinate_long")
            incicdentLongitude: RequestBody,

            @Part("insident_coordinate_lat")
            incidentLatitude: RequestBody,

            @Part("type")
            type: RequestBody
    ) : Single<ComplaintQuestionResponse>

    @GET(ApiSettings.PATH_GET_COMPLAINT)
    fun getComplaint(@Header("Authorization") token: String?): Single<GetComplaintQuestionResponse>

    @GET(ApiSettings.PATH_GET_QUESTION)
    fun getQuestion(@Header("Authorization") token: String?): Single<GetComplaintQuestionResponse>

    @GET(ApiSettings.PATH_COMPLAINT_CATEGORIES)
    fun getComplaintCategories(@Header("Authorization") token: String?): Single<ComplainQuestionCategoriesResponse>

    @GET(ApiSettings.PATH_QUESTION_CATEGORIES)
    fun getQuestionCategories(@Header("Authorization") token: String?): Single<ComplainQuestionCategoriesResponse>

    @GET(ApiSettings.PATH_GET_PROVINCES)
    fun getProvinces(): Single<LocationDataResponse>

    @GET(ApiSettings.PATH_GET_KABUPATEN_BY_PROVINCE)
    fun getKabupatens(@Query("province_id") provinceId: Long): Single<LocationDataResponse>

    @GET(ApiSettings.PATH_GET_KECAMATAN_BY_KABUPATEN)
    fun getKecamatans(@Query("regency_id") kabupatenId: Long): Single<LocationDataResponse>

    @GET(ApiSettings.PATH_GET_KELURAHAN_BY_KECAMATAN)
    fun getKelurahans(@Query("district_id") kecamatanId: Long): Single<LocationDataResponse>

    @GET(ApiSettings.PATH_GET_PEKERJAAN)
    fun getWorkers():Single<WorkerDataResponse>

    @GET(ApiSettings.PATH_GET_SLIDER)
    fun getImageSlider(): Single<ImageSliderResponse>

    @POST(ApiSettings.PATH_LOG)
    fun sendLogActivity(@Header("Authorization") token: String?,
                       @Body logActivityRequest: LogActivityRequest) : Single<ChangePasswordResponse>
}
