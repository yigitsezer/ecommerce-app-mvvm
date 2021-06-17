package com.yigitsezer.ecommerceapp.ui.success_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yigitsezer.ecommerceapp.databinding.FragmentMainBinding
import com.yigitsezer.ecommerceapp.databinding.FragmentSuccessDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuccessDialogFragment: DialogFragment() {
    private var _binding: FragmentSuccessDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuccessDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGoBack.setOnClickListener {
            findNavController().popBackStack()
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}