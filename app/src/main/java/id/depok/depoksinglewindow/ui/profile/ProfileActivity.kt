package id.depok.depoksinglewindow.ui.profile

import android.databinding.DataBindingUtil
import android.os.Bundle
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.User
import id.depok.depoksinglewindow.databinding.ActivityProfileBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import org.koin.android.ext.android.inject

class ProfileActivity : BaseActivity<ProfileContract.Presenter>(), ProfileContract.View {

    override val presenter by inject<ProfileContract.Presenter>()
    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)

        initialize()
    }

    override fun loadProfile(user: User?) {
        if (user != null) {
            binding.apply {
                textviewProfileFullname.text = user.name
                textviewProfileAddress.text = user.address
                textviewProfileEmail.text = user.email
                textviewProfileGender.text = if (user.gender == 0 )
                                                getString(R.string.all_female)
                                              else
                                                getString(R.string.all_male)
                textviewProfilePhonenumber.text = user.phoneNumber
            }
        }
    }

    private fun initialize() {

        binding.apply {
            setSupportActionBar(toolbarProfile)
            toolbarProfile.setNavigationOnClickListener{ finish() }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.start()
    }
}
