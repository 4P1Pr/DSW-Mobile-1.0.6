package id.depok.depoksinglewindow.ui.editpassword

import android.databinding.DataBindingUtil
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.databinding.ActivityEditpasswordBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import org.koin.android.ext.android.inject

/**
 * Created by PiNGUiN on 2017-12-26.
 */
class EditPasswordActivity: BaseActivity<EditPasswordContract.Presenter>(), EditPasswordContract.View{

    override val presenter by inject<EditPasswordContract.Presenter>()

    lateinit var binding: ActivityEditpasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_editpassword)
        initialize()
    }

    override fun showErrorOldPassword(stringId: Int) {
        binding.edittextEditpasswordOldpassword.error = getString(stringId)
    }

    override fun showErrorNewPassword(stringId: Int) {
        binding.edittextEditpasswordNewpassword.error = getString(stringId)
    }

    override fun showErrorNewPasswordConfirm(stringId: Int) {
        binding.edittextEditpasswordNewpasswordconfirm.error = getString(stringId)
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarEditpassword)

        binding.apply {
            toolbarEditpassword.setNavigationOnClickListener{ finish() }

            buttonEditpasswordSavebutton.setOnClickListener {
                presenter.onPressSave(edittextEditpasswordOldpassword.text.toString(),
                        edittextEditpasswordNewpassword.text.toString(),
                        edittextEditpasswordNewpasswordconfirm.text.toString())
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}