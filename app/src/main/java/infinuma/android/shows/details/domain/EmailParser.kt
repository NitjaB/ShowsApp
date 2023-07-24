package infinuma.android.shows.details.domain

object EmailParser {

    fun parseToUsername(email: String) =
        email.substringBefore("@")
}
