package com.example.applauncher.screens.homeScreen.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.applauncher.databinding.FragmentHomeScreenBinding


/**
 * Home Screen fragment which acts as an entry point for app drawer.
 */
class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var wallpaperManager: WallpaperManager

    @SuppressLint("MissingPermission")
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            wallpaperManager = WallpaperManager.getInstance(requireContext())
            val wallpaperDrawable = if (ActivityCompat.checkSelfPermission(
                    activity as Context,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                null
            } else {
                wallpaperManager.drawable
            }

            if (wallpaperDrawable != null) {
                binding.container.background = wallpaperDrawable
            }
        } else {
            Toast.makeText(requireContext(), "Storage Permission Denied", Toast.LENGTH_SHORT)
                .show()
        }
    }


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
        wallpaperManager = WallpaperManager.getInstance(requireContext())
        val wallpaperDrawable = if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
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