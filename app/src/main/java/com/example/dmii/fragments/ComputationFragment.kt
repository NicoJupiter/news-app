package com.example.dmii.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dmii.R
import com.example.dmii.extensions.toDouble
import com.example.dmii.viewModel.CalculViewModel
import kotlinx.android.synthetic.main.computation_fragment.*

class ComputationFragment : Fragment() {

    private lateinit var calculViewModel : CalculViewModel

    val operation: String by lazy {
        arguments?.getString(ARGS_OPERATION, "SUM") ?: "SUM"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.computation_fragment, container, false)
    }

    companion object {
        const val ARGS_OPERATION = "ARGS_OPERATION"
        fun newIstance(operation:String):ComputationFragment {
            return ComputationFragment().apply {
                arguments = bundleOf(ARGS_OPERATION to operation)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Le view model est rattaché au cycle de vie du fragment
        // Quand le fragment se détruit, le VM se détruit aussi
        calculViewModel = ViewModelProvider(this).get(CalculViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submit.setOnClickListener {
            val nb1 = numberField1.toDouble() ?: return@setOnClickListener
            val nb2 = numberField2.toDouble() ?: return@setOnClickListener

            calculViewModel.calcul(nb1,nb2,operation)

            result.text = getString(R.string.result, nb1, nb2, calculViewModel.resultatLiveData.value)

        }

        returnButton.setOnClickListener {
            val choiceFragment = ChoiceFragment()
            val activity = activity ?: return@setOnClickListener

            activity.supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, choiceFragment)
                addToBackStack(null)
            }.commit()
        }

    }

}