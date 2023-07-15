package infinuma.android.shows.shows

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import infinuma.android.shows.R
import infinuma.android.shows.databinding.ActivityShowsBinding
import infinuma.android.shows.shows.adapter.ShowsAdapter
import infinuma.android.shows.shows.models.ShowsUi
import infinuma.android.shows.utils.makeStatusBarTransparent

class ShowsActivity : Activity() {

    private lateinit var binding: ActivityShowsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        binding = ActivityShowsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val shows = createShowUiList()
        binding.showsRecyclerView.apply {
            val manager = LinearLayoutManager(this@ShowsActivity)
            val adapter = ShowsAdapter(shows)
            val decoration = DividerItemDecoration(this.context, manager.orientation).apply {
                setDrawable(ContextCompat.getDrawable(this@ShowsActivity, R.drawable.show_divider)!!)
            }
            this.layoutManager = manager
            this.adapter = adapter
            this.addItemDecoration(decoration)
            if (shows.isEmpty()) {
                binding.noShowsImageView.visibility = View.VISIBLE
                binding.noShowsTextView.visibility = View.VISIBLE
            }
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

    private fun createEmptyShowList() = listOf<ShowsUi>()
}