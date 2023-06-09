package org.android.go.sopt.presentation.main.gallery

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
//    val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
//  랴     binding.ivGalleryImage.load(uri)
//    }

    //사진 여러장 첨부
    private val launcher =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { imageUriList: List<Uri> ->
            with(binding) {
                when (imageUriList.size) {
                    0 -> {
                        Toast.makeText(requireContext(), "이미지를 선택하지 않았습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }

                    1 -> {
                        viewModel.setRequestBody(
                            ContentUriRequestBody(
                                requireContext(),
                                imageUriList[0]
                            )
                        )
                        ivGalleryImage1.load(imageUriList[0])
                    }

                    2 -> {
                        viewModel.setRequestBody(
                            ContentUriRequestBody(
                                requireContext(),
                                imageUriList[0]
                            )
                        )
                        viewModel.setRequestBody(
                            ContentUriRequestBody(
                                requireContext(),
                                imageUriList[1]
                            )
                        )
                        ivGalleryImage1.load(imageUriList[0])
                        ivGalleryImage2.load(imageUriList[1])
                    }

                    3 -> {
                        viewModel.setRequestBody(
                            ContentUriRequestBody(
                                requireContext(),
                                imageUriList[0]
                            )
                        )
                        viewModel.setRequestBody(
                            ContentUriRequestBody(
                                requireContext(),
                                imageUriList[1]
                            )
                        )
                        viewModel.setRequestBody(
                            ContentUriRequestBody(
                                requireContext(),
                                imageUriList[2]
                            )
                        )

                        ivGalleryImage1.load(imageUriList[0])
                        ivGalleryImage2.load(imageUriList[1])
                        ivGalleryImage3.load(imageUriList[2])
                    }

                    else -> {
                        Toast.makeText(requireContext(), "3개까지의 이미지만 선택해주세요.", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
                if (imageUriList.size != 0) {
                    viewModel.uploadProfileImage()
                }

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
        binding.btnGallery.setOnClickListener {
            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}