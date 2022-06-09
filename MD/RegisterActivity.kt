package com.example.feemeowapp.ui.page.register

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.feemeowapp.databinding.ActivityRegisterBinding
import com.example.feemeowapp.ui.request.RegisterRequest
import com.example.feemeowapp.ui.api.ApiConfig
import com.example.feemeowapp.ui.model.UserModel
import com.example.feemeowapp.ui.model.UserPreference
import com.example.feemeowapp.ui.model.ViewModelFactory
import com.example.feemeowapp.ui.page.login.LoginActivity
import com.example.feemeowapp.ui.page.welcome.WelcomeActivity
import com.example.feemeowapp.ui.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        setupView()
        setupViewModel()
        setupAction()
        playAnimation()
    }

    private fun setupAction() {
        binding.btBack.setOnClickListener {
            val i = Intent(this@RegisterActivity, WelcomeActivity::class.java)
            startActivity(i)
        }
        binding.signupButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val nama = binding.nameEditText.text.toString()
            val phoneNumber = binding.telfonEditText.text.toString()
            val location = binding.alamatEditText.text.toString()

            when {
                username.isEmpty() -> {
                    binding.usernamelEditTextLayout.error = "Isikan username anda"
                }
                password.isEmpty() -> {
                    binding.passwordEditTextLayout.error = "Password anda tidak boleh kosong"
                }
                nama.isEmpty() -> {
                    binding.nameEditTextLayout.error = "Isikan nama lengkap anda"
                }
                    phoneNumber.isEmpty() -> {
                     binding.telfonEditTextLayout.error = "No Telfon tidak boleh kosong"
                    }
                    location.isEmpty() -> {
                        binding.alamatEditTextLayout.error = "Alamat tidak boleh kosong, isikan asal Kota Anda"
                    }
                else -> {
                    val retIn = ApiConfig.getApiService()
                    retIn.registerUser(RegisterRequest(username, password, nama, phoneNumber, location)).enqueue(object :
                        Callback<RegisterResponse> {
                        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                            showLoading(true)
                            Toast.makeText(
                                this@RegisterActivity,
                                "Silakan Daftar kembali!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onResponse(
                            call: Call<RegisterResponse>,
                            response: Response<RegisterResponse>
                        ) {
                            showLoading(false)
                            if (response.code() == 201) {
                                showLoading(true)
                                val i = Intent(this@RegisterActivity, LoginActivity::class.java)
                                startActivity(i)
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Registration Succses!",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
//                                registerViewModel.saveUser(UserModel(username, password, nama,  phoneNumber, location, false))
//                                AlertDialog.Builder(this@RegisterActivity).apply {
//                                    setTitle("Selamat!")
//                                    setMessage("Kamu berhasil buat akun. Yuk, login dan have fun!.")
//                                    setPositiveButton("Lanjut") { _, _ ->
//                                        finish()
//                                    }
//                                    create()
//                                    show()
//                                }
                            } else {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Registration failed!",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                    })
                }
            }
        }
    }
    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    private fun setupViewModel() {
        registerViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[RegisterViewModel::class.java]
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.ProgresBar.visibility = View.VISIBLE
        } else {
            binding.ProgresBar.visibility = View.GONE
        }
    }
    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
        val title =
            ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(500)
        val message =
            ObjectAnimator.ofFloat(binding.messageTextView, View.ALPHA, 1f).setDuration(500)
        val nameTextView =
            ObjectAnimator.ofFloat(binding.nameTextView, View.ALPHA, 1f).setDuration(500)
        val nameEditTextLayout =
            ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f).setDuration(500)
        val usernameTextView =
            ObjectAnimator.ofFloat(binding.usernameTextView, View.ALPHA, 1f).setDuration(500)
        val usernameEditTextLayout =
            ObjectAnimator.ofFloat(binding.usernamelEditTextLayout, View.ALPHA, 1f).setDuration(500)
        val alamatTextView =
            ObjectAnimator.ofFloat(binding.alamatTextView, View.ALPHA, 1f).setDuration(500)
        val alamatEditTextLayout =
            ObjectAnimator.ofFloat(binding.alamatEditTextLayout, View.ALPHA, 1f).setDuration(500)
        val  telfonTextView=
            ObjectAnimator.ofFloat(binding.telfonTextView, View.ALPHA, 1f).setDuration(500)
        val telfonEditTextLayout =
            ObjectAnimator.ofFloat(binding.telfonEditTextLayout, View.ALPHA, 1f).setDuration(500)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(500)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f)
                .setDuration(500)
        val login = ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(
                title,
                message,
                nameTextView,
                nameEditTextLayout,
                usernameTextView,
                usernameEditTextLayout,
                alamatTextView,
                alamatEditTextLayout,
                telfonTextView,
                telfonEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                login
            )
            startDelay = 500
        }.start()
    }
}
