package id.haadii.submission.dicoding.footballmatchschedule.favorite


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
import id.haadii.submission.dicoding.footballmatchschedule.detailMatch.DetailMatchActivity
import id.haadii.submission.dicoding.footballmatchschedule.match.MatchAdapter
import id.haadii.submission.dicoding.footballmatchschedule.match.MatchViewModel
import id.haadii.submission.dicoding.footballmatchschedule.util.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite.*

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

        viewModel.setFavoriteMatch(activity!!)
        viewModel.getFavoriteMatch().observe(this, Observer {
            val league = it.filter { event -> event.idLeague == idLeague } as ArrayList
            rv_favorites.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                adapter = MatchAdapter(league) { event ->
                    val intent = Intent(activity, DetailMatchActivity::class.java)
                    intent.putExtra("event", event)
                    startActivity(intent)
                }
            }
        })
    }

    private fun obtainViewModel(activity: FragmentActivity): MatchViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(this, factory).get(MatchViewModel::class.java)

    }
}
