package id.haadii.submission.dicoding.footballmatchschedule.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.haadii.submission.dicoding.footballmatchschedule.R
import id.haadii.submission.dicoding.footballmatchschedule.model.Event
import kotlinx.android.synthetic.main.item_match.view.*

class SearchAdapter(private val items: ArrayList<Event>, private val listener: (Event) -> Unit) :
    RecyclerView.Adapter<SearchAdapter.SearchAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterViewHolder {
        return SearchAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_match,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SearchAdapterViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    inner class SearchAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Event, listener: (Event) -> Unit) {
            if (item.strBadgeHomeTeam != null) {
                Glide.with(itemView.context)
                    .load(item.strBadgeHomeTeam)
                    .into(itemView.iv_team_home)
            }
            if (item.strBadgeAwayTeam != null) {
                Glide.with(itemView.context)
                    .load(item.strBadgeAwayTeam)
                    .into(itemView.iv_team_away)
            }
            if (item.intAwayScore == null) {
                itemView.tv_score_away.text = "-"
            } else {
                itemView.tv_score_away.text = item.intAwayScore
            }
            if (item.intHomeScore == null) {
                itemView.tv_score_home.text = "-"
            } else {
                itemView.tv_score_home.text = item.intHomeScore
            }
            itemView.tv_event_title.text = item.strEvent
            itemView.tv_event_date.text = item.dateEvent
            itemView.tv_team_home.text = item.strHomeTeam
            itemView.tv_team_away.text = item.strAwayTeam
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }
}