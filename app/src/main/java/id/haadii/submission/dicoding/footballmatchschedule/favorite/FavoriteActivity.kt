package id.haadii.submission.dicoding.footballmatchschedule.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.haadii.submission.dicoding.footballmatchschedule.R
import id.haadii.submission.dicoding.footballmatchschedule.match.MatchActivity
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

//        view_pager_favorite.adapter = FavoritePagerAdapter(supportFragmentManager, this)
//        tab_favorite.setupWithViewPager(view_pager_favorite)

        setUpBottomNav()
    }

    private fun setUpBottomNav() {
        bottom_nav_favorite.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.matches -> {
                    val intent = Intent(this,MatchActivity::class.java)
                    startActivity(intent)
                }
                R.id.favorites -> {}
            }
            true
        }
    }

}
