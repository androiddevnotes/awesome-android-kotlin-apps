package com.akashsoam.instagramcloneapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akashsoam.instagramcloneapp.Adapter.PostAdapter
import com.akashsoam.instagramcloneapp.Model.Post
import com.akashsoam.instagramcloneapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {


    private var postAdapter: PostAdapter? = null
    private var postList: MutableList<Post>? = null
    private var followingList: MutableList<Post>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        var recyclerView: RecyclerView? = null
        recyclerView = view.findViewById(R.id.recycler_view_home)
        val linearLayoutManager = LinearLayoutManager(context)

        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true

        recyclerView.layoutManager = linearLayoutManager

        postList = ArrayList()
        postAdapter = context?.let {
            PostAdapter(it, postList as ArrayList<Post>)
        }
        recyclerView.adapter = postAdapter

        checkFollowings()



        return view
    }

    private fun checkFollowings() {
        followingList = ArrayList()

        val followingRef =
            FirebaseDatabase.getInstance().reference.child(FirebaseAuth.getInstance().currentUser!!.uid)
                .child("following")

        followingRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    (followingList as ArrayList<String>).clear()
                    for (snapshot in dataSnapshot.children) {
                        snapshot.key?.let {
                            (followingList as ArrayList<String>).add(it)
                        }
                    }
                    retrievePosts()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }

    private fun retrievePosts() {
        val postsRef =
            FirebaseDatabase.getInstance().reference.child("posts")
        postsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                postList?.clear()
                for (snapshot in dataSnapshot.children) {
                    val post = snapshot.getValue(Post::class.java)
                    for (userId in (followingList!! as ArrayList<String>)) {
                        if (post!!.getPublisher() == userId) {
                            postList!!.add(post)
                        }
                        postAdapter!!.notifyDataSetChanged()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

}