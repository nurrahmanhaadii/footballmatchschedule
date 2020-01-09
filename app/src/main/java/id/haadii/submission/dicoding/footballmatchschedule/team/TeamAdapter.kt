package id.haadii.submission.dicoding.footballmatchschedule.team

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.haadii.submission.dicoding.footballmatchschedule.R
import id.haadii.submission.dicoding.footballmatchschedule.model.Team
import kotlinx.android.synthetic.main.item_team.view.*

class TeamAdapter(private val teams: ArrayList<Team>, private val listener: (Team) -> Unit) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return TeamViewHolder(view)
    }

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teams[position], listener)
    }

    inner class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(team: Team, listener: (Team) -> Unit) {
            Glide.with(itemView.context)
                .load(team.strTeamBadge)
                .into(itemView.iv_team)
            itemView.tv_team_name.text = team.strTeam
            itemView.tv_team_desc.text = team.strDescriptionEN

            itemView.setOnClickListener { listener(team) }
        }
    }
}