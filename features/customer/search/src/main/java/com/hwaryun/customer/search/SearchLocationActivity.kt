package com.hwaryun.customer.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.hwaryun.customer.search.databinding.ActivitySearchLocationBinding
import com.hwaryun.utils.BindingActivity
import org.koin.android.ext.android.inject

class SearchLocationActivity : BindingActivity<ActivitySearchLocationBinding>() {

    private val viewModel: SearchLocationViewModel by inject()

    override fun inflateBinding(): ActivitySearchLocationBinding {
        return ActivitySearchLocationBinding.inflate(layoutInflater)
    }

    override fun onCreateBinding(savedInstanceState: Bundle?) {

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