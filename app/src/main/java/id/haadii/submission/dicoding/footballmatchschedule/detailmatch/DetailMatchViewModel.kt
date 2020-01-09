package id.haadii.submission.dicoding.footballmatchschedule.detailmatch

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import id.haadii.submission.dicoding.footballmatchschedule.helper.database
import id.haadii.submission.dicoding.footballmatchschedule.model.Event
import id.haadii.submission.dicoding.footballmatchschedule.model.Favorite
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailMatchViewModel(application: Application) : AndroidViewModel(application) {

    private val favList = ArrayList<Event>()
    var isFavorite: Boolean = false

    init {
        setFavoriteMatch(application)
    }

    fun removeFromFavorite(context: Context, idEvent: Long) {
        try {
            context.database.use {
                delete(
                    Favorite.TABLE_FAVORITE, "(ID_ = {id})",
                    "id" to idEvent
                )
            }
        } catch (e: SQLiteConstraintException) {
            Log.e("error", "$e")
        }
    }

    private fun setFavoriteMatch(context: Context) {
        context.database.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            setEvent(favorite)
        }
    }

    private fun setEvent(list: List<Favorite>) {
        for (favorite in list) {
            val event = Event(
                idEvent = favorite.id.toString(),
                idLeague = favorite.idLeague,
                strEvent = favorite.eventName,
                dateEvent = favorite.eventDate,
                strSport = favorite.eventSport,
                strTime = favorite.eventTime,
                idHomeTeam = favorite.teamHomeId,
                strHomeTeam = favorite.teamHomeName,
                intHomeScore = favorite.teamHomeScore,
                strHomeGoalDetails = favorite.teamHomeGoal,
                strHomeRedCards = favorite.teamHomeRedCards,
                strHomeYellowCards = favorite.teamHomeYellowCards,
                strHomeLineupGoalkeeper = favorite.teamHomeGoalKeeper,
                strHomeLineupForward = favorite.teamHomeLineUp,
                strBadgeHomeTeam = favorite.teamHomeBadge,
                idAwayTeam = favorite.teamAwayId,
                strAwayTeam = favorite.teamAwayName,
                intAwayScore = favorite.teamAwayScore,
                strAwayYellowCards = favorite.teamAwayYellowCards,
                strAwayRedCards = favorite.teamAwayRedCards,
                strAwayLineupGoalkeeper = favorite.teamAwayGoalKeeper,
                strAwayLineupForward = favorite.teamAwayLineUp,
                strAwayGoalDetails = favorite.teamAwayGoal,
                strBadgeAwayTeam = favorite.teamAwayBadge,
                isNextMatch = favorite.isNextMatch
            )
            favList.add(event)
        }
    }

    fun insertFavMatch(event: Event, isNextMatch: Boolean, context: Context) {
        try {
            context.database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.ID to event.idEvent.toInt(),
                    Favorite.ID_LEAGUE to event.idLeague,
                    Favorite.EVENT_TITLE to event.strEvent,
                    Favorite.EVENT_DATE to event.dateEvent,
                    Favorite.EVENT_SPORT to event.strSport,
                    Favorite.EVENT_TIME to event.strTime,
                    Favorite.TEAM_HOME_ID to event.idHomeTeam,
                    Favorite.TEAM_HOME_NAME to event.strHomeTeam,
                    Favorite.TEAM_HOME_BADGE to event.strBadgeHomeTeam,
                    Favorite.TEAM_HOME_GOAL to event.strHomeGoalDetails,
                    Favorite.TEAM_HOME_LINEUP_FORWARD to event.strHomeLineupForward,
                    Favorite.TEAM_HOME_GOAL_KEEPER to event.strHomeLineupGoalkeeper,
                    Favorite.TEAM_HOME_SCORE to event.intHomeScore,
                    Favorite.TEAM_HOME_RED_CARDS to event.strHomeRedCards,
                    Favorite.TEAM_HOME_YELLOW_CARDS to event.strHomeYellowCards,
                    Favorite.TEAM_AWAY_ID to event.idAwayTeam,
                    Favorite.TEAM_AWAY_NAME to event.strAwayTeam,
                    Favorite.TEAM_AWAY_BADGE to event.strBadgeAwayTeam,
                    Favorite.TEAM_AWAY_GOAL to event.strAwayGoalDetails,
                    Favorite.TEAM_AWAY_GOAL_KEEPER to event.strAwayLineupGoalkeeper,
                    Favorite.TEAM_AWAY_LINEUP_FORWARD to event.strAwayLineupGoalkeeper,
                    Favorite.TEAM_AWAY_SCORE to event.intAwayScore,
                    Favorite.TEAM_AWAY_RED_CARDS to event.strAwayRedCards,
                    Favorite.TEAM_AWAY_YELLOW_CARDS to event.strAwayYellowCards,
                    Favorite.IS_NEXT_MATCH to isNextMatch
                )
            }
        } catch (e: SQLiteConstraintException) {
            Log.e("error", "$e")
        }
    }

    fun checkFavorite(event: Event) {
        for (fav in favList) {
            if (event.idEvent == fav.idEvent) {
                isFavorite = true
                break
            }
        }
    }
}