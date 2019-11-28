package id.haadii.submission.dicoding.footballmatchschedule.match

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import id.haadii.submission.dicoding.footballmatchschedule.model.Event
import id.haadii.submission.dicoding.footballmatchschedule.repository.MatchRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MatchViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MatchViewModel
    private var repository = mock(MatchRepository::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MatchViewModel(repository)
    }

    @Test
    fun getNextMatch() {
        val idLeague = "4328"
        val events = ArrayList<Event>()
        events.add(
            Event(
                idEvent = "602249",
                strEvent = "Crystal Palace vs Liverpool",
                strSport = "Soccer",
                idLeague = "4328",
                strHomeTeam = "Crystal Palace",
                strAwayTeam = "Liverpool",
                dateEvent = "2019-11-23",
                strTime = "15:00:00",
                idHomeTeam = "133632",
                idAwayTeam = "133602",
                strBadgeHomeTeam = "https://www.thesportsdb.com/images/media/team/badge/rytxyw1448813222.png",
                strBadgeAwayTeam = "https://www.thesportsdb.com/images/media/team/badge/uvxuqq1448813372.png"
            )
        )
        val nextMatch = MutableLiveData<ArrayList<Event>>()
        nextMatch.value = events

        `when`(repository.getNextMatch(eq(idLeague), any())).thenAnswer {
            (it.arguments[1] as (ArrayList<Event>?) -> Unit).invoke(events)
        }

        val observer = mock(Observer::class.java) as Observer<ArrayList<Event>>
        viewModel.setNextMatch().value = events
        viewModel.setNextMatch().observeForever(observer)
        verify(observer, times(1)).onChanged(events)
        assertNotNull(viewModel.setNextMatch().value)
        assertEquals(events[0].idEvent, viewModel.setNextMatch().value?.get(0)?.idEvent)
    }

    @Test
    fun getPastMatch() {
        val idLeague = "4328"
        val events = ArrayList<Event>()
        events.add(
            Event(
                idEvent = "602247",
                strEvent = "Man United vs Brighton",
                strSport = "Soccer",
                idLeague = "4328",
                strHomeTeam = "Man United",
                strAwayTeam = "Brighton",
                dateEvent = "2019-11-10",
                strTime = "14:00:00",
                idHomeTeam = "133612",
                idAwayTeam = "133619",
                intHomeScore = "3",
                intAwayScore = "1",
                strHomeLineupForward = "Anthony Martial; ",
                strAwayLineupForward = "Neal Maupay; Aaron Connolly; ",
                strHomeGoalDetails = "17':Andreas Pereira;20':Scott McTominay;66':Marcus Rashford;",
                strAwayGoalDetails = "64':Lewis Dunk;",
                strHomeRedCards = "",
                strAwayRedCards = "",
                strHomeYellowCards = "45':Marcus Rashford;58':Brandon Williams;",
                strAwayYellowCards = "5':Dale Stephens;19':Lewis Dunk;31':Martin Montoya;45':Daniel Burn;74':Davy Propper;",
                strHomeLineupGoalkeeper = "David De Gea; ",
                strAwayLineupGoalkeeper = "Mathew Ryan; ",
                strBadgeHomeTeam = "https://www.thesportsdb.com/images/media/team/badge/xzqdr11517660252.png",
                strBadgeAwayTeam = "https://www.thesportsdb.com/images/media/team/badge/ywypts1448810904.png"
            )
        )
        val pastMatch = MutableLiveData<ArrayList<Event>>()
        pastMatch.value = events

        `when`(repository.getPastMatch(eq(idLeague), any())).thenAnswer {
            (it.arguments[1] as (ArrayList<Event>?) -> Unit).invoke(events)
        }

        val observer = mock(Observer::class.java) as Observer<ArrayList<Event>>
        viewModel.setPastMatch().value = events
        viewModel.setPastMatch().observeForever(observer)
        verify(observer, times(1)).onChanged(events)
        assertNotNull(viewModel.setPastMatch().value)
        assertEquals(events[0].idEvent, viewModel.setPastMatch().value?.get(0)?.idEvent)
    }

    @Test
    fun getSearchEvent() {
        val query = "Man United"
        val events = ArrayList<Event>()
        events.add(
            Event(
                idEvent = "602247",
                strEvent = "Man United vs Brighton",
                strSport = "Soccer",
                idLeague = "4328",
                strHomeTeam = "Man United",
                strAwayTeam = "Brighton",
                dateEvent = "2019-11-10",
                strTime = "14:00:00",
                idHomeTeam = "133612",
                idAwayTeam = "133619",
                intHomeScore = "3",
                intAwayScore = "1",
                strHomeLineupForward = "Anthony Martial; ",
                strAwayLineupForward = "Neal Maupay; Aaron Connolly; ",
                strHomeGoalDetails = "17':Andreas Pereira;20':Scott McTominay;66':Marcus Rashford;",
                strAwayGoalDetails = "64':Lewis Dunk;",
                strHomeRedCards = "",
                strAwayRedCards = "",
                strHomeYellowCards = "45':Marcus Rashford;58':Brandon Williams;",
                strAwayYellowCards = "5':Dale Stephens;19':Lewis Dunk;31':Martin Montoya;45':Daniel Burn;74':Davy Propper;",
                strHomeLineupGoalkeeper = "David De Gea; ",
                strAwayLineupGoalkeeper = "Mathew Ryan; ",
                strBadgeHomeTeam = "https://www.thesportsdb.com/images/media/team/badge/xzqdr11517660252.png",
                strBadgeAwayTeam = "https://www.thesportsdb.com/images/media/team/badge/ywypts1448810904.png"
            )
        )
        val searchEvent = MutableLiveData<ArrayList<Event>>()
        searchEvent.value = events

        `when`(repository.getSearchEvent(eq(query), any())).thenAnswer {
            (it.arguments[1] as (ArrayList<Event>?) -> Unit).invoke(events)
        }

        val observer = mock(Observer::class.java) as Observer<ArrayList<Event>>
        viewModel.setSearchEvent().value = events
        viewModel.setSearchEvent().observeForever(observer)
        verify(observer, times(1)).onChanged(events)
        assertNotNull(viewModel.setSearchEvent().value)
        assertEquals(events[0].idEvent, viewModel.setSearchEvent().value?.get(0)?.idEvent)
    }
}