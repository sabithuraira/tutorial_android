package com.dev.myapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.dev.myapp.databinding.FragmentBlank1Binding

class Blank1Fragment : Fragment() {

    private lateinit var binding: FragmentBlank1Binding
//    private lateinit var viewModel: MainViewModel
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var parentActivity: NavActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_blank1, container, false)
        binding = FragmentBlank1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        parentActivity = requireActivity() as NavActivity

        viewModel.multiply(9)
        binding.textViewmodel.text = viewModel.result.toString()

        ////OBSERVE LIVEDATA
        viewModel.isLoading.observe(viewLifecycleOwner){
            data -> parentActivity.setLoading(data)
        }

        binding.btnSharePreferenc.setOnClickListener {
            view.findNavController().navigate(
                Blank1FragmentDirections.actionBlank1FragmentToPreferenceFragment()
            )
        }

        ///
        binding.btnNext.setOnClickListener {
//            val fragment2 = Blank2Fragment()
//            val bundle2 = Bundle()
//            bundle2.putString(Blank2Fragment.EXTRA_VALUE, "My Value For Fragment 2")
//            fragment2.arguments = bundle2
//
//            val mFragmentManager = parentFragmentManager
//            mFragmentManager
//                .beginTransaction().apply {
//                    replace(R.id.fragment_container, fragment2, Blank2Fragment::class.java.simpleName)
//                    addToBackStack(null)
//                    commit()
//                }
            val myStudent = Student("Alex", "98766442")
            view.findNavController().navigate(
                Blank1FragmentDirections.actionBlank1FragmentToBlank2Fragment(myStudent)
            )
        }

        binding.btnTodo.setOnClickListener {
            view.findNavController().navigate(
                Blank1FragmentDirections.actionBlank1FragmentToTodosFragment()
            )
        }


        binding.btnSqlLite.setOnClickListener {
            view.findNavController().navigate(
                Blank1FragmentDirections.actionBlank1FragmentToSqlTodoFragment()
            )
        }

        binding.btnDialog.setOnClickListener {
            val mDialogFragment = MyDialogFragment()
            val mFragmentManager = childFragmentManager
            mDialogFragment.show(mFragmentManager, MyDialogFragment::class.java.simpleName)
        }
    }

    var dialogListener: MyDialogFragment.DialogListener = object: MyDialogFragment.DialogListener{
        override fun onSubmit(text: String) {
            Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
        }
    }
}
