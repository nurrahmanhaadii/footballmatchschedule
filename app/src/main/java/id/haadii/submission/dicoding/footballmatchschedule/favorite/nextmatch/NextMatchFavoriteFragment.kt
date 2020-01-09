package id.haadii.submission.dicoding.footballmatchschedule.favorite.nextmatch


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import id.haadii.submission.dicoding.footballmatchschedule.R
import id.haadii.submission.dicoding.footballmatchschedule.match.MatchAdapter
import id.haadii.submission.dicoding.footballmatchschedule.match.MatchViewModel
import id.haadii.submission.dicoding.footballmatchschedule.util.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_next_match_favorite.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFavoriteFragment : Fragment() {

    private lateinit var viewModel: MatchViewModel
    private lateinit var matchAdapter: MatchAdapter

    companion object {
        fun newInstance(id: String): NextMatchFavoriteFragment {
            val args = Bundle()
            args.putString("id_league", id)
            val fragment = NextMatchFavoriteFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = obtainViewModel(activity!!)

        val idLeague = arguments?.getString("id_league") as String

        if (viewModel.favList.isEmpty()) {
            viewModel.setFavoriteMatch(activity!!)
        }
        rv_favorites.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
        }

        viewModel.getFavoriteMatch().observe(this, Observer {
            val league = it.filter { event -> event.idLeague == idLeague && event.isNextMatch } as ArrayList
            rv_favorites.adapter = MatchAdapter(league) {

            }
        })

    }

    private fun obtainViewModel(activity: FragmentActivity): MatchViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(Objects.requireNonNull(activity), factory).get(MatchViewModel::class.java)
    }

}
