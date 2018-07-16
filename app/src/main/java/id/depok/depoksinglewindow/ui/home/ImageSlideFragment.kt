package id.depok.depoksinglewindow.ui.home


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.depok.depoksinglewindow.GlideApp
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.databinding.FragmentImageslideBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ImageSlideFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageSlideFragment : Fragment() {

    private var imageUrl: String? = null
    private var imageResId: Int? = null
    private lateinit var binding: FragmentImageslideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            imageUrl = arguments!!.getString(ARG_IMAGE_URL)
            imageResId = arguments!!.getInt(ARG_IMAGE_RES_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_imageslide,
                    container, false)
        initialize()
        return binding.root
    }

    companion object {
        private val ARG_IMAGE_URL = "image_url"
        private val ARG_IMAGE_RES_ID = "image_res_id"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param imageUrl image URL.
         * @return A new instance of fragment ImageSlideFragment.
         */
        fun newInstance(imageUrl: String): ImageSlideFragment {
            val fragment = ImageSlideFragment()
            val args = Bundle()
            args.putString(ARG_IMAGE_URL, imageUrl)
            fragment.arguments = args
            return fragment
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param imageResId image res id.
         * @return A new instance of fragment ImageSlideFragment.
         */
        fun newInstance(imageResId: Int): ImageSlideFragment {
            val fragment = ImageSlideFragment()
            val args = Bundle()
            args.putInt(ARG_IMAGE_RES_ID, imageResId)
            fragment.arguments = args
            return fragment
        }
    }

    private fun initialize() {
        binding.apply {
            if (!imageUrl.isNullOrEmpty()) {
                GlideApp.with(this@ImageSlideFragment)
                        .load(imageUrl)
                        .into(imageviewSliderImage)
            } else if (imageResId != null) {
                imageviewSliderImage.setImageDrawable(context?.let {
                    ContextCompat.getDrawable(it,
                            imageResId!!)
                })
            }
        }
    }

}// Required empty public constructor
