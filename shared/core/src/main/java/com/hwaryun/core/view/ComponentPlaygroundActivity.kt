package com.hwaryun.core.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hwaryun.core.databinding.ActivityComponentPlaygroundBinding
import com.hwaryun.core.view.component.TransportCardView

class ComponentPlaygroundActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComponentPlaygroundBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComponentPlaygroundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            transportCar.type = TransportCardView.Type.TAXI
        }
    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(
                Intent(context, ComponentPlaygroundActivity::class.java)
            )
        }
    }
}