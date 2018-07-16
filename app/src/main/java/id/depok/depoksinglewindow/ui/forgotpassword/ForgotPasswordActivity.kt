package id.depok.depoksinglewindow.ui.forgotpassword

import android.databinding.DataBindingUtil
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.databinding.ActivityForgotPasswordBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import org.koin.android.ext.android.inject

class ForgotPasswordActivity : BaseActivity<ForgotPasswordContract.Presenter>(),
    ForgotPasswordContract.View{

    override val presenter by inject<ForgotPasswordContract.Presenter>()

    lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)

        initialize()
    }

    override fun close() {
        finish()
    }

    override fun showErrorEmail(stringId: Int) {
    }

    override fun clearError() {
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarForgotpassword)

        binding.apply {
            toolbarForgotpassword.setNavigationOnClickListener{ finish() }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}
