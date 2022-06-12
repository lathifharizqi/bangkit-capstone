package com.example.feemeowapp.ui.page.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feemeowapp.ui.model.UserModel
import com.example.feemeowapp.ui.model.UserPreference

import kotlinx.coroutines.launch

class RegisterViewModel (private val pref: UserPreference): ViewModel() {

    fun saveUser(user: UserModel){
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }
}