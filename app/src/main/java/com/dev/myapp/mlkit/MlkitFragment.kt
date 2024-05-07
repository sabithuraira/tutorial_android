package com.dev.myapp.mlkit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dev.myapp.databinding.FragmentMlkitBinding
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class MlkitFragment : Fragment() {
    private lateinit var binding: FragmentMlkitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentMlkitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.translateButton.setOnClickListener {
            translate(binding.etInput.text.toString())
        }
    }

    private fun translate(myText: String?){
        binding.progressIndicator.visibility = View.VISIBLE

        val opts = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.INDONESIAN)
            .build()

        val translator = Translation.getClient(opts)
        val conditions = DownloadConditions.Builder().build()

        translator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                translator.translate(myText.toString())
                    .addOnSuccessListener { result ->
                        binding.translatedText.text = result
                        translator.close()
                        binding.progressIndicator.visibility = View.GONE
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT)
                        print(e.stackTrace)
                        translator.close()
                        binding.progressIndicator.visibility = View.GONE
                    }
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT)
                binding.progressIndicator.visibility = View.GONE
            }
    }

}