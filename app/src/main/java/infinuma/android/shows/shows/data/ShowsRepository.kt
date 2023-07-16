package infinuma.android.shows.shows.data

import android.content.Context
import infinuma.android.shows.R
import infinuma.android.shows.shows.models.ShowsUi

class ShowsRepository {

    fun getShows(context: Context) = listOf(
        ShowsUi(
            1,
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowsUi(
            2,
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowsUi(
            3,
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
        ShowsUi(
            4,
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowsUi(
            5,
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
        ShowsUi(
            6,
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowsUi(
            7,
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowsUi(
            8,
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowsUi(
            9,
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
        ShowsUi(
            10,
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowsUi(
            11,
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowsUi(
            12,
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
        ShowsUi(
            13,
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowsUi(
            14,
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowsUi(
            15,
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
        ShowsUi(
            16,
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowsUi(
            17,
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowsUi(
            18,
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
    )

    fun getShow(id: Int, context: Context) = getShows(context).firstOrNull { show ->
        show.id == id
    }
}
