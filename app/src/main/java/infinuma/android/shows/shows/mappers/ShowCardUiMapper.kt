package infinuma.android.shows.shows.mappers

import infinuma.android.shows.shows.domain.models.ShowInfo
import infinuma.android.shows.shows.models.ShowCardUi

class ShowCardUiMapper {

    fun mapFromDomain(domain: List<ShowInfo>): List<ShowCardUi> {
        val showCards = mutableListOf<ShowCardUi>()
        domain.forEach {
            showCards.add(
                ShowCardUi(
                    id = it.id.toString(),
                    imageUrl = it.imageUrl,
                    name = it.title,
                    description = it.description
                )
            )
        }
        return showCards
    }
}