package com.itdev.teammanager.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.itdev.teammanager.R
import com.itdev.teammanager.databinding.FragmentGalleryBinding
import com.itdev.teammanager.viewmodels.GalleryViewModel

class GalleryFragment : Fragment() {
    private lateinit var binding: FragmentGalleryBinding

    private val args: GalleryFragmentArgs by navArgs()

    companion object {
        fun newInstance() = GalleryFragment()
    }

    private lateinit var viewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGalleryBinding.inflate(layoutInflater, container, false)

        binding.galleryTitle.text = args.memberName

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}