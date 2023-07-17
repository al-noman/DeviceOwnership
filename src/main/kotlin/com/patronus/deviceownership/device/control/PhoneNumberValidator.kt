package com.patronus.deviceownership.device.control

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [PhoneNumberConstraint::class])
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.FIELD,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.PROPERTY_GETTER
)
@Retention(AnnotationRetention.RUNTIME)
annotation class PhoneNumberValidator(
    val message: String = "Invalid phone number",

    val groups: Array<KClass<*>> = [],

    val payload: Array<KClass<out Payload>> = []
)
