package com.example.feemeowapp.ui.page.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

import com.example.feemeowapp.ui.model.UserModel
import com.example.feemeowapp.ui.model.UserPreference

class WelcomeViewModel (private val pref: UserPreference) : ViewModel() {
    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }
}