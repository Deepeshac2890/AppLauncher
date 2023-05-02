package com.example.applauncher.screens.homeScreen.ui

import android.Manifest
import android.app.WallpaperManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.applauncher.databinding.FragmentHomeScreenBinding
import com.example.applauncher.show


/**
 * Home Screen fragment which acts as an entry point for app drawer.
 */
class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var wallpaperManager: WallpaperManager
    private var checkPermissionExplicitly = false

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            toggleNoPermissionsUI(false)
            setWallpaperDrawable()
        } else {
            toggleNoPermissionsUI(true)
        }
    }

    private fun toggleNoPermissionsUI(show: Boolean) {
        binding.grantPermissionButton.show(show)
        binding.noPermissionText.show(show)
    }

    override fun onResume() {
        super.onResume()
        if (checkPermissionExplicitly) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Got the permission
                toggleNoPermissionsUI(false)
                setWallpaperDrawable()
            } else {
                toggleNoPermissionsUI(true)
            }
        }
        checkPermissionExplicitly = false
    }

    /**
     * Set the wallpaper which is set in the default launcher of device.
     */
    private fun setWallpaperDrawable() {
        val wallpaperDrawable = if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            null
        } else {
            wallpaperManager.drawable
        }

        if (wallpaperDrawable != null) {
            binding.container.background = wallpaperDrawable
        }
    }

    /**
     * To set the listeners.
     */
    private fun initListener() {
        binding.grantPermissionButton.setOnClickListener {
            checkPermissionExplicitly = true
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri: Uri = Uri.fromParts("package", requireActivity().application.packageName, null)
            intent.data = uri
            startActivity(intent)
        }

        binding.iconDrawer.setOnClickListener {
            findNavController().navigate(HomeScreenFragmentDirections.navigateToDrawer())
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
        toggleNoPermissionsUI(false)
        initListener()
        wallpaperManager = WallpaperManager.getInstance(requireContext())
        setWallpaperDrawable()
    }
}