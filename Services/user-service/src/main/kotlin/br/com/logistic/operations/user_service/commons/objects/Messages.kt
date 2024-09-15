package br.com.logistic.operations.user_service.commons.objects

object Messages {

    object Errors{
        const val USED_EMAIL = "[SERVICE][VALIDATION] - This email (%s) is already used by someone else"
        const val INVALID_CATEGORY = "[SERVICE][VALIDATION] - Category (%s) is not a valid Category"
        const val INVALID_USER_ID = "[SERVICE][VALIDATION] - Do not have user with User Id (%s) "
    }

    object Logs{}
}