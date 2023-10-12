package com.akashsoam.instagramcloneapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.akashsoam.instagramcloneapp.Model.Post
import com.akashsoam.instagramcloneapp.Model.User
import com.akashsoam.instagramcloneapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class PostAdapter(val mContext: Context, val mPost: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private var firebaseUser: FirebaseUser? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.post_layout, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mPost.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        firebaseUser = FirebaseAuth.getInstance().currentUser
        val post = mPost[position]
        Picasso.get().load(post.getImage()).into(holder.postImage)

        publisherInfo(holder.profileImage, holder.username, holder.publisher, post.getPublisher())

    }

    private fun publisherInfo(
        profileImage: CircleImageView,
        username: TextView,
        publisher: TextView,
        publisherId: String
    ) {
        val usersRef = FirebaseDatabase.getInstance().reference.child("users").child(publisherId)
        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val user = dataSnapshot.getValue<User>(User::class.java)
                    Picasso.get().load(user!!.getImage()).placeholder(R.drawable.profile)
                        .into(profileImage)
                    username.text = user.getUsername()
                    publisher.text = user.getFullname()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    inner class PostViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        var profileImage: CircleImageView
        var postImage: ImageView
        var commentButton: ImageView
        var likeButton: ImageView
        var saveButton: ImageView
        var username: TextView
        var likes: TextView
        var publisher: TextView
        var description: TextView
        var comments: TextView

        init {
            profileImage = itemView.findViewById(R.id.user_profile_image_post)
            postImage = itemView.findViewById(R.id.post_image_home)
            likeButton = itemView.findViewById(R.id.post_image_like_btn)
            commentButton = itemView.findViewById(R.id.post_image_comment_btn)
            saveButton = itemView.findViewById(R.id.post_save_image_btn)
            username = itemView.findViewById(R.id.user_profile_image_post)
            likes = itemView.findViewById(R.id.likes)
            publisher = itemView.findViewById(R.id.publisher)
            description = itemView.findViewById(R.id.description)
            comments = itemView.findViewById(R.id.comments)
        }
    }
}