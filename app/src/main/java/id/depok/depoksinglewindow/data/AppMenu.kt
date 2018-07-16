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
    PLN
}

enum class CekPBBMenuType {
    TAGIHAN_PBB,
    INFORMASI_PBB
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
    HEALTHCARE_SERVICE_REGISTRATION
}

enum class LayananPendidikanMenuType {
    DAFTAR_SEKOLAH,
    NILAI_PASSINGGRADE,
    KALENDER_PENDIDIKAN,
    PPDB
}

enum class SettingsMenuType {
    PROFILE,
    EDIT_PASSWORD,
    ABOUT,
    DEV_TEAM,
    LOGOUT
}

class AppMenu(val typeApp: AppMenuType, val iconResId: Int, val stringResId: Int)