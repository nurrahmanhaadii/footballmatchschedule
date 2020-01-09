package id.haadii.submission.dicoding.footballmatchschedule.match


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.haadii.submission.dicoding.footballmatchschedule.R
import kotlinx.android.synthetic.main.fragment_match.*

/**
 * A simple [Fragment] subclass.
 */
class MatchFragment : Fragment() {

    companion object {
        fun newInstance(id: String): MatchFragment {
            val args = Bundle()
            args.putString("id_league", id)
            val fragment = MatchFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val idLeague = arguments?.getString("id_league") as String

        view_pager_main.adapter =
            MatchPagerAdapter(childFragmentManager, activity!!.applicationContext, idLeague)
        tab_layout.setupWithViewPager(view_pager_main)
    }
}
