package com.example.simplecounter_viewmodel

import androidx.lifecycle.ViewModel

class AppViewModel: ViewModel() {

    private var counter = 0

    fun getCurrentCounter(): Int {
        return counter
    }
    fun updateCounter(): Int {
        return ++counter
    }

}