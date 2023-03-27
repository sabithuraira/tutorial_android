package com.dev.myapp.fragment_data

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.myapp.Profile
import com.dev.myapp.R
import com.dev.myapp.data.ProfileSharePreference
import com.dev.myapp.databinding.FragmentBlank1Binding
import com.dev.myapp.databinding.FragmentPreferenceBinding

class PreferenceFragment : Fragment() {
    private lateinit var binding: FragmentPreferenceBinding
    private lateinit var profile: Profile

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreferenceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profilePref = ProfileSharePreference(requireContext())
        profile = profilePref.getProfile()

        profile.name?.let {
            binding.edtName.setText(profile.name)
        }

        profile.birthday?.let {
            binding.edtBirthday.setText(profile.birthday)
        }

        binding.btnSave.setOnClickListener {
            profile.name = binding.edtName.text.toString()
            profile.birthday = binding.edtBirthday.text.toString()
            profilePref.setProfile(profile)
        }
    }
}