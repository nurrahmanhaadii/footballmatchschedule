package id.haadii.submission.dicoding.footballmatchschedule.match

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.haadii.submission.dicoding.footballmatchschedule.helper.database
import id.haadii.submission.dicoding.footballmatchschedule.model.Event
import id.haadii.submission.dicoding.footballmatchschedule.model.Favorite
import id.haadii.submission.dicoding.footballmatchschedule.model.Team
import id.haadii.submission.dicoding.footballmatchschedule.model.TeamFavorite
import id.haadii.submission.dicoding.footballmatchschedule.repository.MatchRepository
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class MatchViewModel(private val repository: MatchRepository) : ViewModel() {

    private val nextMatch = MutableLiveData<ArrayList<Event>>()
    private val pastMatch = MutableLiveData<ArrayList<Event>>()
    private val teams = MutableLiveData<ArrayList<Team>>()
    private val searchEvent = MutableLiveData<ArrayList<Event>>()
    private val searchTeam = MutableLiveData<ArrayList<Team>>()
    private val favEvent = MutableLiveData<ArrayList<Event>>()
    private val favTeam = MutableLiveData<ArrayList<Team>>()

    val loadData = MutableLiveData<Boolean>()
    val loadDataTeam = MutableLiveData<Boolean>()
    val nextList = ArrayList<Event>()
    val pastList = ArrayList<Event>()
    val searchList = ArrayList<Event>()
    val searchTeamList = ArrayList<Team>()
    val favList = ArrayList<Event>()
    val favTeamList = ArrayList<Team>()

    fun getNextMatch(id: String) {
        repository.getNextMatch(id) { listEvents ->
            if (listEvents != null) {
                for (event in listEvents) {
                    repository.getDetailTeam(event.idHomeTeam, event, isHomeTeam = true) {
                        repository.getDetailTeam(
                            event.idAwayTeam,
                            event,
                            isHomeTeam = false
                        ) { event ->
                            nextList.add(event)
                            nextMatch.postValue(nextList)
                        }
                    }
                }
            } else {
                nextMatch.value = nextList
            }
        }
    }

    fun setNextMatch(): MutableLiveData<ArrayList<Event>> {
        return nextMatch
    }

    fun getPastMatch(id: String) {
        repository.getPastMatch(id) { events ->
            if (events != null) {
                for (event in events) {
                    repository.getDetailTeam(event.idHomeTeam, event, isHomeTeam = true) {
                        repository.getDetailTeam(
                            event.idAwayTeam,
                            event,
                            isHomeTeam = false
                        ) { event ->
                            pastList.add(event)
                            pastMatch.postValue(pastList)
                        }
                    }
                }
            } else {
                pastMatch.value = pastList
            }
        }
    }

    fun setPastMatch(): MutableLiveData<ArrayList<Event>> {
        return pastMatch
    }

    fun getTeamList(id: String) {
        repository.getTeamList(id) {
            teams.value = it
        }
    }

    fun setTeamList(): MutableLiveData<ArrayList<Team>> {
        return teams
    }

    fun getSearchEvent(query: String) {
        repository.getSearchEvent(query) { events ->
            if (events != null) {
                for (event in events) {
                    repository.getDetailTeam(event.idHomeTeam, event, isHomeTeam = true) {
                        repository.getDetailTeam(
                            event.idAwayTeam,
                            event,
                            isHomeTeam = false
                        ) { event ->
                            searchList.add(event)
                            searchEvent.postValue(searchList)
                        }
                    }
                }
            } else {
                searchEvent.value = searchList
            }
        }
    }

    fun setSearchEvent(): MutableLiveData<ArrayList<Event>> {
        return searchEvent
    }

    fun getSearchTeam(query: String) {
        repository.getSearchTeam(query) { team ->
            if (team?.teams.isNullOrEmpty()) {
                searchTeam.value = searchTeamList
            } else {
                searchTeam.value = team?.teams
            }
        }
    }

    fun setSearchTeam(): MutableLiveData<ArrayList<Team>> {
        return searchTeam
    }

    fun getFavoriteMatch(): MutableLiveData<ArrayList<Event>> {
        return favEvent
    }

    fun setFavoriteMatch(context: Context) {
        context.database.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            setEvent(favorite)
            favEvent.postValue(favList)
        }
    }

    fun getFavoriteTeam(): MutableLiveData<ArrayList<Team>> {
        return favTeam
    }

    fun setFavoriteTeam(context: Context) {
        context.database.use {
            val result = select(TeamFavorite.TABLE_TEAM_FAVORITE)
            val favorite = result.parseList(classParser<TeamFavorite>())
            setTeam(favorite)
            favTeam.postValue(favTeamList)
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
            favTeamList.add(team)
        }
    }

}