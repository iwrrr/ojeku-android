package com.hwaryun.customer.profile

import android.os.Bundle
import com.hwaryun.customer.profile.databinding.FragmentProfileBinding
import com.hwaryun.utils.BindingFragment

class ProfileFragment : BindingFragment<FragmentProfileBinding>() {

    override fun inflateBinding(): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun onCreateBinding(savedInstanceState: Bundle?) {

    }
}