package id.depok.depoksinglewindow.ui.onboarding


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.databinding.FragmentOnboardingslidepageBinding


/**
 * A simple [Fragment] subclass.
 * Use the [OnBoardingSlidePageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OnBoardingSlidePageFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingslidepageBinding

    private var mTitle: String? = null
    private var mBody: String? = null
    private var mImageId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mTitle = arguments!!.getString(ARG_TITLE)
            mBody = arguments!!.getString(ARG_BODY)
            mImageId = arguments!!.getInt(ARG_IMAGE_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_onboardingslidepage, container, false)

        initialize()

        return binding.root
    }

    companion object {
        // the fragment initialization parameters
        private val ARG_TITLE = "title"
        private val ARG_BODY = "text"
        private val ARG_IMAGE_ID = "image_id"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param title Title text
         * @param body Body text
         * @param imageId Id of the image
         * @return A new instance of fragment OnBoardingSlidePageFragment.
         */
        fun newInstance(title: String, body: String, imageId: Int): OnBoardingSlidePageFragment {
            val fragment = OnBoardingSlidePageFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            args.putString(ARG_BODY, body)
            args.putInt(ARG_IMAGE_ID, imageId)
            fragment.arguments = args
            return fragment
        }
    }

    fun initialize() {
        binding.apply {
            mTitle?.let { textviewOnboardingslidepageTitletext.text = mTitle }
            mBody?.let { textviewOnboardingslidepageBodytext.text = mBody }
            mImageId?.let { imageviewOnboardingslidpagePageimage.setImageResource(it) }
        }
    }

}// Required empty public constructor
