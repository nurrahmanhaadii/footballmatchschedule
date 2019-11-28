package id.haadii.submission.dicoding.footballmatchschedule.detailLeague

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import id.haadii.submission.dicoding.footballmatchschedule.R
import id.haadii.submission.dicoding.footballmatchschedule.match.MatchActivity
import id.haadii.submission.dicoding.footballmatchschedule.model.League
import id.haadii.submission.dicoding.footballmatchschedule.util.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_league.*

class DetailLeagueActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)

        title = getString(R.string.detail_league_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = obtainViewModel()

        val id = intent.getIntExtra("id", 0)

        viewModel.getDetailLeague(id.toString())

        viewModel.setDetailLeague().observe(this, Observer {
            if (it.leagues.isNotEmpty()) {
                pb_detail_league.visibility = View.GONE
                btn_see_match.visibility = View.VISIBLE

                setView(it.leagues[0])
            }
        })
    }

    private fun setView(leagues: League) {
        Glide.with(this)
            .load(leagues.strBanner)
            .into(iv_banner)

        Glide.with(this)
            .load(leagues.strBadge)
            .into(iv_logo)

        tv_league_name.text = leagues.strLeague
        tv_country_content.text = leagues.strCountry
        tv_first_event_content.text = leagues.dateFirstEvent
        tv_formed_year_content.text = leagues.intFormedYear
        tv_website_content.text = leagues.strWebsite
        tv_description.text = leagues.strDescriptionEN

        btn_see_match.setOnClickListener {
            clickMatch(leagues.idLeague)
        }
    }

    private fun clickMatch(id: String) {
        val intent = Intent(this, MatchActivity::class.java)
        intent.putExtra("id_league", id)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun obtainViewModel(): DetailViewModel {
        val factory = ViewModelFactory.getInstance(application)
        return ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
    }
}
