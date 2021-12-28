package com.firstapi.firstapi.user

import java.util.Date
import javax.validation.constraints.Past
import javax.validation.constraints.Size

data class User(
    var id: Int,
    @field:Size(min = 3, message = "Name should have at least 3 characters.")
    private val name: String,
    @field:Past(message = "birthDay should be a date from past.")
    val birthDate: Date
)
