package com.example.dmii.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculViewModel : ViewModel() {

    private val _resultatLiveData = MutableLiveData<Double>()
    val resultatLiveData : LiveData<Double>
        get() = _resultatLiveData

    fun calcul(nb1 : Double, nb2:Double, operation: String) {

        _resultatLiveData.value =  when (operation) {
            "MINUS" -> nb1 - nb2
            "SUM" -> nb1 + nb2
            "DIVIDE" -> nb1 / nb2
            "TIMES" -> nb1 * nb2
            else -> 0.0
        }
    }

}