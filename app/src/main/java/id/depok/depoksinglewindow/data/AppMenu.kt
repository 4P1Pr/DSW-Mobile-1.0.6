package id.depok.depoksinglewindow.data

/**
 * Created by PiNGUiN on 2017-12-17.
 */

enum class AppMenuType {
    PANGGILAN_DARURAT,
    CEK_PBB,
    CEK_BPHTB,
    LAYANAN_PERIZINAN,
    LAYANAN_KESEHATAN,
    PERTANYAAN_DAN_ASPIRASI,
    PENGADUAN,
    CONTACT_CENTER,
    PENGATURAN,
    LAYANAN_PENDIDIKAN,
    LAYANAN_ZAKAT,
    PETA_DEPOK,
    BERITA_DEPOK,
    INFO_KEMACETAN,
    INFO_CUACA,
    INFO_LOWONGAN_KERJA,
    PLN_DAN_PDAM
}

enum class CekPBBMenuType {
    TAGIHAN_PBB,
    INFORMASI_PBB,
    INFO_PEMBAYARAN_PBB
}

enum class LayananPerizinanMenuType {
    LACAK_PERIZINAN,
    INFORMASI_PERIZINAN,
    PERIZINAN_ONLINE
}

enum class LayananKesehatanMenuType {
    NEW_PATIEN,
    OLD_PATIEN,
    HEALTHCARE_SERVICE_INFORMATION,
    HEALTHCARE_SERVICE_REGISTRATION,
    HEALTHCARE_SERVICE_HEALTH_NEWS
}

enum class LayananPendidikanMenuType {
    DAFTAR_SEKOLAH,
    NILAI_PASSINGGRADE,
    KALENDER_PENDIDIKAN,
    PPDB
}

enum class PLNdanPDAMMenuType{
    PDAM,
    PLN
}

enum class MenuPDAM{
    REGISTRASI_PDAM,
    INFO_LAYANAN_PDAM,
    JARINGAN_LAYANAN_PDAM,
    CEK_TAGIHAN_PDAM,
    INFO_PEMBAYARAN,
    SIMULASI_TAGIHAN
}

enum class BankMenuType{
    BNI,
    MANDIRI,
    BCA,
    PPOB_DAN_BANK_LAIN
}

enum class InfoLayananPDAM{
    KELOMPOK_TARIF,
    TIPS_HEMAT_AIR,
    HAK_DAN_KEWAJIBAN,
    KELUHAN_NON_PELANGGAN,
    KELUHAN_PELANGGAN
}

enum class CallCenterMenuType{
    CALL_CENTER_DEPOK,
    CALL_CENTER_OPD
}

enum class RegisterPelangganMenuPDAMType{
    REGISTER_PELANGGAN_LAMA_PDAM,
    REGISTER_PELANGGAN_BARU_PDAM
}

enum class RegisterPatienType{
    REGISTER_OLD_PATIEN,
    REGISTER_NEW_PATIEN
}

enum class SettingsMenuType {
    PROFILE,
    EDIT_PASSWORD,
    ABOUT,
    DEV_TEAM,
    LOGOUT
}

class AppMenu(val typeApp: AppMenuType, val iconResId: Int, val stringResId: Int)