package com.example.feemeowapp.ui.page.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider

import com.example.feemeowapp.databinding.ActivityLoginBinding
import com.example.feemeowapp.ui.api.ApiConfig
import com.example.feemeowapp.ui.model.UserModel
import com.example.feemeowapp.ui.model.UserPreference
import com.example.feemeowapp.ui.model.ViewModelFactory
import com.example.feemeowapp.ui.page.MainActivity
import com.example.feemeowapp.ui.page.welcome.WelcomeActivity
import com.example.feemeowapp.ui.request.SignInRequest
import com.example.feemeowapp.ui.response.LoginResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var user: UserModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupViewModel()
        setupView()
        setupAction()
        playAnimation()
    }

    private fun setupAction() {
        binding.btBack.setOnClickListener {
            startActivity(Intent(this@LoginActivity, WelcomeActivity::class.java))
        }
        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            when {
                username.isEmpty() -> {
                    binding.passwordEditTextLayout.error = "Please Filled Username"
                }
                password.isEmpty() -> {
                    binding.passwordEditTextLayout.error = "Please Filled Password"
                }
                else -> {
                    val retIn = ApiConfig.getApiService()
                    retIn.loginUser(SignInRequest(username, password)).enqueue(object : Callback<LoginResponse> {
                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            showLoading(true)
                            Toast.makeText(
                                this@LoginActivity,
                                t.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            showLoading(false)
                            if (response.code() == 200) {
                                loginViewModel.login()
                                AlertDialog.Builder(this@LoginActivity).apply {
                                    setTitle("Yeah!")
                                    setMessage("Succsess login. Lets Have Fun!")
                                    setPositiveButton("Next") { _, _ ->
                                        val intent = Intent(context, MainActivity::class.java)
                                        intent.flags =
                                            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                        startActivity(intent)
                                        finish()
                                    }
                                    create()
                                    show()
                                }
                            } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Login failed!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
                }
            }
        }
    }

    fun String.isValidEmail() =
        !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()

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
        supportActionBar?.hide()
    }

    private fun setupViewModel() {
        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[LoginViewModel::class.java]

        loginViewModel.getUser().observe(this, { user ->
            this.user = user
        })
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
        val emailTextView =
            ObjectAnimator.ofFloat(binding.usernameTextView, View.ALPHA, 1f).setDuration(500)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.usernamelEditTextLayout, View.ALPHA, 1f).setDuration(500)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(500)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f)
                .setDuration(500)
        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(
                title,
                message,
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                login
            )
            startDelay = 500
        }.start()
    }
}
