package infinuma.android.shows.shows.data

import android.content.Context
import infinuma.android.shows.R
import infinuma.android.shows.shows.models.ShowUi

class ShowsRepository {

    fun getShows(context: Context) = listOf(
        ShowUi(
            "1",
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowUi(
            "2",
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowUi(
            "3",
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
        ShowUi(
            "4",
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowUi(
            "5",
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
        ShowUi(
            "6",
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowUi(
            "7",
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowUi(
            "8",
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowUi(
            "9",
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
        ShowUi(
            "10",
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowUi(
            "11",
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowUi(
            "12",
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
        ShowUi(
            "13",
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowUi(
            "14",
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowUi(
            "15",
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
        ShowUi(
            "16",
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowUi(
            "17",
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowUi(
            "18",
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
    )

    fun getShow(id: String, context: Context) = getShows(context).firstOrNull { show ->
        show.id == id
    }
}
