package com.dev.myapp.fragment_sql

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.myapp.NavActivity
import com.dev.myapp.R
import com.dev.myapp.databinding.FragmentSqlTodoBinding
import com.dev.myapp.databinding.FragmentTodosBinding
import com.dev.myapp.fragment_api.TodoAdapter
import com.dev.myapp.fragment_api.TodosFragmentDirections
import com.dev.myapp.models.SqlTodo
import com.dev.myapp.models.Todo
import com.dev.myapp.sqlite.DbTodo

class SqlTodoFragment : Fragment() {
    private lateinit var binding: FragmentSqlTodoBinding
    private lateinit var dbTodo: DbTodo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSqlTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbTodo = DbTodo(requireContext())
        dbTodo.open()

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvTodos.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvTodos.addItemDecoration(itemDecoration)

        binding.btnAdd.setOnClickListener {
            view.findNavController().navigate(
                SqlTodoFragmentDirections.actionSqlTodoFragmentToSqlTodoFormFragment(null)
            )
        }

        loadDatas(view)
    }

    fun loadDatas(view: View){
        val datas = dbTodo.findAll()

        val todoAdapter = SqlTodoAdapter(ArrayList(datas))
        todoAdapter.setOnClickCallBack(object: SqlTodoAdapter.onClickCallBack{
            override fun onItemClicked(data: SqlTodo) {
                editData(view, data)
            }
        })
        binding.rvTodos.adapter = todoAdapter
    }

    private fun editData(view: View, data: SqlTodo){
        view.findNavController().navigate(
            SqlTodoFragmentDirections.actionSqlTodoFragmentToSqlTodoFormFragment(data)
        )
    }
}