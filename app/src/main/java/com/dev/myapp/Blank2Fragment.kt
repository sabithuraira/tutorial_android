package com.dev.myapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.dev.myapp.databinding.FragmentBlank1Binding
import com.dev.myapp.databinding.FragmentBlank2Binding

class Blank2Fragment : Fragment() {
    companion object{
        var EXTRA_VALUE = "extra_value"
    }

    private lateinit var binding: FragmentBlank2Binding
//    private lateinit var viewModel: MainViewModel
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_blank2, container, false)
        binding = FragmentBlank2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.textViewmodel.text = viewModel.result.toString()

//        if(arguments!=null){
//            val label = arguments?.getString(EXTRA_VALUE)
//            binding.myText.text = label
//        }

        val myStudent = Blank2FragmentArgs.fromBundle(arguments as Bundle).myStudent
        binding.myText.text = myStudent.name

        binding.btnNext.setOnClickListener {
//            val fragment1 = Blank1Fragment()
//            val mFragmentManager = parentFragmentManager
//            mFragmentManager
//                .beginTransaction().apply {
//                    replace(R.id.fragment_container, fragment1, Blank1Fragment::class.java.simpleName)
//                    addToBackStack(null)
//                    commit()
//                }

            view.findNavController().navigate(
                Blank2FragmentDirections.actionBlank2FragmentToBlank1Fragment()
            )
        }
    }
}