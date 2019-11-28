package id.haadii.submission.dicoding.footballmatchschedule

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import id.haadii.submission.dicoding.footballmatchschedule.detailLeague.DetailLeagueActivity
import id.haadii.submission.dicoding.footballmatchschedule.repository.DataDummy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = getString(R.string.football_match_title)

        rv_league.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = LeagueAdapter(DataDummy().footballData()) {
                val intent = Intent(this@MainActivity, DetailLeagueActivity::class.java)
                intent.putExtra("id", it.id)
                startActivity(intent)
            }
        }
    }
}