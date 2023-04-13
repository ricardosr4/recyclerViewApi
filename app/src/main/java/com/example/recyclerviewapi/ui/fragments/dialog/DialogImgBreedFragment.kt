package com.example.recyclerviewapi.ui.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.recyclerviewapi.R
import com.example.recyclerviewapi.databinding.FragmentDialogImgBreedBinding
import com.squareup.picasso.Picasso

private const val IMG_BREED = "IMG_BREED"

class DialogImgBreedFragment : DialogFragment() {
    private lateinit var binding: FragmentDialogImgBreedBinding
    private var breedimg: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // todo aqui se leen los argumentos o parametros que se envian desde el activity
        arguments?.let {
            breedimg = it.getString(IMG_BREED)
        }
        // todo aqui  el dialog se vea pantalla completa
        val style = STYLE_NO_FRAME
        val theme = R.style.FullScreenDialogStyle
        setStyle(style, theme)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDialogImgBreedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // todo asi se cierra un dialogfragment
        binding.ivClose.setOnClickListener { dismiss() }
        // todo asi se cierra un dialogfragment
        Picasso.get().load(breedimg).into(binding.imgBreed)
    }

    companion object {

      //  todo aqui se guardan los argumentos o parametros que se envian desde el activity
        @JvmStatic
        fun newInstance(param1: String) =
            DialogImgBreedFragment().apply {
                arguments = Bundle().apply {
                    putString(IMG_BREED, param1)
                }
            }
    }
}