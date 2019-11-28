package id.haadii.submission.dicoding.footballmatchschedule.match

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import id.haadii.submission.dicoding.footballmatchschedule.R
import id.haadii.submission.dicoding.footballmatchschedule.favorite.FavoriteFragment
import id.haadii.submission.dicoding.footballmatchschedule.search.SearchFragment
import id.haadii.submission.dicoding.footballmatchschedule.util.ViewModelFactory
import kotlinx.android.synthetic.main.activity_match.*

class MatchActivity : AppCompatActivity() {

    private lateinit var viewModel: MatchViewModel
    private lateinit var idLeague: String
    private var search: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        title = "Match"

        viewModel = obtainViewModel()

        idLeague = intent.getStringExtra("id_league") as String

        view_pager_main.adapter = MatchPagerAdapter(supportFragmentManager, this, idLeague)
        tab_layout.setupWithViewPager(view_pager_main)

        bottom_nav.setOnNavigationItemSelectedListener {
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            when (it.itemId) {
                R.id.matches -> {
                    view_pager_main.visibility = View.VISIBLE
                    contentFrame.visibility = View.GONE
                    search?.isEnabled = true
                }
                R.id.favorites -> {
                    view_pager_main.visibility = View.GONE
                    contentFrame.visibility = View.VISIBLE
                    search?.isEnabled = false
                    transaction.replace(R.id.contentFrame, FavoriteFragment.newInstance(idLeague))
                        .commit()
                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        search = menu?.findItem(R.id.search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search"

        search?.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                onBackPressed()
                contentFrame.visibility = View.GONE
                view_pager_main.visibility = View.VISIBLE
                bottom_nav.visibility = View.VISIBLE
                return true
            }

            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                contentFrame.visibility = View.VISIBLE
                view_pager_main.visibility = View.GONE
                bottom_nav.visibility = View.GONE
                val manager = supportFragmentManager
                val transaction = manager.beginTransaction()
                transaction.replace(R.id.contentFrame, SearchFragment.newInstance(idLeague))
                    .addToBackStack(null)
                    .commit()
                return true
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    if (viewModel.setSearchEvent().value != null) {
                        viewModel.setSearchEvent().value!!.clear()
                    }
                    viewModel.loadData.value = true
                    viewModel.getSearchEvent(query)
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun obtainViewModel(): MatchViewModel {
        val factory = ViewModelFactory.getInstance(application)
        return ViewModelProviders.of(this, factory).get(MatchViewModel::class.java)
    }

}
