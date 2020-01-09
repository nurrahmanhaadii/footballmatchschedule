package id.haadii.submission.dicoding.footballmatchschedule.favorite.team


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import id.haadii.submission.dicoding.footballmatchschedule.R
import id.haadii.submission.dicoding.footballmatchschedule.match.MatchViewModel
import id.haadii.submission.dicoding.footballmatchschedule.team.TeamAdapter
import id.haadii.submission.dicoding.footballmatchschedule.util.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_team_favorite.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class TeamFavoriteFragment : Fragment() {

    private lateinit var viewModel: MatchViewModel

    companion object {
        fun newInstance(id: String) : TeamFavoriteFragment {
            val args = Bundle()
            args.putString("id_league", id)
            val fragment = TeamFavoriteFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = obtainViewModel(activity!!)

        val idLeague = arguments?.getString("id_league") as String

        if (viewModel.favTeamList.isEmpty()) {
            viewModel.setFavoriteTeam(activity!!)
        }

        viewModel.getFavoriteTeam().observe(this, androidx.lifecycle.Observer {
            val team = it.filter { team -> team.idLeague == idLeague } as ArrayList
            rv_favorites_team.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                adapter = TeamAdapter(team) {

                }
            }
        })
    }

    private fun obtainViewModel(activity: FragmentActivity): MatchViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(Objects.requireNonNull(activity), factory).get(MatchViewModel::class.java)
    }

}
