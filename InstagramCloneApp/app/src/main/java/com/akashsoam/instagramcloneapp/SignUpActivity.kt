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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.Locale

class SignUpActivity : AppCompatActivity() {
    lateinit var fullNameSignUpEdtText: EditText
    lateinit var usernameSignUpEdtText: EditText
    lateinit var passwordSignUpEdtText: EditText
    lateinit var emailSignUpEdtText: EditText
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        fullNameSignUpEdtText = findViewById(R.id.fullname_signup)
        usernameSignUpEdtText = findViewById(R.id.username_signup)
        emailSignUpEdtText = findViewById(R.id.email_signup)
        passwordSignUpEdtText = findViewById(R.id.password_signup)


        findViewById<View>(R.id.signin_link_btn).setOnClickListener {
//            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
        findViewById<View>(R.id.signup_btn).setOnClickListener {
            createAccount()
        }
    }

    private fun createAccount() {
        val fullname = fullNameSignUpEdtText.text.toString()
        val username = usernameSignUpEdtText.text.toString()
        val email = emailSignUpEdtText.text.toString()
        val password = passwordSignUpEdtText.text.toString()

        when {
            TextUtils.isEmpty(fullname) -> Toast.makeText(
                this, "Please provide a valid username", Toast.LENGTH_SHORT
            ).show()

            TextUtils.isEmpty(username) -> Toast.makeText(
                this, "Please provide a valid username", Toast.LENGTH_SHORT
            ).show()

            TextUtils.isEmpty(email) -> Toast.makeText(
                this, "Please provide a valid email", Toast.LENGTH_SHORT
            ).show()

            TextUtils.isEmpty(password) -> Toast.makeText(
                this, "Please provide a valid password", Toast.LENGTH_SHORT
            ).show()

            else -> {
                progressDialog =
                    ProgressDialog(this@SignUpActivity)
                progressDialog.setTitle("Sign Up")
                progressDialog.setMessage("Registering..This may take some time")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()


                val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        run {
                            if (task.isSuccessful) {
                                saveUserInfo(fullname, username, email, progressDialog)

                            } else {
                                val message = task.exception.toString()
                                Toast.makeText(
                                    this, message, Toast.LENGTH_SHORT
                                ).show()
                                mAuth.signOut()
                                progressDialog.dismiss()
                            }
                        }
                    }
            }
        }
    }

    private fun saveUserInfo(
        fullname: String,
        username: String,
        email: String,
        progressDialog: ProgressDialog
    ) {
        val currentUserID = FirebaseAuth.getInstance().currentUser!!.uid
        val usersRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("users")

        val userMap = HashMap<String, Any>()
        userMap["uid"] = currentUserID
        userMap["fullname"] = fullname.lowercase(Locale.getDefault())
        userMap["email"] = email
        userMap["username"] = username.lowercase(Locale.getDefault())
        userMap["bio"] = "Hey I am using Insta clone app."
        userMap["image"] =
            "https://firebasestorage.googleapis.com/v0/b/instagram-clone-app-788ee.appspot.com/o/Default%20Images%2Fprofile.png?alt=media&token=96550107-44ad-452f-94d1-a8e53e2c1c51"

        usersRef.child(currentUserID).setValue(userMap).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                progressDialog.dismiss()
                Toast.makeText(
                    this@SignUpActivity,
                    "Account creation successful",
                    Toast.LENGTH_SHORT
                ).show()


                FirebaseDatabase.getInstance().reference.child("follow").child(currentUserID)
                    .child("following").child(currentUserID).setValue(true)


                val intent = Intent(this@SignUpActivity, MainActivity::class.java)
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