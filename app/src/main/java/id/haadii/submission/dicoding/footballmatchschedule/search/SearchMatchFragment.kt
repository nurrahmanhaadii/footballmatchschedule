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
import id.haadii.submission.dicoding.footballmatchschedule.detailmatch.DetailMatchActivity
import id.haadii.submission.dicoding.footballmatchschedule.match.MatchViewModel
import id.haadii.submission.dicoding.footballmatchschedule.model.Event
import id.haadii.submission.dicoding.footballmatchschedule.util.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_search_match.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class SearchMatchFragment : Fragment() {

    private lateinit var viewModel: MatchViewModel

    companion object {
        fun newInstance(id: String): SearchMatchFragment {
            val args = Bundle()
            args.putString("id_league", id)
            val fragment = SearchMatchFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = obtainViewModel()
        val idLeague = arguments?.getString("id_league") as String

        viewModel.loadData.observe(this, androidx.lifecycle.Observer { loadData ->
            if (loadData) {
                pb_search_match.visibility = View.VISIBLE
            } else {
                pb_search_match.visibility = View.GONE
            }
        })

        rv_search_match.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity!!)
        }

        viewModel.setSearchEvent().observe(this, androidx.lifecycle.Observer {
            if (it.isNotEmpty()) {
                val list = it.filter { event ->
                    event.strSport == "Soccer" &&
                            event.idLeague == idLeague
                } as ArrayList<Event>

                if (list.isNotEmpty()) {
                    val searchAdapter = SearchAdapter(list) { event ->
                        val intent = Intent(activity, DetailMatchActivity::class.java)
                        intent.putExtra("event", event)
                        startActivity(intent)
                    }
                    tv_no_result_match.visibility = View.GONE
                    rv_search_match.visibility = View.VISIBLE
                    rv_search_match.adapter = searchAdapter

                } else {
                    rv_search_match.visibility = View.GONE
                    tv_no_result_match.visibility = View.VISIBLE
                }
                viewModel.loadData.value = false
            } else {
                viewModel.loadData.value = false
                rv_search_match.visibility = View.GONE
                tv_no_result_match.visibility = View.VISIBLE
            }
        })
    }

    private fun obtainViewModel(): MatchViewModel {
        val factory = ViewModelFactory.getInstance(activity!!.application)
        return ViewModelProviders.of(Objects.requireNonNull(activity!!), factory)
            .get(MatchViewModel::class.java)
    }

}
