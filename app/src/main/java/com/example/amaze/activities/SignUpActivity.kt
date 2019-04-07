package com.example.amaze.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.example.amaze.R
import com.ybs.passwordstrengthmeter.PasswordStrength
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), TextWatcher {

    lateinit var username : String
    lateinit var email : String
    lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signUpPasswordTextview.addTextChangedListener(this)

    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        updatePasswordStrengthView(s.toString())
    }

    private fun updatePasswordStrengthView(password: String) {

        val progressBar = signUpProgressBar
        val strengthView = signUpPasswordStrength

        if (TextView.VISIBLE != strengthView.visibility)
            return

        if (password.isEmpty()) {
            strengthView.text = ""
            progressBar.progress = 0
            return
        }

        val str = PasswordStrength.calculateStrength(password)
        strengthView.text = str.getText(this)
        strengthView.setTextColor(str.color)

        progressBar.progressDrawable.setColorFilter(str.color, android.graphics.PorterDuff.Mode.SRC_IN)
        if (str.getText(this) == "Weak") {
            progressBar.progress = 25
        } else if (str.getText(this) == "Medium") {
            progressBar.progress = 50
        } else if (str.getText(this) == "Strong") {
            progressBar.progress = 75
        } else {
            progressBar.progress = 100
        }
    }

}
