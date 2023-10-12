package com.akashsoam.instagramcloneapp

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import com.theartofdev.edmodo.cropper.CropImage
import de.hdodenhof.circleimageview.CircleImageView
import java.util.Locale

class AddPostActivity : AppCompatActivity() {

    private var myUrl = ""
    private var imageUri: Uri? = null
    private var storagePostPictureRef: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)

        storagePostPictureRef = FirebaseStorage.getInstance().reference.child("Posts Pictures")

        findViewById<ImageView>(R.id.save_new_post_btn).setOnClickListener { uploadImage() }

        CropImage.activity().setAspectRatio(3, 2).start(this@AddPostActivity)


    }

    private fun uploadImage() {
        when {
            imageUri == null -> Toast.makeText(
                this,
                "Please select an image first.",
                Toast.LENGTH_SHORT
            ).show()

            TextUtils.isEmpty(findViewById<EditText>(R.id.description_post).text.toString()) -> Toast.makeText(
                this,
                "please add a description",
                Toast.LENGTH_SHORT
            ).show()

            else -> {

                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Adding post")
                progressDialog.setMessage("Please wait while we upload your post...")
                progressDialog.show()


                val fileRef =
                    storagePostPictureRef!!.child(System.currentTimeMillis().toString() + ".jpg")
                var uploadTask: StorageTask<*>
                uploadTask = fileRef.putFile(imageUri!!)

                uploadTask.continueWithTask<Uri?>(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            progressDialog.dismiss()
                            throw it
                        }
                    }
                    return@Continuation fileRef.downloadUrl
                }).addOnCompleteListener(OnCompleteListener<Uri> { task ->
                    if (task.isSuccessful) {
                        val downloadUrl = task.result
                        myUrl = downloadUrl.toString()

                        val ref = FirebaseDatabase.getInstance().reference.child("posts")

                        val postId = ref.push().key

                        val postMap = HashMap<String, Any>()
                        postMap["postid"] = postId!!
                        postMap["description"] =
                            findViewById<TextView>(R.id.description_post).text.toString()
                                .lowercase(Locale.getDefault())
                        postMap["publisher"] = FirebaseAuth.getInstance().currentUser!!.uid
                        postMap["image"] = myUrl
                        ref.child(postId).updateChildren(postMap)

                        val intent = Intent(this@AddPostActivity, MainActivity::class.java)
                        Toast.makeText(
                            this@AddPostActivity,
                            "Post uploading successful",
                            Toast.LENGTH_SHORT
                        ).show()
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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val result = CropImage.getActivityResult(data)
            imageUri = result.uri
            findViewById<ImageView>(R.id.image_post).setImageURI(imageUri)
        }
    }
}