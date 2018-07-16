package id.depok.depoksinglewindow.data.source

import id.depok.depoksinglewindow.data.User

/**
 * Created by PiNGUiN on 2017-12-08.
 */
class UserRepository(
        val userLocalDataSource: UserDataSource) : UserDataSource {

    override fun saveUser(user: User) {
        userLocalDataSource.saveUser(user)
    }

    override fun getUser(): User? {
        return userLocalDataSource.getUser()
    }

    override fun deleteUser() {
        userLocalDataSource.deleteUser()
    }
}