package com.sanogueralorenzo.presentation

import com.sanogueralorenzo.domain.model.*
import com.sanogueralorenzo.presentation.model.PostItem

fun createUser(): User = User("1", "name", "username", "email", createAddress(), "phone", "website", createCompany())
fun createGeo(): Geo = Geo("0.0", "0.0")
fun createAddress(): Address = Address("street", "suite", "city", "zipcode", createGeo())
fun createCompany(): Company = Company("name", "catchPhrase", "bs")

fun createPost(): Post = Post("1", "1", "title", "body")

fun createComment(): Comment = Comment("1", "1", "name", "email", "body")

fun createPostItem(): PostItem = PostItem("1", "1", "title", "body", "name", "username", "email")