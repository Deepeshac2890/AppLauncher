package com.example.applauncher.screens.appDrawer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applauncher.R
import com.example.applauncher.screens.appDrawer.adapter.AppsDrawerAdapter
import com.example.applauncher.screens.appDrawer.viewmodel.AppDrawersViewModel
import com.example.applauncher.data.Repository
import com.example.applauncher.data.network.ApiClient
import com.example.applauncher.data.network.ScreenState
import com.example.applauncher.databinding.FragmentAppDrawerBinding
import com.example.applauncher.model.AppInfo
import com.example.applauncher.utils.extensions.show

/**
 * The app drawer fragment which will show the drawer containing the apps.
 */
class AppDrawerFragment : Fragment() {

    private lateinit var binding: FragmentAppDrawerBinding
    private var adapter: AppsDrawerAdapter? = null
    private lateinit var viewModel: AppDrawersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppDrawerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            AppDrawersViewModel(requireActivity().application, Repository(ApiClient.apiService))
        viewModel.setUpApps()
    }

    override fun onStart() {
        super.onStart()
        viewModel.appsListData.observe(viewLifecycleOwner) { screenState ->
            if (screenState is ScreenState.Success) {

                screenState.data?.let { data ->
                    binding.appDrawerRecylerView.show()
                    binding.otherStateText.show(false)
                    initRecyclerView(data)
                }
            } else if (screenState is ScreenState.Error) {
                // Show error View
                binding.appDrawerRecylerView.show(false)
                binding.otherStateText.show()
                binding.otherStateText.text =
                    requireContext().getString(R.string.something_bad_happened)
            } else {
                // Loading State
                binding.otherStateText.show()
                binding.appDrawerRecylerView.show(false)
                binding.otherStateText.text = requireContext().getString(R.string.loading)
            }
        }
    }

    private fun initRecyclerView(data: List<AppInfo>) {
        adapter = AppsDrawerAdapter(requireContext(), data)
        val layoutManager = LinearLayoutManager(requireContext());
        binding.appDrawerRecylerView.layoutManager = layoutManager;
        binding.appDrawerRecylerView.adapter = adapter;
    }
}