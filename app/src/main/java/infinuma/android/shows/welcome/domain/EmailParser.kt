package infinuma.android.shows.welcome.domain

class EmailParser {

    fun toUsername(email: String) = email.substringBefore("@")

}
