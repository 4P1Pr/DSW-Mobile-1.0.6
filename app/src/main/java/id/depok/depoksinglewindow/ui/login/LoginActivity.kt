package id.depok.depoksinglewindow.ui.login

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.databinding.ActivityLoginBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import id.depok.depoksinglewindow.ui.forgotpassword.ForgotPasswordActivity
import id.depok.depoksinglewindow.ui.home.HomeActivity
import id.depok.depoksinglewindow.ui.register.RegisterActivity
import id.depok.depoksinglewindow.util.ui.LinkCallback
import id.depok.depoksinglewindow.util.ui.clickyfy
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity<LoginContract.Presenter>(), LoginContract.View {

    override val presenter by inject<LoginContract.Presenter>()

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        initialize()
    }

    override fun close() {
        super.onBackPressed()
    }

    override fun showForgotPassword() {
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
    }

    override fun showRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    override fun showMain() {
        finish()
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun showErrorEmail(stringId: Int) {
        binding.edittextLoginEmail.error = getString(stringId)
    }

    override fun showErrorPassword(stringId: Int) {
        binding.edittextLoginPassword.error = getString(stringId)
    }

    override fun clearError() {
        binding.apply {
            edittextLoginEmail.error = null
            edittextLoginPassword.error = null
        }
    }

    private fun initialize() {
        binding.apply {
            buttonLoginClosebutton.setOnClickListener { presenter.onPressClose() }
            buttonLoginLoginbutton.setOnClickListener {
                    presenter.onPressLogin(edittextLoginEmail.text.toString(),
                                            edittextLoginPassword.text.toString())
            }

            textviewLoginForgotpassword.clickyfy(
                this@LoginActivity,
                getString(R.string.login_forgotpassword),
                ContextCompat.getColor(this@LoginActivity, R.color.brightRed),
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
                    })
        }

        presenter.view = this
        presenter.start()
    }

}
