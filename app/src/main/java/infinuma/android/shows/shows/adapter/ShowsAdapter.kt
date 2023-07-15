package infinuma.android.shows.shows.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import infinuma.android.shows.databinding.ShowCardBinding
import infinuma.android.shows.shows.models.ShowsUi

class ShowsAdapter(
    private val shows: List<ShowsUi>
) : RecyclerView.Adapter<ShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val binding = ShowCardBinding.inflate(LayoutInflater.from(parent.context))
        return ShowViewHolder(binding)
    }

    override fun getItemCount() = shows.size

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(shows[position])
    }
}

class ShowViewHolder(private val binding: ShowCardBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(show: ShowsUi) {
        binding.showCardTitleTextView.text = itemView.context.getString(show.title)
        binding.showCardImageImageView.setImageResource(show.image)
    }
}
