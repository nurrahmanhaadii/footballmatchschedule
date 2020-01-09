package id.haadii.submission.dicoding.footballmatchschedule.search


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.haadii.submission.dicoding.footballmatchschedule.R
import id.haadii.submission.dicoding.footballmatchschedule.detailteam.DetailTeamActivity
import id.haadii.submission.dicoding.footballmatchschedule.match.MatchViewModel
import id.haadii.submission.dicoding.footballmatchschedule.model.Team
import id.haadii.submission.dicoding.footballmatchschedule.team.TeamAdapter
import id.haadii.submission.dicoding.footballmatchschedule.util.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_search_team.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class SearchTeamFragment : Fragment() {

    private lateinit var viewModel: MatchViewModel

    companion object {
        fun newInstance(id: String): SearchTeamFragment {
            val args = Bundle()
            args.putString("id_league", id)
            val fragment = SearchTeamFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = obtainViewModel()
        val idLeague = arguments?.getString("id_league") as String

        viewModel.loadDataTeam.observe(this, androidx.lifecycle.Observer { loadData ->
            if (loadData) {
                pb_search_team.visibility = View.VISIBLE
            } else {
                pb_search_team.visibility = View.GONE
            }
        })

        rv_search_team.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity!!)
        }

        viewModel.setSearchTeam().observe(this, androidx.lifecycle.Observer {
            if (it.isNotEmpty() && it != null) {
                val list = it.filter { team ->
                    team.strSport == "Soccer" &&
                            team.idLeague == idLeague
                } as ArrayList<Team>

                if (list.isNotEmpty()) {
                    val searchAdapter = TeamAdapter(list) { team ->
                        val intent = Intent(activity, DetailTeamActivity::class.java)
                        intent.putExtra("team", team)
                        startActivity(intent)
                    }

                    tv_no_result_team.visibility = View.GONE
                    rv_search_team.visibility = View.VISIBLE
                    rv_search_team.adapter = searchAdapter
                } else {
                    rv_search_team.visibility = View.GONE
                    tv_no_result_team.visibility = View.VISIBLE
                }
                viewModel.loadDataTeam.value = false
            } else {
                viewModel.loadDataTeam.value = false
                rv_search_team.visibility = View.GONE
                tv_no_result_team.visibility = View.VISIBLE
            }
        })
    }

    private fun obtainViewModel(): MatchViewModel {
        val factory = ViewModelFactory.getInstance(activity!!.application)
        return ViewModelProviders.of(Objects.requireNonNull(activity!!), factory)
            .get(MatchViewModel::class.java)
    }

}
