package id.depok.depoksinglewindow.data

import android.content.Context
import id.depok.depoksinglewindow.R

/**
 * Created by PiNGUiN on 2017-12-17.
 */
class MockData {

    fun getProvincePlaceHolder(context: Context, id: Long): LocationData{
        return LocationData(id, context.getString(R.string.all_province))
    }

    fun getKabupatenPlaceHolder(context: Context, id: Long): LocationData{
        return LocationData(id, context.getString(R.string.all_kabupaten))
    }

    fun getKecamatanPlaceHolder(context: Context, id: Long): LocationData{
        return LocationData(id, context.getString(R.string.all_kecamatan))
    }

    fun getKelurahanPlaceHolder(context: Context, id: Long): LocationData{
        return LocationData(id, context.getString(R.string.all_kelurahan))
    }

    fun getJawaBaratLocation(context: Context): LocationData{
        return LocationData(JAWA_BARAT_ID, context.getString(R.string.all_kelurahan))
    }

    fun getDepokLocation(context: Context): LocationData{
        return LocationData(DEPOK_ID, context.getString(R.string.all_kelurahan))
    }
}
