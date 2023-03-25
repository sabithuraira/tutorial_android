package com.dev.myapp.fragment_api

import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.myapp.Blank1FragmentDirections
import com.dev.myapp.api.ApiConfig
import com.dev.myapp.databinding.FragmentTodosBinding
import com.dev.myapp.models.ResponseTodo
import com.dev.myapp.models.Todo
import retrofit2.Callback
import retrofit2.Response

class TodosFragment : Fragment() {
    private lateinit var binding: FragmentTodosBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvTodos.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvTodos.addItemDecoration(itemDecoration)

        binding.btnAdd.setOnClickListener {
            view.findNavController().navigate(
                TodosFragmentDirections.actionTodosFragmentToFormFragment(null)
            )
        }

        loadDatas(view)

    }

    private fun loadDatas(view: View){
        val client = ApiConfig.getApiService().getTodos()

        client.enqueue(object: Callback<ResponseTodo>{
            override fun onFailure(call: retrofit2.Call<ResponseTodo>, t: Throwable) {
                Log.e("error", t.message!!)
            }

            override fun onResponse(
                call: retrofit2.Call<ResponseTodo>,
                response: Response<ResponseTodo>
            ) {
                if(response.body()===null){
                    Log.e("error", "Your data API is empty")
                }
                else{
                    val todos_data = response.body()?.result?.data
                    val todoAdapter = TodoAdapter(ArrayList(todos_data))
                    todoAdapter.setOnClickCallBack(object: TodoAdapter.onClickCallBack{
                        override fun onItemClicked(data: Todo) {
                            editData(view, data)
                        }
                    })
                    binding.rvTodos.adapter = todoAdapter
                }
            }
        })
    }

    private fun editData(view: View, data: Todo){
        view.findNavController().navigate(
            TodosFragmentDirections.actionTodosFragmentToFormFragment(data)
        )
    }
}