package id.haadii.submission.dicoding.footballmatchschedule.detailMatch

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import id.haadii.submission.dicoding.footballmatchschedule.R
import id.haadii.submission.dicoding.footballmatchschedule.model.Event
import id.haadii.submission.dicoding.footballmatchschedule.util.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_match.*

class DetailMatchActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailMatchViewModel
    private lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        title = "Detail Match"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = obtainViewModel()
        event = intent.getParcelableExtra("event") as Event

        viewModel.checkFavorite(event)
        setView(event)

    }

    private fun setView(it: Event) {
        if (it.strBadgeAwayTeam != null) {
            Glide.with(this)
                .load(it.strBadgeAwayTeam!!)
                .into(iv_team_away)
        }
        if (it.strBadgeHomeTeam != null) {
            Glide.with(this)
                .load(it.strBadgeHomeTeam!!)
                .into(iv_team_home)
        }
        if (it.intHomeScore != null) {
            tv_score_home.text = it.intHomeScore
        }
        if (it.intAwayScore != null) {
            tv_score_away.text = it.intAwayScore
        }
        if (it.strHomeLineupForward != null) {
            home_line_up.text = it.strHomeLineupForward
        }
        if (it.strHomeGoalDetails != null) {
            home_goal.text = it.strHomeGoalDetails
        }
        if (it.strHomeLineupGoalkeeper != null) {
            home_goal_keeper.text = it.strHomeLineupGoalkeeper
        }
        if (it.strHomeRedCards != null) {
            home_red_card.text = it.strHomeRedCards
        }
        if (it.strHomeYellowCards != null) {
            home_yellow_card.text = it.strHomeYellowCards
        }
        if (it.strAwayLineupForward != null) {
            away_line_up.text = it.strAwayLineupForward
        }
        if (it.strAwayGoalDetails != null) {
            away_goal.text = it.strAwayGoalDetails
        }
        if (it.strAwayLineupGoalkeeper != null) {
            away_goal_keeper.text = it.strAwayLineupGoalkeeper
        }
        if (it.strAwayRedCards != null) {
            away_red_card.text = it.strAwayRedCards
        }
        if (it.strAwayYellowCards != null) {
            away_yellow_card.text = it.strAwayYellowCards
        }
        tv_event_title.text = it.strEvent
        tv_event_date.text = it.dateEvent
        tv_team_away.text = it.strAwayTeam
        tv_team_home.text = it.strHomeTeam
        tv_home.text = it.strHomeTeam
        tv_away.text = it.strAwayTeam
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        if (viewModel.isFavorite) {
            menu?.findItem(R.id.favorite)?.icon = resources.getDrawable(R.drawable.ic_favorite)
        } else {
            menu?.findItem(R.id.favorite)?.icon = resources.getDrawable(R.drawable.ic_unfavorite)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                if (viewModel.isFavorite) {
                    viewModel.isFavorite = false
                    viewModel.removeFromFavorite(this, event.idEvent.toLong())
                    item.icon = resources.getDrawable(R.drawable.ic_unfavorite)
                } else {
                    viewModel.isFavorite = true
                    viewModel.insertFavMatch(event, this)
                    item.icon = resources.getDrawable(R.drawable.ic_favorite)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun obtainViewModel(): DetailMatchViewModel {
        val factory = ViewModelFactory.getInstance(application)
        return ViewModelProviders.of(this, factory).get(DetailMatchViewModel::class.java)
    }

}
