package com.aryan.competishum_dummy

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aryan.competishum_dummy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.etEnterMob.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val length = s?.length ?: 0
                if (length == 10) {
                    binding.btnVerify.setBackgroundColor(Color.parseColor("#3E3EF7"))
                } else {
                    binding.btnVerify.setBackgroundColor(Color.parseColor("#B5B3B3"))
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnVerify.setOnClickListener {
            val intent = Intent(this, VerifyOTP::class.java)
            startActivity(intent)
        }

        val termsText = "Terms & Conditions"
        val privacyText = "Privacy Policy"
        val fullText = "By signing up, you agree to our $termsText \nand $privacyText"

        val spannableString = SpannableString(fullText)

        val termsClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@MainActivity, "Terms & Conditions Clicked", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: android.text.TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }

        val privacyClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@MainActivity, "Privacy Policy Clicked", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: android.text.TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }

        spannableString.setSpan(termsClickableSpan, fullText.indexOf(termsText), fullText.indexOf(termsText) + termsText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(Color.BLUE), fullText.indexOf(termsText), fullText.indexOf(termsText) + termsText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(privacyClickableSpan, fullText.indexOf(privacyText), fullText.indexOf(privacyText) + privacyText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(Color.BLUE), fullText.indexOf(privacyText), fullText.indexOf(privacyText) + privacyText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvTermsPrivacy.text = spannableString
        binding.tvTermsPrivacy.movementMethod = android.text.method.LinkMovementMethod.getInstance()
    }
}
