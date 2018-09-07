package id.depok.depoksinglewindow.ui.register

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.MOCK_ID
import id.depok.depoksinglewindow.data.MockData
import id.depok.depoksinglewindow.data.RegisterForm
import id.depok.depoksinglewindow.databinding.ActivityRegisterBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import org.koin.android.ext.android.inject

class RegisterActivity : BaseActivity<RegisterContract.Presenter>(), RegisterContract.View {

    override val presenter by inject<RegisterContract.Presenter>()

    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        initialize()
    }

    override fun close() {
        finish()
    }

    override fun showErrorFullName(stringId: Int) {
        binding.edittextRegisterFullname.error = getString(stringId)
    }

    override fun showErrorEmail(stringId: Int) {
        binding.edittextRegisterEmail.error = getString(stringId)
    }

    override fun showErrorPassword(stringId: Int) {
        binding.edittextRegisterPassword.error = getString(stringId)
    }

    override fun showErrorPhoneNumber(stringId: Int) {
        binding.edittextRegisterPhonenumber.error = getString(stringId)
    }

    override fun clearError() {
        binding.apply {
            edittextRegisterFullname.error = null
            edittextRegisterEmail.error = null
            edittextRegisterPassword.error = null
            edittextRegisterPhonenumber.error = null
        }
    }

    override fun showNextPage(formData: RegisterForm) {
        val intent = Intent(this, Register2Activity::class.java)
        intent.putExtra(ARG_FORM_DATA, formData)
        startActivity(intent)
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarRegister)

        binding.apply {
            toolbarRegister.setNavigationOnClickListener{ finish() }

            buttonRegisterNextbutton.setOnClickListener {
                presenter.onPressNext(RegisterForm(
                        edittextRegisterFullname.text.toString(),
                        edittextRegisterNickname.text.toString(),
                        spinnerRegisterGender.selectedItemPosition,
                        edittextRegisterEmail.text.toString(),
                        edittextRegisterPhonenumber.text.toString(),
                        1,
                        "",
                        MockData().getJawaBaratLocation(this@RegisterActivity),
                        MockData().getDepokLocation(this@RegisterActivity),
                        MockData().getKecamatanPlaceHolder(this@RegisterActivity, MOCK_ID),
                        MockData().getKelurahanPlaceHolder(this@RegisterActivity, MOCK_ID),
                        "",
                        edittextRegisterPassword.text.toString()
                ))
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }

}
