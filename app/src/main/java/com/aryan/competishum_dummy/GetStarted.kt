package com.aryan.competishum_dummy

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aryan.competishum_dummy.databinding.ActivityGetStartedBinding

class GetStarted : AppCompatActivity() {
    private lateinit var binding: ActivityGetStartedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpTextWatchers()

        updateButtonBackground()

        binding.btnGetSubmit2.setOnClickListener{
            val intent = Intent(this,GetStarted_two::class.java)
            startActivity(intent)
            finish()
        }

        Log.d("GetStarted", "Activity created")
    }

    private fun setUpTextWatchers() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                updateButtonBackground()
            }
        }

        binding.etEnterHereText.addTextChangedListener(textWatcher)
        binding.etEnterCityText.addTextChangedListener(textWatcher)
    }

    private fun updateButtonBackground() {
        val text1 = binding.etEnterHereText.text.toString().trim()
        val text2 = binding.etEnterCityText.text.toString().trim()

        Log.d("GetStarted", "etEnterHereText: $text1, etEnterCityText: $text2")

        if (text1.isNotEmpty() && text2.isNotEmpty()) {
            binding.btnGetSubmit2.setBackgroundColor(Color.parseColor("#3E3EF7"))
        } else {
            binding.btnGetSubmit2.setBackgroundColor(Color.parseColor("#B5B3B3"))
        }
    }
}
