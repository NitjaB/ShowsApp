package infinuma.android.shows.login.domain.mappers

import infinuma.android.shows.login.domain.models.User
import infinuma.android.shows.network.models.RegisterUserUserResponse

class UserMapper {

    fun fromResources(response: RegisterUserUserResponse) =
        User(
            email = response.email,
            avatarUrl = response.avatarUrl ?: ""
        )
}
