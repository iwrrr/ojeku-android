package com.hwaryun.locationapi.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.hwaryun.core.extensions.ifNetworkError
import com.hwaryun.core.state.StateEventSubscriber
import com.hwaryun.locationapi.databinding.ActivitySearchLocationBinding
import com.hwaryun.locationapi.entity.LocationData
import com.hwaryun.utils.BindingActivity
import org.koin.android.ext.android.inject

class SearchLocationActivity : BindingActivity<ActivitySearchLocationBinding>() {

    private val viewModel: SearchLocationViewModel by inject()

    override fun inflateBinding(): ActivitySearchLocationBinding {
        return ActivitySearchLocationBinding.inflate(layoutInflater)
    }

    override fun onCreateBinding(savedInstanceState: Bundle?) {

        //        viewModel.locationResult.observe(this) {
        //            println(it)
        //
        //            binding.progressBar.isVisible = it is StateEvent.Loading
        //            when (it) {
        //                is StateEvent.Idle -> renderIdle()
        //                is StateEvent.Loading -> renderLoading()
        //                is StateEvent.Empty -> renderEmpty()
        //                is StateEvent.Failure -> renderFailure(it.exception)
        //                is StateEvent.Success -> renderSuccess(it.data)
        //            }
        //        }

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
        //        throwable.ifStateEmpty {
        //            binding.tvResult.text = "Kosong"
        //        }

        binding.progressBar.isVisible = false

        throwable.ifNetworkError {
            binding.tvResult.text = throwable.message
        }
    }

    private fun renderSuccess(data: List<LocationData>) {
        binding.progressBar.isVisible = false
        binding.tvResult.text = data.map { location -> location.name }.toString()
    }

    companion object {
        @JvmStatic
        fun launch(context: Context) {
            context.startActivity(
                Intent(context, SearchLocationActivity::class.java)
            )
        }
    }
}