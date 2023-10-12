package com.akashsoam.instagramcloneapp.Model

class Post {
    private var postid: String = ""
    private var image: String = ""
    private var publisher: String = ""
    private var description: String = ""


    constructor()
    constructor(postid: String, image: String, publisher: String, description: String) {
        this.postid = postid
        this.image = image
        this.publisher = publisher
        this.description = description
    }

    fun getPostid(): String {
        return postid
    }

    fun getImage(): String {
        return postid
    }

    fun getPublisher(): String {
        return postid
    }

    fun getDescription(): String {
        return postid
    }

    fun setPostid(postid: String) {
        this.postid = postid
    }

    fun setPublisher(publisher: String) {
        this.publisher = publisher
    }

    fun setImage(image: String) {
        this.image = image
    }

    fun setDescription(description: String) {
        this.description = description
    }

}