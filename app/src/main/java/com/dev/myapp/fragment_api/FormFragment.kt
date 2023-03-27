package com.dev.myapp.fragment_api

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.dev.myapp.MainViewModel
import com.dev.myapp.NavActivity
import com.dev.myapp.api.ApiConfig
import com.dev.myapp.databinding.FragmentFormBinding
import com.dev.myapp.models.ResponseDeleteTodo
import com.dev.myapp.models.ResponseStoreTodo
import com.dev.myapp.models.ResponseTodo
import com.dev.myapp.models.Todo
import com.google.gson.JsonObject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormFragment : Fragment() {
    private lateinit var binding: FragmentFormBinding
    private var data: Todo? = null
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var parentActivity: NavActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentActivity = requireActivity() as NavActivity

        viewModel.isLoading.observe(viewLifecycleOwner){ data -> parentActivity.setLoading(data) }
        viewModel.isFinish.observe(viewLifecycleOwner){ data -> setFinishAction(view, data) }

        binding.btnDelete.visibility = View.GONE

        data = FormFragmentArgs.fromBundle(arguments as Bundle).data
        viewModel.setTargetTodo(data)

        data?.let {
            binding.edtTitle.setText(data!!.title)
            binding.edtDescription.setText(data!!.description)
            binding.btnDelete.visibility = View.VISIBLE
            binding.btnDelete.setOnClickListener {
                //fungsi delete
                viewModel.deleteData()
            }
        }

        binding.btnSave.setOnClickListener {
            //fungsi save
            viewModel.saveData(binding.edtTitle.text.toString(), binding.edtDescription.text.toString())
        }
    }

    private fun setFinishAction(view: View, data: Boolean){
        if(data){
            view.findNavController().navigate(FormFragmentDirections.actionFormFragmentToTodosFragment())
        }
    }
}