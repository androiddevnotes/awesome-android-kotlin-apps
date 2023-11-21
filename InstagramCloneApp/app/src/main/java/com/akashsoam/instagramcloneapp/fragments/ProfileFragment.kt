package com.akashsoam.instagramcloneapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.akashsoam.instagramcloneapp.AccountSettingsActivity
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

class ProfileFragment : Fragment() {
    private lateinit var profileId: String
    private lateinit var firebaseUser: FirebaseUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)


        firebaseUser = FirebaseAuth.getInstance().currentUser!!


        val pref = context?.getSharedPreferences("PREFS", Context.MODE_PRIVATE)
        if (pref != null) {
            this.profileId = pref.getString("profileId", "none").toString()
        }
        if (profileId == firebaseUser.uid) {
            view.findViewById<Button>(R.id.edit_account_settings_btn).text = "Edit Profile"
        } else if (profileId != firebaseUser.uid) {
            checkFollowAndFollowingButtonStatus()
        }
        view.findViewById<View>(R.id.edit_account_settings_btn).setOnClickListener {
            val getButtonText =
                view.findViewById<Button>(R.id.edit_account_settings_btn).text.toString()
            when {
                getButtonText == "Edit Profile" -> startActivity(
                    Intent(
                        context,
                        AccountSettingsActivity::class.java
                    )
                )

                getButtonText == "Follow" -> {
                    firebaseUser.uid.let { it ->
                        FirebaseDatabase.getInstance().reference.child("follow")
                            .child(it.toString())
                            .child("following").child(profileId).setValue(true)

                    }
                    firebaseUser.uid.let { it ->
                        FirebaseDatabase.getInstance().reference.child("follow")
                            .child(profileId)
                            .child("followers").child(it.toString()).setValue(true)

                    }
                }

                getButtonText == "Following" -> {
                    firebaseUser.uid.let { it ->
                        FirebaseDatabase.getInstance().reference.child("follow")
                            .child(it.toString())
                            .child("following").child(profileId).removeValue()

                    }
                    firebaseUser.uid.let { it ->
                        FirebaseDatabase.getInstance().reference.child("follow")
                            .child(profileId)
                            .child("followers").child(it.toString()).removeValue()

                    }
                }

            }
        }
        getFollowers()
        getFollowings()
        getUserInfo()
        return view
    }

    private fun checkFollowAndFollowingButtonStatus() {
        val followingRef = firebaseUser.uid.let { it ->
            FirebaseDatabase.getInstance().reference.child("follow").child(it.toString())
                .child("following")
        }
        if (followingRef != null) {
            followingRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.child(profileId).exists()) {
                        view?.findViewById<Button>(R.id.edit_account_settings_btn)?.text =
                            "Following"

                    } else {
                        view?.findViewById<Button>(R.id.edit_account_settings_btn)?.text = "Follow"

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        }
    }

    private fun getFollowers() {
        val followersRef =
            FirebaseDatabase.getInstance().reference.child("follow").child(profileId)
                .child("followers")

        followersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    view?.findViewById<TextView>(R.id.total_followers)?.text =
                        dataSnapshot.childrenCount.toString()

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun getFollowings() {
        val followersRef =
            FirebaseDatabase.getInstance().reference.child("follow").child(profileId)
                .child("following")

        followersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    view?.findViewById<TextView>(R.id.total_following)?.text =
                        dataSnapshot.childrenCount.toString()

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun getUserInfo() {
        val usersRef = FirebaseDatabase.getInstance().getReference().child("users").child(profileId)
        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.exists()) {
                    val user = dataSnapshot.getValue<User>(User::class.java)
                    Picasso.get().load(user!!.getImage()).placeholder(R.drawable.profile)
                        .into(view?.findViewById<CircleImageView>(R.id.pro_image_profile_frag))
                    view?.findViewById<TextView>(R.id.profile_fragment_username)?.text =
                        user.getUsername()
                    view?.findViewById<TextView>(R.id.full_name_profile_frag)?.text =
                        user.getFullname()
                    view?.findViewById<TextView>(R.id.bio_profile_frag)?.text = user.getBio()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    override fun onStop() {
        super.onStop()
        val pref = context?.getSharedPreferences("PREFS", Context.MODE_PRIVATE)?.edit()
        pref?.putString("profileId", firebaseUser.uid)
        pref?.apply()
    }

    override fun onPause() {
        super.onPause()
        val pref = context?.getSharedPreferences("PREFS", Context.MODE_PRIVATE)?.edit()
        pref?.putString("profileId", firebaseUser.uid)
        pref?.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        val pref = context?.getSharedPreferences("PREFS", Context.MODE_PRIVATE)?.edit()
        pref?.putString("profileId", firebaseUser.uid)
        pref?.apply()
    }
}