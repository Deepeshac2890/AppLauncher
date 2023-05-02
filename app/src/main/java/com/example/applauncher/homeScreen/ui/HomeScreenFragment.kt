package com.example.applauncher.homeScreen.ui

import android.Manifest
import android.app.WallpaperManager
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.applauncher.databinding.FragmentHomeScreenBinding


/**
 * Home Screen fragment which acts as an entry point for app drawer.
 */
class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val wallpaperManager = WallpaperManager.getInstance(requireContext())
        val wallpaperDrawable = if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Do not have the permission can add a here to get the permission in future
            null
        } else {
            wallpaperManager.drawable
        }

        if (wallpaperDrawable != null) {
            binding.container.background = wallpaperDrawable
        }

        binding.iconDrawer.setOnClickListener {
            findNavController().navigate(HomeScreenFragmentDirections.navigateToDrawer())
        }
    }
}