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
import infinuma.android.shows.shows.data.ShowsRepository
import infinuma.android.shows.utils.makeStatusBarTransparent

class ShowsActivity : Activity() {

    private lateinit var binding: ActivityShowsBinding

    private val repository = ShowsRepository()

    private val adapter = ShowsAdapter(ArrayList(repository.getShows()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        binding = ActivityShowsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        setUpToggleButton()
    }

    private fun setUpRecyclerView() {
        binding.showsRecyclerView.apply {
            val manager = LinearLayoutManager(this@ShowsActivity)
            val decoration = DividerItemDecoration(this.context, manager.orientation).apply {
                setDrawable(ContextCompat.getDrawable(this@ShowsActivity, R.drawable.show_divider)!!)
            }
            this.layoutManager = manager
            this.adapter = this@ShowsActivity.adapter
            this.addItemDecoration(decoration)
        }
    }

    private fun setUpToggleButton() {
        binding.toggleShowList.setOnClickListener {
            if (adapter.getShows().isEmpty()) {
                adapter.addShows(repository.getShows())
                binding.toggleShowList.icon = ContextCompat.getDrawable(this@ShowsActivity, R.drawable.ic_trash_can)
                hideNoShowViews()
            } else {
                adapter.deleteShows()
                binding.toggleShowList.icon = ContextCompat.getDrawable(this@ShowsActivity, R.drawable.ic_add)
                showNoShowsViews()
            }
        }
    }

    private fun showNoShowsViews() {
        binding.noShowsImageView.visibility = View.VISIBLE
        binding.noShowsTextView.visibility = View.VISIBLE
    }

    private fun hideNoShowViews() {
        binding.noShowsImageView.visibility = View.GONE
        binding.noShowsTextView.visibility = View.GONE
    }
}