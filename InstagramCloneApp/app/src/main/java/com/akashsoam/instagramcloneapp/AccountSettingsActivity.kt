package com.akashsoam.instagramcloneapp

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.akashsoam.instagramcloneapp.Model.User
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import de.hdodenhof.circleimageview.CircleImageView
import java.util.Locale

class AccountSettingsActivity : AppCompatActivity() {
    private lateinit var logoutButton: Button
    private lateinit var firebaseUser: FirebaseUser
    private var checker = ""
    private var myUrl = ""
    private var imageUri: Uri? = null
    private var storageProfilePictureRef: StorageReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_settings)


        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        storageProfilePictureRef = FirebaseStorage.getInstance().reference.child("Profile Pictures")

        logoutButton = findViewById(R.id.logout_btn_acc_sett)

        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this@AccountSettingsActivity, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        findViewById<TextView>(R.id.change_image_txt_btn).setOnClickListener {
            checker = "clicked"
            CropImage.activity().setAspectRatio(1, 1).start(this@AccountSettingsActivity)
        }

        findViewById<ImageView>(R.id.save_info_profile_imgview_btn).setOnClickListener {
            if (checker == "clicked") {
                uploadImageAndUpdateInfo()
            } else {
                updateUserInfoOnly()
            }
        }

        getUserInfo()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val result = CropImage.getActivityResult(data)
            imageUri = result.uri
            findViewById<CircleImageView>(R.id.profile_image_view_acc_settings).setImageURI(imageUri)
        }
    }

    private fun updateUserInfoOnly() {
        if (findViewById<TextView>(R.id.full_name_account_sett).text.toString() == "" || findViewById<TextView>(
                R.id.user_name_account_sett
            ).text.toString() == "" || findViewById<TextView>(R.id.bio_account_sett).text.toString() == ""
        ) {
            if (findViewById<TextView>(R.id.full_name_account_sett).text.toString() == "") {
                Toast.makeText(this, "Please enter full name", Toast.LENGTH_SHORT).show()
            }
            if (findViewById<TextView>(R.id.user_name_account_sett).text.toString() == "") {
                Toast.makeText(this, "Please enter user name", Toast.LENGTH_SHORT).show()
            }
            if (findViewById<TextView>(R.id.bio_account_sett).text.toString() == "") {
                Toast.makeText(this, "Please enter bio", Toast.LENGTH_SHORT).show()
            }
        } else {

            val usersRef = FirebaseDatabase.getInstance().reference.child("users")
            val userMap = HashMap<String, Any>()
            userMap["fullname"] =
                findViewById<TextView>(R.id.full_name_account_sett).text.toString()
                    .lowercase(Locale.getDefault())
            userMap["username"] =
                findViewById<TextView>(R.id.user_name_account_sett).text.toString()
                    .lowercase(Locale.getDefault())
            userMap["bio"] = findViewById<TextView>(R.id.bio_account_sett).text.toString()
                .lowercase(Locale.getDefault())

            usersRef.child(firebaseUser.uid).updateChildren(userMap)

            Toast.makeText(
                this@AccountSettingsActivity, "Account update successful", Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(this@AccountSettingsActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun uploadImageAndUpdateInfo() {


        if (findViewById<TextView>(R.id.full_name_account_sett).text.toString() == "" || findViewById<TextView>(
                R.id.user_name_account_sett
            ).text.toString() == "" || findViewById<TextView>(R.id.bio_account_sett).text.toString() == ""
        ) {
            if (findViewById<TextView>(R.id.full_name_account_sett).text.toString() == "") {
                Toast.makeText(this, "Please enter full name", Toast.LENGTH_SHORT).show()
            }
            if (findViewById<TextView>(R.id.user_name_account_sett).text.toString() == "") {
                Toast.makeText(this, "Please enter user name", Toast.LENGTH_SHORT).show()
            }
            if (findViewById<TextView>(R.id.bio_account_sett).text.toString() == "") {
                Toast.makeText(this, "Please enter bio", Toast.LENGTH_SHORT).show()
            }
            if (findViewById<CircleImageView>(R.id.profile_image_view_acc_settings) == null) {
                Toast.makeText(this, "Please upload image", Toast.LENGTH_SHORT).show()
            }
        } else {

            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Account Settings")
            progressDialog.setMessage("Updating your profile")
            progressDialog.show()


            val fileRef = storageProfilePictureRef!!.child(firebaseUser.uid + ".jpg")
            var uploadTask: StorageTask<*>
            uploadTask = fileRef.putFile(imageUri!!)
            uploadTask.continueWithTask<Uri?>(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                        progressDialog.dismiss()
                    }
                }
                return@Continuation fileRef.downloadUrl
            }).addOnCompleteListener(OnCompleteListener<Uri> { task ->
                if (task.isSuccessful) {
                    val downloadUrl = task.result
                    myUrl = downloadUrl.toString()

                    val ref = FirebaseDatabase.getInstance().reference.child("users")
                    val userMap = HashMap<String, Any>()
                    userMap["fullname"] =
                        findViewById<TextView>(R.id.full_name_account_sett).text.toString()
                            .lowercase(Locale.getDefault())
                    userMap["username"] =
                        findViewById<TextView>(R.id.user_name_account_sett).text.toString()
                            .lowercase(Locale.getDefault())
                    userMap["bio"] = findViewById<TextView>(R.id.bio_account_sett).text.toString()
                        .lowercase(Locale.getDefault())
                    userMap["image"] = myUrl
                    ref.child(firebaseUser.uid).updateChildren(userMap)
                    Toast.makeText(
                        this@AccountSettingsActivity,
                        "Account update successful",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(this@AccountSettingsActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    progressDialog.dismiss()
                } else {
                    progressDialog.dismiss()
                }
            }

            )
        }
    }

    private fun getUserInfo() {
        val usersRef =
            FirebaseDatabase.getInstance().reference.child("users").child(firebaseUser.uid)
        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.exists()) {
                    val user = dataSnapshot.getValue<User>(User::class.java)
                    Picasso.get().load(user!!.getImage()).placeholder(R.drawable.profile)
                        .into(findViewById<CircleImageView>(R.id.profile_image_view_acc_settings))
                    findViewById<TextView>(R.id.user_name_account_sett).text = user.getUsername()
                    findViewById<TextView>(R.id.full_name_account_sett).text = user.getFullname()
                    findViewById<TextView>(R.id.bio_account_sett).text = user.getBio()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

}