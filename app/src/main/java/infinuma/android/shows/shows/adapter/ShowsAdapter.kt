package infinuma.android.shows.shows.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import infinuma.android.shows.R
import infinuma.android.shows.shows.models.ShowsUi

class ShowsAdapter(
    private val shows: List<ShowsUi>
) : RecyclerView.Adapter<ShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ShowViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.show_card, parent, false)
        )

    override fun getItemCount() = shows.size

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(shows[position])
    }
}

class ShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val title: TextView = view.findViewById(R.id.showCardTitleTextView)
    private val image: ImageView = view.findViewById(R.id.showCardImageImageView)

    fun bind(show: ShowsUi) {
        title.text = itemView.context.getString(show.title)
        image.setImageResource(show.image)
    }
}
