package id.haadii.submission.dicoding.footballmatchschedule.detailteam

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import id.haadii.submission.dicoding.footballmatchschedule.R
import id.haadii.submission.dicoding.footballmatchschedule.model.Team
import id.haadii.submission.dicoding.footballmatchschedule.util.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailTeamViewModel
    private lateinit var team: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        title = "Detail Team"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = obtainViewModel()
        team = intent.getParcelableExtra("team") as Team

        viewModel.checkFavorite(team)
        setView(team)
        pb_detail_team.visibility = View.GONE
    }

    private fun setView(team: Team) {
        Glide.with(this)
            .load(team.strTeamBadge)
            .into(iv_logo)
        tv_team_name.text = team.strTeam
        tv_formed_year_content.text = team.intFormedYear
        tv_stadium_content.text = team.strStadium
        tv_country_content.text = team.strCountry
        tv_website_content.text = team.strWebsite
        tv_description.text = team.strDescriptionEN
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
                    viewModel.removeFromFavorite(this, team.idTeam.toLong())
                    item.icon = resources.getDrawable(R.drawable.ic_unfavorite)
                } else {
                    viewModel.isFavorite = true
                    viewModel.insertFavTeam(team, this)
                    item.icon = resources.getDrawable(R.drawable.ic_favorite)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun obtainViewModel(): DetailTeamViewModel {
        val factory = ViewModelFactory.getInstance(application)
        return ViewModelProviders.of(this, factory).get(DetailTeamViewModel::class.java)
    }
}
