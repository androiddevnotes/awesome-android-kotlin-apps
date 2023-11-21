package com.akashsoam.instagramcloneapp.Model

class User {
    private var username: String = ""
    private var fullname: String = ""
    private var bio: String = ""
    private var email: String = ""
    private var image: String = ""
    private var uid: String = ""

    constructor()

    constructor(
        username: String, fullname: String, bio: String, image: String, uid: String, email: String
    ) {
        this.bio = bio
        this.username = username
        this.fullname = fullname
        this.image = image
        this.uid = uid
        this.email = email
    }

    fun getUsername(): String {
        return username
    }

    fun getFullname(): String {
        return fullname
    }

    fun getImage(): String {
        return image
    }

    fun getEmail(): String {
        return email
    }

    fun getUid(): String {
        return uid
    }

    fun getBio(): String {
        return bio
    }

    fun setUsername(username: String) {
        this.username = username
    }

    fun setFullname(fullname: String) {
        this.fullname = fullname
    }

    fun setBio(bio: String) {
        this.bio = bio
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun setImage(image: String) {
        this.image = image
    }

    fun setUid(uid: String) {
        this.uid = uid
    }

}