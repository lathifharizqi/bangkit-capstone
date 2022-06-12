package com.example.feemeowapp.ui.page.boarding

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.example.feemeowapp.databinding.ActivityBoardingBinding
import com.example.feemeowapp.ui.page.welcome.WelcomeActivity

class BoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
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
        supportActionBar?.hide()
    }
    private fun setupAction(){
        binding.btnNext.setOnClickListener(){
            val i = Intent(this@BoardingActivity, WelcomeActivity::class.java)
            startActivity(i)
            Toast.makeText(
              this@BoardingActivity,
                "Chosse Login or Register",
                Toast.LENGTH_SHORT
            ) .show()
        }
    }

}