package com.patronus.deviceownership.device.control

import com.google.i18n.phonenumbers.PhoneNumberUtil
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class PhoneNumberConstraint : ConstraintValidator<PhoneNumberValidator, String> {

    private var phoneUtil = PhoneNumberUtil.getInstance()

    override fun initialize(phoneNumber: PhoneNumberValidator?) {}

    override fun isValid(phoneNumber: String?, cxt: ConstraintValidatorContext): Boolean {

        val parsedPhoneNumber = phoneUtil.parse(phoneNumber, "DE")

        return phoneUtil.isPossibleNumber(parsedPhoneNumber)
    }

}
