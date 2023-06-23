package org.android.go.sopt.presentation.main.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import org.android.go.sopt.databinding.FragmentGalleryBinding
import org.android.go.sopt.presentation.viewmodel.GalleryViewModel
import org.android.go.sopt.util.ContentUriRequestBody

class GalleryFragment : Fragment() {
    private val viewModel by viewModels<GalleryViewModel>()
    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding) { "binding is null" }
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        binding.ivGalleryImage.load(uri)
        uri?.let {
            viewModel.setRequestBody(ContentUriRequestBody(requireContext(), it))
        }
    }

    private val locatePermissionlauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(requireContext(), "허락 받음", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "허락 못받음", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        locatePermissionlauncher.launch("android.permission.ACCESS_FINE_LOCATION")

        setOnClickListener()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setOnClickListener() {

        val pref = context?.getSharedPreferences("autoLogIn", AppCompatActivity.MODE_PRIVATE)
        val id = pref?.getString("id", "").toString()

        with(binding) {
            btnGallery.setOnClickListener {
                viewModel.postMusicData(id, etGllaeryTitle.text.toString(), etGllaerySinger.text.toString())
            }
            ivGalleryImage.setOnClickListener {
                launcher.launch("image/*")
            }
        }
    }
}