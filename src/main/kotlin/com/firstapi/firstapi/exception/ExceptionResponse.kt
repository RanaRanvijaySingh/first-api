package com.firstapi.firstapi.exception

import java.util.Date

data class ExceptionResponse(
    val timestamp: Date,
    val message: String,
    val details: String
)