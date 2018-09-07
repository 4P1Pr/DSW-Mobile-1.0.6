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

    //PBB
    const val URL_TAGIHAN_PBB = "https://103.113.31.29/dsw_fix/html/tagihan_pbb"
    const val URL_INFORMASI_PBB = "https://103.113.31.29/dsw_fix/html/pbb"
    const val URL_INFO_PEMBAYARAN_PBB = "https://103.113.31.29/dsw_fix/html/info_pembayaran_pbb"

    //Perizinan
    const val URL_INFORMASI_PERIZINAN = "https://103.113.31.29/dsw_fix/html/perizinan"
    const val URL_LACAK_PERIZINAN = "https://103.113.31.29/dsw_fix/html/tracking_perizinan"
    const val URL_PERIZINAN_ONLINE = "https://perizinanonline.depok.go.id/index.php/ijinmasuk"

    //Kesehatan
    const val URL_REGISTRASI_LAYANAN_KESEHATAN = "https://103.113.31.29/dsw_fix/html/antri"
    const val URL_INFORMASI_LAYANAN_KESEHATAN = "https://103.113.31.29/dsw_fix/html/kesehatan"
    const val URL_OLD_PATIENT_QUEUE = "http://182.23.86.31/dsw/index.php/Home/antri"
    const val URL_DAFTAR_PASIEN_LAMA = "http://simpus.depok.go.id:8072/pendaftaran/web/registrasi/dsw/lama"
    const val URL_DAFTAR_PASIEN_BARU = "http://simpus.depok.go.id:8072/pendaftaran/web/registrasi/dsw/baru"
    const val URL_BERITA_KESEHATAN = "https://103.113.31.29/dsw_fix/html/berita_kesehatan"

    //BPHTB
    const val URL_CHECK_BPHTB = "https://103.113.31.29/dsw_fix/html/pembayaran_bphtb"
    const val URL_INFO_BPHTB = "https://103.113.31.29/dsw_fix/html/bphtb"
    const val URL_INFO_PEMBAYARAN_BPHTB = "https://103.113.31.29/dsw_fix/html/info_pembayaran_bphtb"

    //Zakat
    const val URL_BAYAR_ZAKAT = "https://103.113.31.29/dsw_fix/html/bayar_zakat"
    const val URL_INFO_ZAKAT = "https://103.113.31.29/dsw_fix/html/info_zakat"

    //Fitur Lain
    const val URL_PETA_DEPOK = "https://103.113.31.29/dsw_fix/html/peta_depok"
    const val URL_BERITA = "https://103.113.31.29/dsw_fix/html/rss"
    const val URL_INFO_CUACA = "https://103.113.31.29/dsw_fix/html/cuaca"
    const val URL_INFO_KEMACETAN = "https://103.113.31.29/dsw_fix/html/lalulintas"
    const val URL_INFO_LOWONGAN_KERJA = "https://103.113.31.29/dsw_fix/html/loker"

    //PLN dan PDAM Depok
    const val URL_PLN = "https://103.113.31.29/dsw_fix/html/info_pln"
    const val URL_JARINGAN_LAYANAN_PDAM = "http://pdamdepok.co.id/informasi-edukasi-dsw/cakupan-layanan "
    const val URL_CEK_TAGIHAN_PDAM = "http://pdamdepok.co.id/layanan-online-dsw/info-tagihan "
    const val URL_REGISTRASI_PDAM = " http://pdamdepok.co.id/layanan-online-dsw/index "
    const val URL_INFORMASI_TARIF_LENGKAP_PDAM = "http://pdamdepok.co.id/informasi-edukasi-dsw/kelompok-tarif "
    const val URL_INFORMASI_TIPS_PDAM = " http://pdamdepok.co.id/informasi-edukasi-dsw/tips "
    const val URL_INFORMASI_HAK_DAN_KEWAJIBAN_PELANGGAN_PDAM = "http://pdamdepok.co.id/informasi-edukasi-dsw/hak-kewajiban "
    const val URL_INFO_ATM_BNI_PDAM = "http://pdamdepok.co.id/informasi-edukasi-dsw/pembayaran-atm-all "
    const val URL_INFO_ATM_MANDIRI_PDAM = "http://pdamdepok.co.id/informasi-edukasi-dsw/pembayaran-atm-mandiri "
    const val URL_SIMULASI_TAGIHAN_PDAM = "http://pdamdepok.co.id/informasi-edukasi-dsw/simulasi-tagihan "
    const val URL_INFO_ATM_BCA_PDAM = "http://pdamdepok.co.id/informasi-edukasi-dsw/pembayaran-atm-bni"
    const val URL_INFO_BANK_DAN_PPOB_PDAM = "http://pdamdepok.co.id/informasi-edukasi-dsw/pembayaran-atm-danamon"
    const val URL_LAYANAN_REGISTER_LAMA = "https://103.113.31.29/dsw_fix/html/layanan_registerlama"
    const val URL_LAYANAN_REGISTER_BARU = "https://103.113.31.29/dsw_fix/html/layanan_registerbaru"
    const val URL_KELUHAN_PELANGGAN_PDAM = "https://103.113.31.29/dsw_fix/html/layanan_keluhanpelanggan"
    const val URL_KELUHAN_NON_PELANGGAN_PDAM = "https://103.113.31.29/dsw_fix/html/layanan_keluhannonpelanggan"

    //Pendidikan
    const val URL_SCHOOL_REGISTER = "https://103.113.31.29/dsw_fix/html/daftar_sekolah"
    const val URL_PASSINGGRADE = "https://103.113.31.29/dsw_fix/html/info_passinggrade"
    const val URL_KALENDER_PENDIDIKAN = "https://103.113.31.29/dsw_fix/html/kalender_pendidikan"
    const val URL_PPDB = "https://103.113.31.29/dsw_fix/html/ppdb_online"

    //Call Center
    const val URL_CONTACT_CENTER_OPD = "https://103.113.31.29/dsw_fix/html/panggilan_opd"


}