package com.example.feemeowapp.ui.page.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.feemeowapp.databinding.ActivitySplashScreenBinding
import com.example.feemeowapp.ui.page.boarding.BoardingActivity


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        binding.splashLogo.alpha = 0f
        binding.splashLogo.animate().setDuration(1000).alpha(1f).withEndAction{
            val i = Intent(this, BoardingActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
        binding.txApp.alpha = 0f
        binding.txApp.animate().setDuration(1500).alpha(1f).withEndAction{
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
        binding.txSlogan.alpha = 0f
        binding.txSlogan.animate().setDuration(2800).alpha(1f).withEndAction{
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}