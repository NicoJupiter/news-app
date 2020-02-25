package com.example.dmii.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.dmii.R

class ChoiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.choice_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (view as ViewGroup).children.filter {
            it is Button
        }.forEach {
            it.setOnClickListener {
                computation(it.tag as String)
            }
        }

    }

    fun computation(operation: String) {
        val computationFragment = ComputationFragment.newIstance(operation)

        val activity = activity ?: return

        activity.supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, computationFragment)
            addToBackStack(null)
        }.commit()
    }

}