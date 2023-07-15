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

    private val adapter = ShowsAdapter(createShowUiList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        binding = ActivityShowsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.showsRecyclerView.apply {
            val manager = LinearLayoutManager(this@ShowsActivity)
            val decoration = DividerItemDecoration(this.context, manager.orientation).apply {
                setDrawable(ContextCompat.getDrawable(this@ShowsActivity, R.drawable.show_divider)!!)
            }
            this.layoutManager = manager
            this.adapter = this@ShowsActivity.adapter
            this.addItemDecoration(decoration)
        }
        binding.toggleShowList.setOnClickListener {
            if(adapter.getShows().isEmpty()) {
                adapter.addShows(createShowUiList())
                binding.toggleShowList.icon = ContextCompat.getDrawable(this@ShowsActivity, R.drawable.ic_trash_can)
                binding.noShowsImageView.visibility = View.GONE
                binding.noShowsTextView.visibility = View.GONE
            } else {
                adapter.deleteShows()
                binding.noShowsImageView.visibility = View.VISIBLE
                binding.noShowsTextView.visibility = View.VISIBLE
                binding.toggleShowList.icon = ContextCompat.getDrawable(this@ShowsActivity, R.drawable.ic_add)
            }
        }
    }

    private fun createShowUiList() =
        arrayListOf(
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

    private fun createEmptyShowList() = arrayListOf<ShowsUi>()
}