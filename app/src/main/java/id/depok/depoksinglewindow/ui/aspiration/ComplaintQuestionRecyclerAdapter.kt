package id.depok.depoksinglewindow.ui.aspiration

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.ComplaintAndQuestion
import id.depok.depoksinglewindow.databinding.ItemComplaintQuestionBinding
import id.depok.depoksinglewindow.util.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by PiNGUiN on 2018-01-21.
 */
class ComplaintQuestionRecyclerAdapter(val context: Context,
                                       val data: List<ComplaintAndQuestion>):
        RecyclerView.Adapter<ComplaintQuestionRecyclerAdapter.ComplaintQuestionViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplaintQuestionViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_complaint_question, parent,
                false)
        return ComplaintQuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComplaintQuestionViewHolder, position: Int) {
        holder.binding?.apply {
            val item = data[position]
            textviewComplaintquestionitemCategoryname.text = item.categoryName
            //textviewComplaintquestionitemStatuslabel.text = item.status
            val indonesia = Locale("id", "ID", "ID")
            val simpleDateFormat = SimpleDateFormat(DateFormat.FULL_DAY_DATE_MONTH_YEAR, indonesia)
            textviewComplaintquestionitemCreateddate.text = simpleDateFormat.format(item.createdAt)
            textviewComplaintquestionitemHandledby.visibility = View.GONE
            when {
                item.image.isNotEmpty() -> {
                    imageviewComplaintquestionitemImagethumbnail.visibility = View.VISIBLE
                    if (item.file.isEmpty())
                        imageviewComplaintquestionitemFileicon.visibility = View.INVISIBLE
                    Glide.with(context)
                            .load(item.image)
                            .into(imageviewComplaintquestionitemImagethumbnail)
                }
                item.image.isNotEmpty() -> imageviewComplaintquestionitemImagethumbnail.visibility = View.INVISIBLE
                else -> imageviewComplaintquestionitemImagethumbnail.visibility = View.GONE
            }

            when {
                item.file.isNotEmpty() -> imageviewComplaintquestionitemFileicon.visibility = View.VISIBLE
                item.image.isNotEmpty() -> imageviewComplaintquestionitemFileicon.visibility = View.INVISIBLE
                else -> imageviewComplaintquestionitemFileicon.visibility = View.GONE
            }
            textviewComplaintquestionitemMessage.text = item.message
        }
    }

    inner class ComplaintQuestionViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemComplaintQuestionBinding? = DataBindingUtil.bind(itemView!!)
    }
}