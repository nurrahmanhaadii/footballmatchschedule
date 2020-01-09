package id.haadii.submission.dicoding.footballmatchschedule.team


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.haadii.submission.dicoding.footballmatchschedule.R
import id.haadii.submission.dicoding.footballmatchschedule.detailteam.DetailTeamActivity
import id.haadii.submission.dicoding.footballmatchschedule.match.MatchViewModel
import id.haadii.submission.dicoding.footballmatchschedule.util.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_team.*

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment() {

    private lateinit var viewModel: MatchViewModel

    companion object {
        fun newInstance(id: String): TeamFragment {
            val args = Bundle()
            args.putString("id_league", id)
            val fragment = TeamFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = obtainViewModel(activity!!)

        val idLeague = arguments?.getString("id_league") as String
        viewModel.getTeamList(idLeague)

        rv_team.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
        }

        viewModel.setTeamList().observe(this, Observer {
            if (it.isNotEmpty()) {
                rv_team.adapter = TeamAdapter(it) { team ->
                    val intent = Intent(activity, DetailTeamActivity::class.java)
                    intent.putExtra("team", team)
                    startActivity(intent)
                }
            }
            pb_team.visibility = View.GONE
        })
    }

    private fun obtainViewModel(activity: FragmentActivity): MatchViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(this, factory).get(MatchViewModel::class.java)
    }
}
