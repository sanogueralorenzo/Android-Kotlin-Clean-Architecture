package com.sanogueralorenzo.domain

import com.sanogueralorenzo.domain.model.*

fun createUser(): User = User("1", "name", "username", "email", createAddress(), "phone", "website", createCompany())
fun createGeo(): Geo = Geo("0.0", "0.0")
fun createAddress(): Address = Address("street", "suite", "city", "zipcode", createGeo())
fun createCompany(): Company = Company("name", "catchPhrase", "bs")

fun createPost(): Post = Post("1", "1", "title", "body")

fun createComment(): Comment = Comment("1", "1", "name", "email", "body")