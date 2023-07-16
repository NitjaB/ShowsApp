package infinuma.android.shows.details

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import infinuma.android.shows.databinding.ActivityShowDetailsBinding

class ShowDetailsActivity : Activity() {

    companion object {

        private const val SHOW_ID = "SHOW_ID"

        fun startActivity(context: Context, showId: String) {
            context.startActivity(Intent(context, ShowDetailsActivity::class.java).apply {
                putExtra(SHOW_ID, showId)
            })
        }
    }

    private lateinit var binding: ActivityShowDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun getShowIdExtra() =
        intent.getStringExtra(SHOW_ID)
}
