package com.dev.myapp.fragment_sql

import android.content.ContentValues
import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.Person.fromBundle
import androidx.navigation.findNavController
import com.dev.myapp.NavActivity
import com.dev.myapp.R
import com.dev.myapp.databinding.FragmentFormBinding
import com.dev.myapp.databinding.FragmentSqlTodoFormBinding
import com.dev.myapp.fragment_api.FormFragmentArgs
import com.dev.myapp.fragment_api.FormFragmentDirections
import com.dev.myapp.models.SqlTodo
import com.dev.myapp.models.Todo
import com.dev.myapp.sqlite.DbContract.TodoTable.Companion.DESCRIPTION
import com.dev.myapp.sqlite.DbContract.TodoTable.Companion.TITLE
import com.dev.myapp.sqlite.DbTodo


class SqlTodoFormFragment : Fragment() {
    private lateinit var binding: FragmentSqlTodoFormBinding
    private lateinit var dbtodo: DbTodo
    private var data: SqlTodo? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSqlTodoFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbtodo = DbTodo(requireContext())
        dbtodo.open()

        binding.btnDelete.visibility = View.GONE

        data = SqlTodoFormFragmentArgs.fromBundle(arguments as Bundle).data

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
            saveData(view, binding.edtTitle.text.toString(), binding.edtDescription.text.toString())
        }
    }

    fun deleteData(view: View){
        val result = dbtodo.delete(data!!.id.toString())
        if(result>0){
            view.findNavController().navigate(
                SqlTodoFormFragmentDirections.actionSqlTodoFormFragmentToSqlTodoFragment()
            )
        }
        else{
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveData(view: View, title: String, description: String){
        val values = ContentValues()
        values.put(TITLE, title)
        values.put(DESCRIPTION, description)

        if(data==null){
            //CREATE
            val result = dbtodo.insert(values)
            if(result>0){
                view.findNavController().navigate(
                    SqlTodoFormFragmentDirections.actionSqlTodoFormFragmentToSqlTodoFragment()
                )
            }
            else{
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            //UPDATE
            val result = dbtodo.update(data!!.id.toString(), values)
            if(result>0){
                view.findNavController().navigate(
                    SqlTodoFormFragmentDirections.actionSqlTodoFormFragmentToSqlTodoFragment()
                )
            }
            else{
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setFinishAction(view: View, data: Boolean){
        if(data){
            view.findNavController().navigate(FormFragmentDirections.actionFormFragmentToTodosFragment())
        }
    }
}