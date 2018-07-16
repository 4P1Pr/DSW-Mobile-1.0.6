package id.depok.depoksinglewindow.data.api

import id.depok.depoksinglewindow.BuildConfig

/**
 * Created by PiNGUiN on 2017-12-10.
 */
object ApiSettings {
    const val PRAY_TIME_DATE_FORMAT = "yyyy-MM-dd"
    const val COMPLAINT_QUESTION_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss"

    private const val PATH_API = BuildConfig.PATH_API

    const val PATH_LOGIN = PATH_API + "auth/login"
    const val PATH_REGISTER = PATH_API + "auth/register"
    const val PATH_CHANGE_PASSWORD = PATH_API + "auth/change_password"
    const val PATH_COMPLAINT = PATH_API + "complaint"
    const val PATH_COMPLAINT_CATEGORIES = PATH_API + "complaint_category"
    const val PATH_QUESTION_CATEGORIES = PATH_API + "question_category"
    const val PATH_GET_COMPLAINT = PATH_API + "getcomplaint"
    const val PATH_GET_QUESTION = PATH_API + "aspiration"
    const val PATH_GET_PROVINCES = PATH_API + "provinsi"
    const val PATH_GET_KABUPATEN_BY_PROVINCE = PATH_API + "kabupaten"
    const val PATH_GET_KECAMATAN_BY_KABUPATEN = PATH_API + "kecamatan"
    const val PATH_GET_KELURAHAN_BY_KECAMATAN = PATH_API + "kelurahan"
    const val PATH_GET_SLIDER = PATH_API + "slider"
    const val PATH_LOG = PATH_API + "logactivity"

    private const val SIGAP_API = BuildConfig.SIGAP_API

    const val LOGIN_SIGAP = SIGAP_API + "users/login"

    const val URL_TAGIHAN_PBB = "https://182.23.86.31/dsw_fix/html/tagihan_pbb"
    const val URL_INFORMASI_PBB = "https://182.23.86.31/dsw_fix/html/pbb"
    const val URL_INFORMASI_PERIZINAN = "https://182.23.86.31/dsw_fix/html/perizinan"
    const val URL_LACAK_PERIZINAN = "https://182.23.86.31/dsw_fix/html/tracking_perizinan"
    const val URL_PERIZINAN_ONLINE = "https://perizinanonline.depok.go.id/"
    const val URL_REGISTRASI_LAYANAN_KESEHATAN = "https://182.23.86.31/dsw_fix/html/antri"
    const val URL_INFORMASI_LAYANAN_KESEHATAN = "https://182.23.86.31/dsw_fix/html/kesehatan"
    const val URL_OLD_PATIENT_QUEUE = "http://182.23.86.31/dsw/index.php/Home/antri"
    const val URL_CHECK_BPHTB = "https://182.23.86.31/dsw_fix/html/pembayaran_bphtb"
    const val URL_INFO_BPHTB = "https://182.23.86.31/dsw_fix/html/bphtb"
    const val URL_BAYAR_ZAKAT = "https://182.23.86.31/dsw_fix/html/bayar_zakat"
    const val URL_INFO_ZAKAT = "https://182.23.86.31/dsw_fix/html/info_zakat"
    const val URL_PETA_DEPOK = "https://182.23.86.31/dsw_fix/html/peta_depok"
    const val URL_BERITA = "https://182.23.86.31/dsw_fix/html/rss"
    const val URL_INFO_CUACA = "https://182.23.86.31/dsw_fix/html/cuaca"
    const val URL_INFO_KEMACETAN = "https://182.23.86.31/dsw_fix/html/kemacetan"
    const val URL_INFO_LOWONGAN_KERJA = "https://182.23.86.31/dsw_fix/html/loker"
    const val URL_PLN = "https://182.23.86.31/dsw_fix/html/info_pln"

    const val URL_SCHOOL_REGISTER = "https://182.23.86.31/dsw_fix/html/daftar_sekolah"
    const val URL_PASSINGGRADE = "https://182.23.86.31/dsw_fix/html/info_passinggrade"
    const val URL_KALENDER_PENDIDIKAN = "https://182.23.86.31/dsw_fix/html/kalender_pendidikan"
    const val URL_PPDB = "https://182.23.86.31/dsw_fix/html/ppdb_online"


}