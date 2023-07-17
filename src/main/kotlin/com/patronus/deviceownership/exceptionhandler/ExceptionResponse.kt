package com.patronus.deviceownership.exceptionhandler

import java.time.LocalDate

data class ExceptionResponse(val date: LocalDate, val message: String, val details: String)
