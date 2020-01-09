package id.haadii.submission.dicoding.footballmatchschedule.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import id.haadii.submission.dicoding.footballmatchschedule.R
import id.haadii.submission.dicoding.footballmatchschedule.match.MatchViewModel
import id.haadii.submission.dicoding.footballmatchschedule.util.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_search_event.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    private lateinit var viewModel: MatchViewModel

    companion object {
        fun newInstance(id: String): SearchFragment {
            val args = Bundle()
            args.putString("id_league", id)
            val fragment = SearchFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_event, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = obtainViewModel()
        val idLeague = arguments?.getString("id_league") as String

        view_pager_search.adapter =
            SearchPagerAdapter(childFragmentManager, activity!!.applicationContext, idLeague)
        tab_layout_search.setupWithViewPager(view_pager_search)

    }

    private fun obtainViewModel(): MatchViewModel {
        val factory = ViewModelFactory.getInstance(activity!!.application)
        return ViewModelProviders.of(Objects.requireNonNull(activity!!), factory)
            .get(MatchViewModel::class.java)
    }

}
