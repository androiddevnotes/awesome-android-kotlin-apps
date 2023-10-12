package com.akashsoam.instagramcloneapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.akashsoam.instagramcloneapp.Model.User
import com.akashsoam.instagramcloneapp.R
import com.akashsoam.instagramcloneapp.fragments.ProfileFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(
    private var mContext: Context,
    private var mUser: List<User>,
    private var isFragment: Boolean = false
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.user_item_layout, parent, false)
        return UserAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mUser.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = mUser[position]
        holder.usernameTextView.text = user.getUsername()
        holder.userfullnameTextView.text = user.getFullname()
        Picasso.get().load(user.getImage()).placeholder(R.drawable.profile)
            .into(holder.userProfileImage)

        checkFollowingStatus(user.getUid(), holder.userFollowButton)

        holder.itemView.setOnClickListener {
            val pref = mContext.getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit()
            pref.putString("profileId", user.getUid())
            pref.apply()

            (mContext as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileFragment()).commit()
        }

        holder.userFollowButton.setOnClickListener {
            if (holder.userFollowButton.text.toString() == "Follow") {
                firebaseUser?.uid.let { it ->
                    FirebaseDatabase.getInstance().reference.child("follow").child(it.toString())
                        .child("following").child(user.getUid()).setValue(true)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {


                                firebaseUser?.uid.let { it1 ->
                                    FirebaseDatabase.getInstance().reference.child("follow")
                                        .child(user.getUid())
                                        .child("followers").child(it1.toString()).setValue(true)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {

                                            }
                                        }
                                }
                            }
                        }
                }
            } else {
                firebaseUser?.uid.let { it ->
                    FirebaseDatabase.getInstance().reference.child("follow").child(it.toString())
                        .child("following").child(user.getUid()).removeValue()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {


                                firebaseUser?.uid.let { it1 ->
                                    FirebaseDatabase.getInstance().reference.child("follow")
                                        .child(user.getUid())
                                        .child("followers").child(it1.toString()).removeValue()
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {

                                            }
                                        }
                                }
                            }
                        }
                }
            }
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var usernameTextView: TextView = itemView.findViewById(R.id.user_name_search)
        var userfullnameTextView: TextView = itemView.findViewById(R.id.user_full_name_search)
        var userFollowButton: Button = itemView.findViewById(R.id.follow_btn_search)
        var userProfileImage: CircleImageView =
            itemView.findViewById(R.id.user_profile_image_search)

    }

    private fun checkFollowingStatus(uid: String, userFollowButton: Button) {
        val followingRef = firebaseUser?.uid.let { it ->
            FirebaseDatabase.getInstance().reference.child("follow").child(it.toString())
                .child("following")

        }
        followingRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.child(uid).exists()) {
                    userFollowButton.text = "Following"
                } else {
                    userFollowButton.text = "Follow"
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}