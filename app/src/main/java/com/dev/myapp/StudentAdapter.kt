package com.dev.myapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dev.myapp.databinding.RowStudentBinding

class StudentAdapter(private val listData: ArrayList<Student>): RecyclerView.Adapter<StudentAdapter.DataViewHolder>() {
    private lateinit var OnClickCallBack: onClickCallBack

    fun setOnClickCallBack(data: onClickCallBack){
        this.OnClickCallBack = data
    }

    interface onClickCallBack{
        fun onItemClicked(data: Student)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_student, parent, false)
//        return DataViewHolder(view)

        return DataViewHolder(RowStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val (name, nim) = listData[position]
        holder.binding.txtName.text = name
        holder.binding.txtNim.text = nim

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

//    class DataViewHolder(item: View) : RecyclerView.ViewHolder(item){
//        val txtName: TextView = item.findViewById(R.id.txt_name)
//        val txtNim: TextView = item.findViewById(R.id.txt_nim)
//    }

    class DataViewHolder(val binding: RowStudentBinding) : RecyclerView.ViewHolder(binding.root)
}