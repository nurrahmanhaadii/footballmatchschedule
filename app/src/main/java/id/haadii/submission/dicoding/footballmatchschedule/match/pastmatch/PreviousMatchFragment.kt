package id.haadii.submission.dicoding.footballmatchschedule.match.pastmatch


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
import id.haadii.submission.dicoding.footballmatchschedule.detailmatch.DetailMatchActivity
import id.haadii.submission.dicoding.footballmatchschedule.match.MatchAdapter
import id.haadii.submission.dicoding.footballmatchschedule.match.MatchViewModel
import id.haadii.submission.dicoding.footballmatchschedule.util.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_previous_match.*

/**
 * A simple [Fragment] subclass.
 */
class PreviousMatchFragment : Fragment() {

    private lateinit var viewModel: MatchViewModel

    companion object {
        fun newInstance(id: String): PreviousMatchFragment {
            val args = Bundle()
            args.putString("id_league", id)
            val fragment = PreviousMatchFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_previous_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = obtainViewModel(activity!!)

        val idLeague = arguments?.getString("id_league") as String
        if (viewModel.pastList.isEmpty()) {
            viewModel.getPastMatch(idLeague)
        }

        viewModel.setPastMatch().observe(this, Observer {
            if (it.isNotEmpty()) {
                rv_past_match.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(activity!!)
                    adapter =
                        MatchAdapter(it) { event ->
                            val intent = Intent(activity, DetailMatchActivity::class.java)
                            intent.putExtra("id_event", event.idEvent)
                            intent.putExtra("event", event)
                            intent.putExtra("isNextMatch", false)
                            startActivity(intent)
                        }
                }
                pb_past_match.visibility = View.GONE
            } else {
                pb_past_match.visibility = View.GONE
                empty_past_match.visibility = View.VISIBLE
            }
        })
    }

    private fun obtainViewModel(activity: FragmentActivity): MatchViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(this, factory).get(MatchViewModel::class.java)
    }

}
