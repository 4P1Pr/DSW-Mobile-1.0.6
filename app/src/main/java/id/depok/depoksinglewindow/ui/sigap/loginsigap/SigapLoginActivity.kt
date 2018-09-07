package id.depok.depoksinglewindow.ui.sigap.loginsigap

import android.content.Intent
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.home.HomeActivity
import kotlinx.android.synthetic.main.login_sigap.*
import org.koin.android.ext.android.inject


class SigapLoginActivity : BaseActivity<SigapLoginContract.Presenter>(), SigapLoginContract.View {

    override val presenter by inject<SigapLoginContract.Presenter>()

    //lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_sigap)

        initialize()
    }

    override fun close() {
        super.onBackPressed()
    }

    override fun showMain() {
       finish()
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun showErrorEmail(stringId: Int) {
        edittext_login_email_sigap.error = getString(stringId)
    }

    override fun showErrorPassword(stringId: Int) {
        edittext_login_password_sigap.error = getString(stringId)
    }

    override fun clearError() {
        apply {
            edittext_login_email_sigap.error = null
            edittext_login_password_sigap.error = null
        }
    }

    private fun initialize() {
        apply {
            button_login_closebutton_sigap.setOnClickListener { presenter.onPressClose() }
            button_login_loginbutton_sigap.setOnClickListener {
                presenter.onPressLogin(edittext_login_email_sigap.text.toString(),
                       edittext_login_password_sigap.text.toString())
            }

            /*textviewLoginForgotpassword.clickyfy(
                    this@SigapLoginActivity,
                    getString(R.string.login_forgotpassword),
                    ContextCompat.getColor(this@SigapLoginActivity, R.color.brightRed),
                    object : LinkCallback {
                        override fun onClick() {
                            presenter.onPressForgotPassword()
                        }
                    })

            textviewLoginDonthaveaccount.clickyfy(
                    this@LoginActivity,
                    getString(R.string.login_registerhere),
                    ContextCompat.getColor(this@LoginActivity, R.color.colorPrimary),
                    object : LinkCallback {
                        override fun onClick() {
                            presenter.onPressRegister()
                        }
                    })*/
        }

        presenter.view = this
        presenter.start()
    }

}
