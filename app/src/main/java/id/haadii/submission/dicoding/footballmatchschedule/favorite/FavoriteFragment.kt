package id.haadii.submission.dicoding.footballmatchschedule.favorite


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import id.haadii.submission.dicoding.footballmatchschedule.R
import id.haadii.submission.dicoding.footballmatchschedule.match.MatchViewModel
import id.haadii.submission.dicoding.footballmatchschedule.util.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite.tab_favorite
import kotlinx.android.synthetic.main.fragment_favorite.view_pager_favorite
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    private lateinit var viewModel: MatchViewModel

    companion object {
        fun newInstance(id: String): FavoriteFragment {
            val args = Bundle()
            args.putString("id_league", id)
            val fragment = FavoriteFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = obtainViewModel(activity!!)

        val idLeague = arguments?.getString("id_league") as String

//        viewModel.setFavoriteMatch(activity!!)

        view_pager_favorite.adapter = FavoritePagerAdapter(childFragmentManager, activity!!, idLeague)
        tab_favorite.setupWithViewPager(view_pager_favorite)

    }

    private fun obtainViewModel(activity: FragmentActivity): MatchViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(this, factory).get(MatchViewModel::class.java)

    }
}
