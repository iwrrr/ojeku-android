package com.hwaryun.ojeku

import android.location.Location
import android.os.Bundle
import com.hwaryun.core.extensions.attachFragment
import com.hwaryun.ojeku.databinding.ActivityMainBinding
import com.hwaryun.utils.BindingActivity
import com.hwaryun.utils.listener.findFragmentListener

class MainActivity : BindingActivity<ActivityMainBinding>(), MainActivityListener {

    private lateinit var homeTag: String

    override fun inflateBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreateBinding(savedInstanceState: Bundle?) {
        homeTag = attachFragment(binding.mainFrame, HomeFragment::class)
    }

    override fun onLocationResult(data: Location) {
        val instance = findFragmentListener<HomeFragmentListener>(homeTag)
        instance?.onMessageFromActivity("AOWKOAKWOKWOAKWOKAOW")
    }
}