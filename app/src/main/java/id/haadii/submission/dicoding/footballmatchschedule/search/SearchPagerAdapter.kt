package id.haadii.submission.dicoding.footballmatchschedule.search

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.haadii.submission.dicoding.footballmatchschedule.R

class SearchPagerAdapter(
    fragment: FragmentManager,
    private val context: Context,
    private val idLeague: String
) : FragmentPagerAdapter(fragment) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SearchMatchFragment.newInstance(idLeague)
            else -> SearchTeamFragment.newInstance(idLeague)
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.match_title)
            else -> context.getString(R.string.team_title)
        }
    }
}