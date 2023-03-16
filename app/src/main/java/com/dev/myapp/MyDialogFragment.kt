package com.dev.myapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dev.myapp.databinding.FragmentMyDialogBinding

class MyDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentMyDialogBinding
    private var dialogListener: DialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_my_dialog, container, false)
        binding = FragmentMyDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmit.setOnClickListener {
            dialogListener?.onSubmit(binding.edtName.text.toString())
            dialog?.dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment
        if(fragment is Blank1Fragment){
            this.dialogListener = fragment.dialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.dialogListener = null
    }

    interface DialogListener{
        fun onSubmit(text: String)
    }
}