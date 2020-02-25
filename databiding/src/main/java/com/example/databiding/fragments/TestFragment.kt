package com.example.databiding.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.databiding.R
import com.example.databiding.databinding.ActivityMainBinding

class TestFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ActivityMainBinding.inflate(inflater, container, false)
        return binding.root
    }


}