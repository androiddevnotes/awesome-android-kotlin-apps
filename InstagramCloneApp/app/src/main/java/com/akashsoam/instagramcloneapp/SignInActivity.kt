package com.akashsoam.instagramcloneapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {


    lateinit var passwordSignUpEdtText: EditText
    lateinit var emailSignUpEdtText: EditText
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        emailSignUpEdtText = findViewById(R.id.email_login)
        passwordSignUpEdtText = findViewById(R.id.password_login)


        findViewById<View>(R.id.signup_link_btn).setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        findViewById<View>(R.id.login_btn).setOnClickListener {
            loginUser()
        }


    }

    private fun loginUser() {
        val email = emailSignUpEdtText.text.toString()
        val password = passwordSignUpEdtText.text.toString()

        when {
            TextUtils.isEmpty(email) -> Toast.makeText(
                this, "Please provide a valid email", Toast.LENGTH_SHORT
            ).show()

            TextUtils.isEmpty(password) -> Toast.makeText(
                this, "Please provide a valid password", Toast.LENGTH_SHORT
            ).show()

            else -> {
                progressDialog =
                    ProgressDialog(this@SignInActivity)
                progressDialog.setTitle("Sign In")
                progressDialog.setMessage("Verifying Credentials")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()


                val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        progressDialog.dismiss()
                        val intent = Intent(this@SignInActivity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    } else {
                        val message = task.exception.toString()
                        Toast.makeText(
                            this, message, Toast.LENGTH_SHORT
                        ).show()
                        FirebaseAuth.getInstance().signOut()
                        progressDialog.dismiss()
                    }
                }
            }

        }

    }

    override fun onStart() {
        super.onStart()

        if (FirebaseAuth.getInstance().currentUser != null) {
            val intent = Intent(this@SignInActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}