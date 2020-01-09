package id.haadii.submission.dicoding.footballmatchschedule.detailteam

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import id.haadii.submission.dicoding.footballmatchschedule.helper.database
import id.haadii.submission.dicoding.footballmatchschedule.model.Team
import id.haadii.submission.dicoding.footballmatchschedule.model.TeamFavorite
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailTeamViewModel (application: Application) : AndroidViewModel(application) {

    private val favList = ArrayList<Team>()
    var isFavorite: Boolean = false

    init {
        setFavoriteTeam(application)
    }

    private fun setFavoriteTeam(context: Context) {
        context.database.use{
            val result = select(TeamFavorite.TABLE_TEAM_FAVORITE)
            val favorite = result.parseList(classParser<TeamFavorite>())
            setTeam(favorite)
        }
    }

    private fun setTeam(list: List<TeamFavorite>) {
        for (favorite in list) {
            val team = Team(
                idTeam = favorite.id.toString(),
                idLeague = favorite.idLeague,
                strTeam = favorite.strTeam,
                intFormedYear = favorite.intFormedYear,
                strStadium = favorite.strStadium,
                strWebsite = favorite.strWebsite,
                strTeamBadge = favorite.strTeamBadge,
                strDescriptionEN = favorite.strDescriptionEN,
                strCountry = favorite.strCountry
            )
            favList.add(team)
        }
    }

    fun removeFromFavorite(context: Context, idTeam: Long) {
        try {
            context.database.use {
                delete(
                    TeamFavorite.TABLE_TEAM_FAVORITE, "(ID_ = {id})",
                    "id" to idTeam
                )
            }
        } catch (e: SQLiteConstraintException) {
            Log.e("error", "$e")
        }
    }

    fun insertFavTeam(team: Team, context: Context) {
        try {
            context.database.use {
                insert(
                    TeamFavorite.TABLE_TEAM_FAVORITE,
                    TeamFavorite.ID to team.idTeam.toInt(),
                    TeamFavorite.ID_LEAGUE to team.idLeague,
                    TeamFavorite.STR_TEAM to team.strTeam,
                    TeamFavorite.INT_FORMED_YEAR to team.intFormedYear,
                    TeamFavorite.STR_STADIUM to team.strStadium,
                    TeamFavorite.STR_WEBSITE to team.strWebsite,
                    TeamFavorite.STR_TEAM_BADGE to team.strTeamBadge,
                    TeamFavorite.STR_DESCRIPTION to team.strDescriptionEN,
                    TeamFavorite.STR_COUNTRY to team.strCountry
                )
            }
        } catch (e: SQLiteConstraintException) {
            Log.e("error", "$e")
        }
    }

    fun checkFavorite(team: Team) {
        for (fav in favList) {
            if (team.idTeam == fav.idTeam) {
                isFavorite = true
                break
            }
        }
    }
}