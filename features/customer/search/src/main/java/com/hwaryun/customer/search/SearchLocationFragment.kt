package com.hwaryun.customer.search

import android.os.Bundle
import androidx.core.view.isVisible
import com.hwaryun.core.extensions.ifNetworkError
import com.hwaryun.core.state.StateEventSubscriber
import com.hwaryun.customer.search.databinding.FragmentSearchLocationBinding
import com.hwaryun.locationapi.entity.LocationData
import com.hwaryun.navigation.FragmentConnector
import com.hwaryun.navigation.replaceFragment
import com.hwaryun.utils.BindingFragment
import org.koin.android.ext.android.inject

class SearchLocationFragment : BindingFragment<FragmentSearchLocationBinding>() {

    private val viewModel: SearchLocationViewModel by inject()

    override fun inflateBinding(): FragmentSearchLocationBinding {
        return FragmentSearchLocationBinding.inflate(layoutInflater)
    }

    override fun onCreateBinding(savedInstanceState: Bundle?) {
        viewModel.subscribeLocationManager(object : StateEventSubscriber<List<LocationData>> {
            override fun onIdle() {
                renderIdle()
            }

            override fun onLoading() {
                renderLoading()
            }

            override fun onEmpty() {
                renderEmpty()
            }

            override fun onFailure(throwable: Throwable) {
                renderFailure(throwable)
            }

            override fun onSuccess(data: List<LocationData>) {
                renderSuccess(data)
            }
        })

        binding.btnSearch.setOnClickListener {
            val name = binding.etSearch.text.toString()
            viewModel.getLocations(name)
        }

        binding.btnProfile.setOnClickListener {
            val profileFragment = FragmentConnector.Profile.profileFragment
            childFragmentManager.replaceFragment(binding.frameLayout, profileFragment)
        }
    }

    private fun renderIdle() {
        binding.progressBar.isVisible = false
    }

    private fun renderLoading() {
        binding.progressBar.isVisible = true
    }

    private fun renderEmpty() {
        binding.progressBar.isVisible = false
        binding.tvResult.text = "Kosong"
    }

    private fun renderFailure(throwable: Throwable) {
        binding.progressBar.isVisible = false

        throwable.ifNetworkError {
            binding.tvResult.text = throwable.message
        }
    }

    private fun renderSuccess(data: List<LocationData>) {
        binding.progressBar.isVisible = false
        binding.tvResult.text = data.map { location -> location.name }.toString()
    }
}