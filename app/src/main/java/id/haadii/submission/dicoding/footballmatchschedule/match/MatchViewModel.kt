package id.haadii.submission.dicoding.footballmatchschedule.match

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.haadii.submission.dicoding.footballmatchschedule.helper.database
import id.haadii.submission.dicoding.footballmatchschedule.model.Event
import id.haadii.submission.dicoding.footballmatchschedule.model.Favorite
import id.haadii.submission.dicoding.footballmatchschedule.repository.MatchRepository
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class MatchViewModel(private val repository: MatchRepository) : ViewModel() {

    private val nextMatch = MutableLiveData<ArrayList<Event>>()
    private val pastMatch = MutableLiveData<ArrayList<Event>>()
    private val searchEvent = MutableLiveData<ArrayList<Event>>()
    private val favEvent = MutableLiveData<ArrayList<Event>>()

    val loadData = MutableLiveData<Boolean>()
    private val nextList = ArrayList<Event>()
    private val pastList = ArrayList<Event>()
    private val searchList = ArrayList<Event>()
    private val favList = ArrayList<Event>()

    fun getNextMatch(id: String) {
        repository.getNextMatch(id) { listEvents ->
            if (listEvents != null) {
                for (event in listEvents) {
                    repository.getDetailTeam(event.idHomeTeam, event, isHomeTeam = true) {
                        nextList.add(it)
                        nextMatch.value = nextList
                    }

                    repository.getDetailTeam(event.idAwayTeam, event, isHomeTeam = false) {
                        nextList.add(it)
                        nextMatch.value = nextList
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
                        pastList.add(it)
                        pastMatch.value = pastList
                    }

                    repository.getDetailTeam(event.idAwayTeam, event, isHomeTeam = false) {
                        pastList.add(it)
                        pastMatch.value = pastList
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

    fun getSearchEvent(query: String) {
        repository.getSearchEvent(query) { events ->
            if (events != null) {
                for (event in events) {
                    repository.getDetailTeam(event.idHomeTeam, event, isHomeTeam = true) {
                        searchList.add(it)
                        searchEvent.value = searchList
                    }
                    repository.getDetailTeam(event.idAwayTeam, event, isHomeTeam = false) {
                        searchList.add(it)
                        searchEvent.value = searchList
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
                strBadgeAwayTeam = favorite.teamAwayBadge
            )
            favList.add(event)
        }
    }

}