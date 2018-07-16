package id.depok.depoksinglewindow.data.source

import id.depok.depoksinglewindow.data.User

/**
 * Created by PiNGUiN on 2017-12-08.
 */
interface UserDataSource {

    fun saveUser(user: User)

    fun getUser(): User?

    fun deleteUser()

}