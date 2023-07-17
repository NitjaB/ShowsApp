package infinuma.android.shows.shows.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import infinuma.android.shows.databinding.ShowCardBinding
import infinuma.android.shows.shows.models.ShowUi

class ShowsAdapter(
    private val shows: MutableList<ShowUi>,
    private val onClickListener: (ShowUi) -> Unit
) : RecyclerView.Adapter<ShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val binding = ShowCardBinding.inflate(LayoutInflater.from(parent.context))
        return ShowViewHolder(binding, onClickListener)
    }

    override fun getItemCount() = shows.size

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(shows[position])
    }

    fun getShows() : List<ShowUi> = shows

    fun deleteShows() {
        shows.clear()
        notifyDataSetChanged()
    }

    fun addShows(shows: List<ShowUi>) {
        this.shows.addAll(shows)
        notifyDataSetChanged()
    }
}

class ShowViewHolder(
    private val binding: ShowCardBinding,
    private val clickListener: (ShowUi) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(show: ShowUi) {
        binding.showCardTitleTextView.text = show.name
        binding.showCardDescriptionTextView.text = show.description
        binding.showCardImageImageView.setImageResource(show.image)
        binding.root.setOnClickListener { clickListener(show) }
    }
}
