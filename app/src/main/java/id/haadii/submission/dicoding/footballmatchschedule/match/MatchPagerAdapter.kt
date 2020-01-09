package id.haadii.submission.dicoding.footballmatchschedule.match

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.haadii.submission.dicoding.footballmatchschedule.R
import id.haadii.submission.dicoding.footballmatchschedule.match.nextmatch.NextMatchFragment
import id.haadii.submission.dicoding.footballmatchschedule.match.pastmatch.PreviousMatchFragment
import id.haadii.submission.dicoding.footballmatchschedule.team.TeamFragment

class MatchPagerAdapter(
    fragmentManager: FragmentManager,
    private val context: Context,
    private val idLeague: String
) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                NextMatchFragment.newInstance(idLeague)
            }
            1 -> {
                return PreviousMatchFragment.newInstance(idLeague)
            }
            else -> {
                return TeamFragment.newInstance(idLeague)
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                context.getString(R.string.next_match)
            }
            1 -> {
                context.getString(R.string.previous_match)
            }
            else -> context.getString(R.string.team)
        }
    }

}