package id.depok.depoksinglewindow.ui.complaint

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.ComplaintAndQuestion
import id.depok.depoksinglewindow.databinding.ActivityComplaintlistBinding
import id.depok.depoksinglewindow.ui.BaseActivity
import org.koin.android.ext.android.inject

/**
 * Created by PiNGUiN on 2018-01-20.
 */
private const val RC_ADD_COMPLAINT = 3
class ComplaintListActivity: BaseActivity<ComplaintListContract.Presenter>(),
        ComplaintListContract.View {

    override val presenter by inject<ComplaintListContract.Presenter>()

    private  lateinit var binding: ActivityComplaintlistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_complaintlist)

        initialize()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == RC_ADD_COMPLAINT) {
                presenter.onRefresh()
            }
        }
    }

    override fun showDataLoading() {
        binding.textviewComplainlistPlaceholdermessage.visibility = View.GONE
        binding.recyclerviewComplaintlistComplaintrecycler.visibility = View.GONE
        binding.preogressbarComplaintlistLoadingprogress.visibility = View.VISIBLE
        binding.swiperefreshComplaintlist.isRefreshing = true
    }

    override fun hideDataLoading() {
        binding.preogressbarComplaintlistLoadingprogress.visibility = View.GONE
        binding.swiperefreshComplaintlist.isRefreshing = false
    }

    override fun showPlaceholderMessage(stringId: Int) {
        binding.textviewComplainlistPlaceholdermessage.text = getString(stringId)
        binding.textviewComplainlistPlaceholdermessage.visibility = View.VISIBLE
    }

    override fun showPlaceholderMessage(message: String) {
        binding.textviewComplainlistPlaceholdermessage.text = message
        binding.textviewComplainlistPlaceholdermessage.visibility = View.VISIBLE
    }

    override fun loadData(data: List<ComplaintAndQuestion>) {
        if (data.isEmpty()) {
            binding.textviewComplainlistPlaceholdermessage.visibility = View.VISIBLE
            binding.textviewComplainlistPlaceholdermessage.text = getString(R.string.complaintpage_nodata)
        } else {
            binding.textviewComplainlistPlaceholdermessage.visibility = View.GONE
            binding.recyclerviewComplaintlistComplaintrecycler.visibility = View.VISIBLE
            binding.recyclerviewComplaintlistComplaintrecycler.adapter =
                    ComplaintQuestionRecyclerAdapter(this, data)
        }
    }

    override fun showCreatePage(isComplaint: Boolean) {
        val intent = Intent(this, ComplaintPageActivity::class.java)
        intent.putExtra(ARG_IS_COMPLAINT, isComplaint)
        startActivityForResult(intent, RC_ADD_COMPLAINT)
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbarComplaintlist)

        val isComplaint = intent.extras.getBoolean(ARG_IS_COMPLAINT)

        binding.apply {
            toolbarComplaintlist.setNavigationOnClickListener{ finish() }

            if (!isComplaint) {
                textviewComplaintlistCreatebuttonlabel.text = getString(R.string.questionandaspiration_createnewquestion)
            } else {
                textviewComplaintlistCreatebuttonlabel.text = getString(R.string.complaintpage_createnewcomplaint)
            }

            swiperefreshComplaintlist.setOnRefreshListener { presenter.onRefresh() }
            buttonComplaintlistCreatebuttonoverlay.setOnClickListener { presenter.onPressCreateButton() }

            recyclerviewComplaintlistComplaintrecycler.layoutManager = LinearLayoutManager(
                    this@ComplaintListActivity, LinearLayoutManager.VERTICAL, false)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        presenter.view = this
        presenter.isComplaint = isComplaint
        presenter.start()
    }
}