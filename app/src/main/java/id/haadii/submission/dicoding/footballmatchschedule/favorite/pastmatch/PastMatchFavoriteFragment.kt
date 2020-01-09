package id.haadii.submission.dicoding.footballmatchschedule.favorite.pastmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.haadii.submission.dicoding.footballmatchschedule.R
import id.haadii.submission.dicoding.footballmatchschedule.match.MatchAdapter
import id.haadii.submission.dicoding.footballmatchschedule.match.MatchViewModel
import id.haadii.submission.dicoding.footballmatchschedule.util.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_past_match_favorite.*
import java.util.*
import kotlin.collections.ArrayList

class PastMatchFavoriteFragment : Fragment() {

    private lateinit var viewModel: MatchViewModel

    companion object {
        fun newInstance(id: String): PastMatchFavoriteFragment {
            val args = Bundle()
            args.putString("id_league", id)
            val fragment = PastMatchFavoriteFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_past_match_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = obtainViewModel(activity!!)

        val idLeague = arguments?.getString("id_league") as String

        viewModel.getFavoriteMatch().observe(this, androidx.lifecycle.Observer {
            val league = it.filter { event -> event.idLeague == idLeague && !event.isNextMatch } as ArrayList

            rv_favorites_past_match.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                adapter = MatchAdapter(league) {

                }
            }
        })
    }

    private fun obtainViewModel(activity: FragmentActivity): MatchViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(Objects.requireNonNull(activity), factory).get(MatchViewModel::class.java)
    }
}
