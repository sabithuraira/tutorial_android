package com.dev.myapp.fragment_api

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDelete.visibility = View.GONE

        data = FormFragmentArgs.fromBundle(arguments as Bundle).data

        data?.let {
            binding.edtTitle.setText(data!!.title)
            binding.edtDescription.setText(data!!.description)
            binding.btnDelete.visibility = View.VISIBLE
            binding.btnDelete.setOnClickListener {
                //fungsi delete
                deleteData(view)
            }
        }

        binding.btnSave.setOnClickListener {
            //fungsi save
            saveData(view)
        }
    }

    private fun saveData(view: View){
        val jsonObject = JSONObject()
        jsonObject.put("title", binding.edtTitle.text)
        jsonObject.put("description", binding.edtDescription.text)

        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        if(data==null){
            val client = ApiConfig.getApiService().storeTodo(requestBody)

            client.enqueue(object: Callback<ResponseStoreTodo> {
                override fun onFailure(call: Call<ResponseStoreTodo>, t: Throwable) {
                    Log.e("error", t.message!!.toString())
                }

                override fun onResponse(
                    call: Call<ResponseStoreTodo>,
                    response: Response<ResponseStoreTodo>
                ) {
                    view.findNavController().navigate(FormFragmentDirections.actionFormFragmentToTodosFragment())
                }
            })
        }
        else{
            val client = ApiConfig.getApiService().updateTodo(data!!.encId!!, requestBody)

            client.enqueue(object: Callback<ResponseStoreTodo> {
                override fun onFailure(call: Call<ResponseStoreTodo>, t: Throwable) {
                    Log.e("error", t.message!!.toString())
                }

                override fun onResponse(
                    call: Call<ResponseStoreTodo>,
                    response: Response<ResponseStoreTodo>
                ) {
                    view.findNavController().navigate(FormFragmentDirections.actionFormFragmentToTodosFragment())
                }
            })
        }
    }

    private fun deleteData(view: View){
        val client = ApiConfig.getApiService().deleteToto(data!!.encId!!)

        client.enqueue(object: Callback<ResponseDeleteTodo> {
            override fun onFailure(call: Call<ResponseDeleteTodo>, t: Throwable) {
                Log.e("error", t.message!!.toString())
            }

            override fun onResponse(
                call: Call<ResponseDeleteTodo>,
                response: Response<ResponseDeleteTodo>
            ) {
                view.findNavController().navigate(FormFragmentDirections.actionFormFragmentToTodosFragment())
            }
        })
    }
}