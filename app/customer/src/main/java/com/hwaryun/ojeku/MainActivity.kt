package com.hwaryun.ojeku

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hwaryun.core.view.ComponentPlaygroundActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.tv_main).setOnClickListener {
            ComponentPlaygroundActivity.launch(this)
        }
    }
}