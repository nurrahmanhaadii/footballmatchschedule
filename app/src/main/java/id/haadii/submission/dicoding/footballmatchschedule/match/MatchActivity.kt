package id.haadii.submission.dicoding.footballmatchschedule.match

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = obtainViewModel()

        idLeague = intent.getStringExtra("id_league") as String

        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.matches -> {
                    replaceFragment(MatchFragment.newInstance(idLeague))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.favorites -> {
                    replaceFragment(FavoriteFragment.newInstance(idLeague))
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

        replaceFragment(MatchFragment.newInstance(idLeague))

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.contentFrame, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        search = menu?.findItem(R.id.search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search"

        search?.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                onBackPressed()
                bottom_nav.visibility = View.VISIBLE
                return true
            }

            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                contentFrame.visibility = View.VISIBLE
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
                    viewModel.loadDataTeam.value = true
                    viewModel.getSearchEvent(query)
                    viewModel.getSearchTeam(query)
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun obtainViewModel(): MatchViewModel {
        val factory = ViewModelFactory.getInstance(application)
        return ViewModelProviders.of(this, factory).get(MatchViewModel::class.java)
    }

}
