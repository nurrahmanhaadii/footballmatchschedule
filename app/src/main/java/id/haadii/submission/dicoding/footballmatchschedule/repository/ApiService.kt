package id.haadii.submission.dicoding.footballmatchschedule.repository

import id.haadii.submission.dicoding.footballmatchschedule.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("lookupleague.php")
    fun detailLeague(@Query("id") id: String): Call<DetailLeague>

    @GET("eventsnextleague.php")
    fun nextMatch(@Query("id") id: String): Call<Match>

    @GET("eventspastleague.php")
    fun pastMatch(@Query("id") id: String): Call<Match>

    @GET("lookupteam.php")
    fun detailTeam(@Query("id") id: String): Call<DetailTeam>

    @GET("lookupevent.php")
    fun detailMatch(@Query("id") id: String): Call<Match>

    @GET("searchevents.php")
    fun searchEvent(@Query("e") value: String): Call<SearchResult>

    @GET("searchteams.php")
    fun searchTeam(@Query("t") value: String): Call<TeamList>

    @GET("lookup_all_teams.php")
    fun teamList(@Query("id") id: String): Call<TeamList>
}