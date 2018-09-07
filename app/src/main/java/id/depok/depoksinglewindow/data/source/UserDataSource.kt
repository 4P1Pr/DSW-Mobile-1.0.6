package id.depok.depoksinglewindow.data.source

import id.depok.depoksinglewindow.data.User
import id.depok.depoksinglewindow.data.UserSigap

/**
 * Created by PiNGUiN on 2017-12-08.
 */
interface UserDataSource {

    fun saveUser(user: User)

    fun saveUserSigap(userSigap: UserSigap)

    fun getUser(): User?

    fun getUserSigap(): UserSigap?

    fun deleteUser()

}