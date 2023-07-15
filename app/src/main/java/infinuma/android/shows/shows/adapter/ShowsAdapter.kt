package infinuma.android.shows.shows.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import infinuma.android.shows.databinding.ShowCardBinding
import infinuma.android.shows.shows.models.ShowsUi

class ShowsAdapter(
    private val shows: ArrayList<ShowsUi>,
    private val onClickListener: (ShowsUi) -> Unit
) : RecyclerView.Adapter<ShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val binding = ShowCardBinding.inflate(LayoutInflater.from(parent.context))
        return ShowViewHolder(binding, onClickListener)
    }

    override fun getItemCount() = shows.size

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(shows[position])
    }

    fun getShows() : List<ShowsUi> = shows

    fun deleteShows() {
        shows.clear()
        notifyDataSetChanged()
    }

    fun addShows(shows: List<ShowsUi>) {
        this.shows.addAll(shows)
        notifyDataSetChanged()
    }
}

class ShowViewHolder(
    private val binding: ShowCardBinding,
    private val clickListener: (ShowsUi) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(show: ShowsUi) {
        binding.showCardTitleTextView.text = show.title
        binding.showCardDescriptionTextView.text = show.description
        binding.showCardImageImageView.setImageResource(show.image)
        binding.root.setOnClickListener { clickListener(show) }
    }
}
