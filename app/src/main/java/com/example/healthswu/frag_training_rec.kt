package com.example.healthswu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel

class frag_training_rec : Fragment() {

    companion object {
        fun newInstance() = frag_training_rec()
    }

    private lateinit var viewModel: FragTrainingRecViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_frag_training_rec, container, false)
        setHasOptionsMenu(true)
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragTrainingRecViewModel::class.java)
        // TODO: Use the ViewModel
    }

    class FragTrainingRecViewModel : ViewModel() {
        // TODO: Implement the ViewModel
    }


}













