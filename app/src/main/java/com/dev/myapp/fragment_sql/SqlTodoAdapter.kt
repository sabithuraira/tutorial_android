package com.dev.myapp.fragment_sql

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.myapp.databinding.RowStudentBinding
import com.dev.myapp.databinding.RowTodoBinding
import com.dev.myapp.models.SqlTodo
import com.dev.myapp.models.Todo

class SqlTodoAdapter(private val listData: ArrayList<SqlTodo>): RecyclerView.Adapter<SqlTodoAdapter.DataViewHolder>() {
    private lateinit var OnClickCallBack: onClickCallBack

    fun setOnClickCallBack(data: onClickCallBack){
        this.OnClickCallBack = data
    }

    interface onClickCallBack{
        fun onItemClicked(data: SqlTodo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_student, parent, false)
//        return DataViewHolder(view)

        return DataViewHolder(RowTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val curTodo = listData[position]
        holder.binding.txtTitle.text = curTodo.title
        holder.binding.txtDescription.text = curTodo.description

//        holder.txtName.setOnClickListener {
//            Toast.makeText(holder.txtName.context, "I click txtName", Toast.LENGTH_SHORT).show()
//        }

        holder.itemView.setOnClickListener {
            OnClickCallBack.onItemClicked(listData[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listData.count()
    }

    class DataViewHolder(val binding: RowTodoBinding) : RecyclerView.ViewHolder(binding.root)
}