package id.haadii.submission.dicoding.footballmatchschedule.repository

import android.os.Handler
import id.haadii.submission.dicoding.footballmatchschedule.model.*
import id.haadii.submission.dicoding.footballmatchschedule.util.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchRepository {
    fun getNextMatch(id: String, listener: (ArrayList<Event>?) -> Unit) {
        NetworkConfig.api().nextMatch(id).enqueue(object : Callback<Match> {
            override fun onResponse(call: Call<Match>, response: Response<Match>) {
                if (response.isSuccessful) {
                    val events = response.body()?.events
                    listener(events)
                }
            }

            override fun onFailure(call: Call<Match>, t: Throwable) {
            }
        })
    }

    fun getPastMatch(id: String, listener: (ArrayList<Event>?) -> Unit) {
        NetworkConfig.api().pastMatch(id).enqueue(object : Callback<Match> {
            override fun onResponse(call: Call<Match>, response: Response<Match>) {
                if (response.isSuccessful) {
                    val events = response.body()?.events
                    listener(events)
                }
            }

            override fun onFailure(call: Call<Match>, t: Throwable) {
            }
        })
    }

    fun getSearchEvent(query: String, listener: (ArrayList<Event>?) -> Unit) {
        NetworkConfig.api().searchEvent(query).enqueue(object : Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                if (response.isSuccessful) {
                    val events = response.body()?.event
                    listener(events)
                }
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
            }
        })
    }

    fun getSearchTeam(query: String, listener: (TeamList?) -> Unit) {
        NetworkConfig.api().searchTeam(query).enqueue(object : Callback<TeamList> {
            override fun onResponse(call: Call<TeamList>, response: Response<TeamList>) {
                if (response.isSuccessful) {
                    val teams = response.body()
                    listener(teams)
                }
            }

            override fun onFailure(call: Call<TeamList>, t: Throwable) {
            }
        })
    }

    fun getTeamList(id: String, listener: (ArrayList<Team>) -> Unit) {
        NetworkConfig.api().teamList(id).enqueue(object : Callback<TeamList> {
            override fun onResponse(call: Call<TeamList>, response: Response<TeamList>) {
                if (response.isSuccessful) {
                    val teams = response.body()
                    if (teams != null) {
                        listener(teams.teams)
                    }
                }
            }

            override fun onFailure(call: Call<TeamList>, t: Throwable) {

            }
        })
    }

    fun getDetailTeam(id: String, event: Event, isHomeTeam: Boolean, listener: (Event) -> Unit) {
        NetworkConfig.api().detailTeam(id).enqueue(object : Callback<DetailTeam> {
            override fun onResponse(call: Call<DetailTeam>, response: Response<DetailTeam>) {
                if (response.isSuccessful) {
                    val team = response.body()?.teams?.get(0) ?: Team()
                    if (isHomeTeam) {
                        event.strBadgeHomeTeam = team.strTeamBadge
                    } else {
                        event.strBadgeAwayTeam = team.strTeamBadge
                    }
                    listener(event)
                }
            }

            override fun onFailure(call: Call<DetailTeam>, t: Throwable) {
            }
        })
    }

    fun getDetailLeague(id: String, listener: (DetailLeague?) -> Unit) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed({
            NetworkConfig.api().detailLeague(id).enqueue(object : Callback<DetailLeague> {
                override fun onResponse(
                    call: Call<DetailLeague>,
                    response: Response<DetailLeague>
                ) {
                    if (response.isSuccessful) {
                        listener(response.body())
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<DetailLeague>, t: Throwable) {
                }
            })
        }, 5000)
    }
}