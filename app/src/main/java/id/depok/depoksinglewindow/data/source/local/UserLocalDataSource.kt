package id.depok.depoksinglewindow.data.source.local

import android.content.Context
import com.orhanobut.hawk.Hawk
import id.depok.depoksinglewindow.data.User
import id.depok.depoksinglewindow.data.source.UserDataSource

/**
 * Created by PiNGUiN on 2017-12-08.
 */

const val PREF_KEY_USER = "PREF_KEY_USER"

class UserLocalDataSource(val context: Context) : UserDataSource{

    override fun saveUser(user: User) {
        Hawk.put(PREF_KEY_USER, user)
    }

    override fun getUser(): User? {
        return Hawk.get<User>(PREF_KEY_USER)
    }

    override fun deleteUser() {
        Hawk.delete(PREF_KEY_USER)
    }
}