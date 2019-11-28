package id.haadii.submission.dicoding.footballmatchschedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.haadii.submission.dicoding.footballmatchschedule.model.Football
import kotlinx.android.synthetic.main.item_league.view.*
import java.util.*

class LeagueAdapter(
    private val items: ArrayList<Football>,
    private val listener: (Football) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.LeagueAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueAdapterViewHolder {
        return LeagueAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_league,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LeagueAdapterViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class LeagueAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Football, listener: (Football) -> Unit) {
            itemView.tv_league.text = item.name
            Glide.with(itemView.context)
                .load(item.image)
                .into(itemView.iv_league)
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }
}