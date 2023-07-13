package infinuma.android.shows.shows

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import infinuma.android.shows.R
import infinuma.android.shows.shows.adapter.ShowsAdapter
import infinuma.android.shows.shows.models.ShowsUi
import infinuma.android.shows.utils.makeStatusBarTransparent

class ShowsActivity : Activity() {

    private lateinit var showsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        setContentView(R.layout.activity_shows)
        showsRecyclerView = findViewById(R.id.showsRecyclerView)
        showsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ShowsActivity)
            adapter = ShowsAdapter(createShowUiList())
        }
    }

    private fun createShowUiList() =
        listOf(
            ShowsUi(
                1, R.drawable.ic_office, R.string.the_office_title
            ),
            ShowsUi(
                1, R.drawable.ic_stranger_things, R.string.stranger_things_title
            ),
            ShowsUi(
                1, R.drawable.ic_krv_nije_voda, R.string.krv_nije_voda_title,
            ),
            ShowsUi(
                1, R.drawable.ic_office, R.string.the_office_title
            ),
            ShowsUi(
                1, R.drawable.ic_krv_nije_voda, R.string.krv_nije_voda_title,
            ),
            ShowsUi(
                1, R.drawable.ic_stranger_things, R.string.stranger_things_title
            ),
            ShowsUi(
                1, R.drawable.ic_office, R.string.the_office_title
            ),
            ShowsUi(
                1, R.drawable.ic_stranger_things, R.string.stranger_things_title
            ),
            ShowsUi(
                1, R.drawable.ic_krv_nije_voda, R.string.krv_nije_voda_title,
            ),
            ShowsUi(
                1, R.drawable.ic_office, R.string.the_office_title
            ),
            ShowsUi(
                1, R.drawable.ic_krv_nije_voda, R.string.krv_nije_voda_title,
            ),
            ShowsUi(
                1, R.drawable.ic_stranger_things, R.string.stranger_things_title
            ),
            ShowsUi(
                1, R.drawable.ic_office, R.string.the_office_title
            ),
            ShowsUi(
                1, R.drawable.ic_stranger_things, R.string.stranger_things_title
            ),
            ShowsUi(
                1, R.drawable.ic_krv_nije_voda, R.string.krv_nije_voda_title,
            ),
            ShowsUi(
                1, R.drawable.ic_office, R.string.the_office_title
            ),
            ShowsUi(
                1, R.drawable.ic_krv_nije_voda, R.string.krv_nije_voda_title,
            ),
            ShowsUi(
                1, R.drawable.ic_stranger_things, R.string.stranger_things_title
            ),
        )
}