package id.depok.depoksinglewindow.util

/**
 * Created by PiNGUiN on 2018-01-08.
 */
fun String.isValidEmailAddress(): Boolean {
    return isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}