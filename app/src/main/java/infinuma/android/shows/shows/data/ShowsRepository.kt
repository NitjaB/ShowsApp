package infinuma.android.shows.shows.data

import android.content.Context
import android.graphics.BitmapFactory
import infinuma.android.shows.R
import infinuma.android.shows.details.models.RatingUi
import infinuma.android.shows.details.models.ReviewUi
import infinuma.android.shows.shows.models.ShowCardUi

class ShowsRepository(
    private val context: Context
) {

    fun getShows() = listOf(
        ShowCardUi(
            "1",
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowCardUi(
            "2",
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowCardUi(
            "3",
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
        ShowCardUi(
            "4",
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowCardUi(
            "5",
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
        ShowCardUi(
            "6",
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowCardUi(
            "7",
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowCardUi(
            "8",
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowCardUi(
            "9",
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
        ShowCardUi(
            "10",
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowCardUi(
            "11",
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowCardUi(
            "12",
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
        ShowCardUi(
            "13",
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowCardUi(
            "14",
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowCardUi(
            "15",
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
        ShowCardUi(
            "16",
            R.drawable.ic_office,
            context.getString(R.string.the_office_title),
            context.getString(R.string.show_card_description)
        ),
        ShowCardUi(
            "17",
            R.drawable.ic_stranger_things,
            context.getString(R.string.stranger_things_title),
            context.getString(R.string.show_card_description)

        ),
        ShowCardUi(
            "18",
            R.drawable.ic_krv_nije_voda,
            context.getString(R.string.krv_nije_voda_title),
            context.getString(R.string.show_card_description)
        ),
    )

    fun getShow(id: String) = getShows().firstOrNull { show ->
        show.id == id
    }

    fun getReviews() = RatingUi(
        3,
        3.67f,
        listOf(
            ReviewUi(
                avatar = BitmapFactory.decodeResource(context.resources, R.drawable.ic_profile_picture),
                username = context.resources.getString(R.string.show_details_screen_reviews_username_1),
                starGrade = 3,
                review = context.resources.getString(R.string.show_details_screen_review_1)
            ),
            ReviewUi(
                avatar = BitmapFactory.decodeResource(context.resources, R.drawable.ic_profile_picture),
                username = context.resources.getString(R.string.show_details_screen_reviews_username_2),
                starGrade = 3,
                review = null
            ),
            ReviewUi(
                avatar = BitmapFactory.decodeResource(context.resources, R.drawable.ic_profile_picture),
                username = context.resources.getString(R.string.show_details_screen_reviews_username_3),
                starGrade = 3,
                review = context.resources.getString(R.string.show_details_screen_review_2)
            )
        )
    )
}
