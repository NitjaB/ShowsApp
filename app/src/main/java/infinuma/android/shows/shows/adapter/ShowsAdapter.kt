package infinuma.android.shows.shows.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import infinuma.android.shows.databinding.ShowCardBinding
import infinuma.android.shows.shows.models.ShowCardUi
import infinuma.android.shows.utils.loadWithGlide

class ShowsAdapter(
    private val shows: MutableList<ShowCardUi>,
    private val onClickListener: (ShowCardUi) -> Unit
) : RecyclerView.Adapter<ShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val binding = ShowCardBinding.inflate(LayoutInflater.from(parent.context))
        return ShowViewHolder(binding, onClickListener)
    }

    override fun getItemCount() = shows.size

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(shows[position])
    }

    fun getShows() : List<ShowCardUi> = shows

    fun deleteShows() {
        shows.clear()
        notifyDataSetChanged()
    }

    fun addShows(shows: List<ShowCardUi>) {
        this.shows.addAll(shows)
        notifyDataSetChanged()
    }
}

class ShowViewHolder(
    private val binding: ShowCardBinding,
    private val clickListener: (ShowCardUi) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(show: ShowCardUi) {
        binding.showCardTitleTextView.text = show.name
        binding.showCardDescriptionTextView.text = show.description
        binding.showCardImageImageView.loadWithGlide(show.image)
        binding.root.setOnClickListener { clickListener(show) }
    }
}
