package com.meganov.clockviewapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel for storing stage of the fragment and buttons visibility
 */
class ClockVM : ViewModel() {
    val currentState = MutableLiveData(1)
    val isPreviousVisible = MutableLiveData(true)
    val isNextVisible = MutableLiveData(true)
}
