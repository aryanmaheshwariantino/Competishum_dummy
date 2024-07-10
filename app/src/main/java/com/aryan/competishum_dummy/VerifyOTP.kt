package com.aryan.competishum_dummy

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aryan.competishum_dummy.databinding.ActivityVerifyOtpBinding

class VerifyOTP : AppCompatActivity() {

    private lateinit var binding: ActivityVerifyOtpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupOtpInputs()
    }

    private fun setupOtpInputs() {
        val otpBoxes = listOf(
            binding.otpBox1,
            binding.otpBox2,
            binding.otpBox3,
            binding.otpBox4
        )

        otpBoxes.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1 && index < otpBoxes.size - 1) {
                        otpBoxes[index + 1].requestFocus()
                    } else if (s?.length == 0 && index > 0) {
                        otpBoxes[index - 1].requestFocus()
                    }

                    if (otpBoxes.all { it.text.length == 1 }) {
                        navigateToNextScreen()
                    }
                }
            })

            editText.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && editText.text.isEmpty() && index != 0) {
                    otpBoxes[index - 1].requestFocus()
                    return@setOnKeyListener true
                }
                false
            }
        }
    }
    private fun navigateToNextScreen() {
        val intent = Intent(this, GetStarted::class.java)
        startActivity(intent)
        finish()
    }
}
