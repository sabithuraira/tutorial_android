package com.dev.myapp.fragment_api

import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.myapp.Blank1FragmentDirections
import com.dev.myapp.MainViewModel
import com.dev.myapp.NavActivity
import com.dev.myapp.api.ApiConfig
import com.dev.myapp.databinding.FragmentTodosBinding
import com.dev.myapp.models.ResponseTodo
import com.dev.myapp.models.Todo
import retrofit2.Callback
import retrofit2.Response

class TodosFragment : Fragment() {
    private lateinit var binding: FragmentTodosBinding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var parentActivity: NavActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentActivity = requireActivity() as NavActivity

        viewModel.isLoading.observe(viewLifecycleOwner){ data -> parentActivity.setLoading(data) }
        viewModel.listTodo.observe(viewLifecycleOwner){ data -> setRvTodo(data, view) }

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvTodos.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvTodos.addItemDecoration(itemDecoration)

        binding.btnAdd.setOnClickListener {
            view.findNavController().navigate(
                TodosFragmentDirections.actionTodosFragmentToFormFragment(null)
            )
        }

        viewModel.loadDatas()
    }

    private fun setRvTodo(data: List<Todo>?, view: View){
        val todoAdapter = TodoAdapter(ArrayList(data))
        todoAdapter.setOnClickCallBack(object: TodoAdapter.onClickCallBack{
            override fun onItemClicked(data: Todo) {
                editData(view, data)
            }
        })
        binding.rvTodos.adapter = todoAdapter
    }

    private fun editData(view: View, data: Todo){
        view.findNavController().navigate(
            TodosFragmentDirections.actionTodosFragmentToFormFragment(data)
        )
    }
}